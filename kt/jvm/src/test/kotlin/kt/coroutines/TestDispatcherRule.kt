package kt.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import kt.repository.HomeViewModel
import kt.repository.Repository
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import kotlin.test.assertEquals

// Reusable JUnit4 TestRule to override the Main dispatcher
class MainDispatcherRule(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}

class HomeViewModelTestUsingRule {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun settingMainDispatcher() = runTest { // Uses Main’s scheduler
        val viewModel = HomeViewModel()
        viewModel.loadMessage()
        assertEquals("Greetings!", viewModel.message.value)
    }
}

class DispatcherTypesTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun injectingTestDispatchers() = runTest { // Uses Main’s scheduler
        // Use the UnconfinedTestDispatcher from the Main dispatcher
        val unconfinedRepo = Repository(mainDispatcherRule.testDispatcher)

        // Create a new StandardTestDispatcher (uses Main’s scheduler)
        val standardRepo = Repository(StandardTestDispatcher())
    }
}

class ExampleTest {
    val testScheduler = TestCoroutineScheduler()
    val testDispatcher = StandardTestDispatcher(testScheduler)
    val testScope = TestScope(testDispatcher)

    @Test
    fun someTest() = testScope.runTest {

    }
}
