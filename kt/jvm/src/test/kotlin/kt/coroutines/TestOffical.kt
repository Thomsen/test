package kt.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import kt.repository.BetterRepository
import kt.repository.HomeViewModel
import kt.repository.Repository
import kt.repository.UserRepository
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class TestOfficial {

    suspend fun fetchData(): String {
        delay(1000L)
        return "Hello world"
    }

    @Test
    fun dataShouldBeHelloWorld() = runTest {
        val data = fetchData()
        assertEquals("Hello world", data)
    }

    @Test
    // default StandardTestDispatcher
    fun standardTest() = runTest {
        val userRepo = UserRepository()

        launch { userRepo.register("Alice") }
        launch { userRepo.register("Bob") }

        // fail expected: <[Alice, Bob]> but was: <[]>
        assertEquals(listOf("Alice", "Bob"), userRepo.getAllUsers())
    }

    @Test
    fun standardYieldTest() = runTest {
        val userRepo = UserRepository()

        launch { userRepo.register("Alice") }
        launch { userRepo.register("Bob") }
        advanceUntilIdle() // Yields to perform the registrations

        assertEquals(listOf("Alice", "Bob"), userRepo.getAllUsers()) //
    }

    @Test  // runTest(UnconfinedTestDispatcher()) == runBlockingTest
    fun unconfinedTest() = runTest(UnconfinedTestDispatcher()) {
        val userRepo = UserRepository()

        // immediately register and back
        launch { userRepo.register("Alice") }
        launch { userRepo.register("Bob") }

        assertEquals(listOf("Alice", "Bob"), userRepo.getAllUsers()) // ✅ Passes
    }

    @Test  // runTest(UnconfinedTestDispatcher()) == runBlockingTest
    fun blockingTest() = runBlockingTest {
        val userRepo = UserRepository()

        // immediately register and back
        launch { userRepo.register("Alice") }
        launch { userRepo.register("Bob") }

        assertEquals(listOf("Alice", "Bob"), userRepo.getAllUsers()) // ✅ Passes
    }

    @Test
    fun yieldingTest() = runTest(UnconfinedTestDispatcher()) {
        val userRepo = UserRepository()

        // delay suspend not immediately back
        launch {
            userRepo.register("Alice")
            delay(10L)
            userRepo.register("Bob")
        }

        assertEquals(listOf("Alice", "Bob"), userRepo.getAllUsers()) // ❌ Fails
    }

    @Test
    fun yieldingTwoTest() = runTest(UnconfinedTestDispatcher()) {
        val userRepo = UserRepository()

        // delay suspend not immediately back
        launch {
            userRepo.register("Alice")
            delay(10L)
            userRepo.register("Bob")
        }

        launch {
            userRepo.register("Bob")
            delay(1L)
            userRepo.register("Wile")
        }

        assertEquals(listOf("Alice", "Bob"), userRepo.getAllUsers()) // Passes
    }

    @Test
    fun repoInitWorksAndDataIsHelloWorld() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        val repository = Repository(dispatcher)

        repository.initialize()
        advanceUntilIdle() // Runs the new coroutine
        assertEquals(true, repository.initialized.get())

        val data = repository.fetchData() // No thread switch, delay is skipped
        assertEquals("Hello world", data)
    }

    @Test
    fun repoInitWorks() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        val repository = BetterRepository(dispatcher)

        repository.initialize().await() // Suspends until the new coroutine is done
        assertEquals(true, repository.initialized.get())

        val data = repository.fetchData() // No thread switch, delay is skipped
        assertEquals("Hello world", data)
    }

    @Test
    fun settingMainDispatcher() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)

        try {
            val viewModel = HomeViewModel()
            viewModel.loadMessage() // Uses testDispatcher, runs its coroutine eagerly
            assertEquals("Greetings!", viewModel.message.value)
        } finally {
            Dispatchers.resetMain()
        }
    }

}

