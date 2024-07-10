package kt.patterns.structrual

import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.*
import kt.patterns.structural.flyweight.ImageFactory
import org.junit.Before
import org.junit.Test

class TestFlyWeight {

    lateinit var url: String

    @Before
    fun init() {
        url = "https://plus.unsplash.com/premium_photo-1689008992112-f7bdca70877f?q=80&w=2787&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
    }


    @Test
    fun `test image factory to fetch 0 non-blocking`() = runBlocking {
        val factory = ImageFactory()

        coroutineScope {
            val job1 = launch(Dispatchers.IO) {
                val image1 = factory.get0(url)
                // Add assertions or other test logic for image1
                assertNotNull(image1)
            }

            val job2 = launch(Dispatchers.IO) {
                val image2 = factory.get0(url)
                // Add assertions or other test logic for image2
                assertNotNull(image2)
            }

            // Wait for both jobs to complete
            joinAll(job1, job2)
        }

        // Other non-blocking test logic
    }

    @Test
    fun `test image factory to fetch 0`() = runBlocking {
        val factory = ImageFactory()

        val job1 = launch {
            val image1 = factory.get0(url)
            // Add assertions or other test logic for image1
            assertNotNull(image1)
        }

        val job2 = launch {
            val image2 = factory.get0(url)
            // Add assertions or other test logic for image2
            assertNotNull(image2)
        }

        joinAll(job1, job2)
    }

    @Test
    fun `test image factory to fetch blocking`() = runBlocking {
        val factory = ImageFactory()

        val job1 = launch {
            val image1 = factory.get(url)
            // Add assertions or other test logic for image1
            assertNotNull(image1)
        }

        val job2 = launch {
            val image2 = factory.get(url)
            // Add assertions or other test logic for image2
            assertNotNull(image2)
        }

        joinAll(job1, job2)
    }

    @Test
    fun `test image factory to fetch no-blocking`() = runBlocking {
        val factory = ImageFactory()

        coroutineScope {
            val job1 = launch(Dispatchers.IO) {
                val image1 = factory.get(url)
                // Add assertions or other test logic for image1
                assertNotNull(image1)
            }

            val job2 = launch(Dispatchers.IO) {
                val image2 = factory.get(url)
                // Add assertions or other test logic for image2
                assertNotNull(image2)
            }

            joinAll(job1, job2)
        }

    }
}