package kt.mq

import kt.basic.printlnWithTime
import kt.mq.loop.Looper
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import java.util.concurrent.*
import kotlin.concurrent.thread

class TestActivityThread {

    @Before
    fun setup() {
    }

    @After
    fun quit() {
    }

    @Test
    fun `test mock`() {
        val mockedList: MutableList<String> = mock()

        mockedList.add("one")

        printlnWithTime(mockedList[0])

        `when`(mockedList[0]).thenReturn("one2")

        mockedList.clear()

        // verify the method invoked
        verify(mockedList).add("one")
        verify(mockedList).clear()
    }

    @Test
    fun `test looper quit by count down latch`() {
        printlnWithTime("test looper quit by count down latch")
        Looper.prepare()

        val activityThread = ActivityThread()

        val latch = CountDownLatch(2)

        // thread is kt extension
        thread {
            Thread.sleep(2000)
            activityThread.sendEmptyMessage(100)
            latch.countDown()
        }

        thread {
            Thread.sleep(1000)
            activityThread.sendEmptyMessage(101)
            latch.countDown()
        }

        activityThread.sendEmptyMessage(11)

        // await and notifyAll
        latch.await()

        thread {
            activityThread.sendEmptyMessage(ActivityThread.QUIT)
        }

        Looper.loop()
    }


    @Test
    fun `test looper quit by thread join`() {
        printlnWithTime("test looper quit by thread join")
        Looper.prepare()

        val activityThread = ActivityThread()

        thread {
            val threads = ArrayList<Thread>()

            threads.add(thread {
                Thread.sleep(2000)
                activityThread.sendEmptyMessage(100)
            })

            threads.add(thread {
                Thread.sleep(1000)
                activityThread.sendEmptyMessage(101)
            })

            activityThread.sendEmptyMessage(11)

            for (t in threads) {
                t.join()
            }

            Thread {
                activityThread.sendEmptyMessage(ActivityThread.QUIT)
            }.start()
        }

        Looper.loop()
    }

    @Test
    fun `test looper quit by future`() {
        printlnWithTime("test looper quit by future")
        Looper.prepare()

        val activityThread = ActivityThread()

        thread {
            val executor = Executors.newFixedThreadPool(3)
            val tasks = ArrayList<Callable<Boolean>>()


            tasks.add(Callable<Boolean> {
                Thread.sleep(2000)
                activityThread.sendEmptyMessage(100)
            })

            tasks.add(Callable {
                Thread.sleep(1000)
                activityThread.sendEmptyMessage(101)
            })

            tasks.add(Callable {
                Thread.sleep(3000)
                activityThread.sendEmptyMessage(201)
            })

            tasks.add(Callable {
                Thread.sleep(1000)
                activityThread.sendEmptyMessage(202)
            })

            activityThread.sendEmptyMessage(11)

            val futures = executor.invokeAll(tasks)
            for (t in futures) {
                t.get()
            }

            Thread {
                activityThread.sendEmptyMessage(ActivityThread.QUIT)
            }.start()
        }

        Looper.loop()
    }

    @Test
    fun `test looper quit by completion service`() {
        printlnWithTime("test looper quit by completion service")
        Looper.prepare()

        val activityThread = ActivityThread()

        thread {
            val executor = Executors.newFixedThreadPool(3)
            val completions = ExecutorCompletionService<Boolean>(executor)

            completions.submit(Callable<Boolean> {
                Thread.sleep(2000)
                activityThread.sendEmptyMessage(100)
            })

            completions.submit(Callable {
                Thread.sleep(1000)
                activityThread.sendEmptyMessage(101)
            })

            completions.submit(Callable {
                Thread.sleep(3000)
                activityThread.sendEmptyMessage(201)
            })

            completions.submit(Callable {
                Thread.sleep(1000)
                activityThread.sendEmptyMessage(202)
            })

            activityThread.sendEmptyMessage(11)

            for (t in 0..3) {
                completions.take()
            }

            Thread {
                activityThread.sendEmptyMessage(ActivityThread.QUIT)
            }.start()
        }

        Looper.loop()
    }

    @Test
    fun `test looper quit by completion service with future`() {
        printlnWithTime("test looper quit by completion service with future")
        Looper.prepare()

        val activityThread = ActivityThread()

        thread {
            val executor = Executors.newFixedThreadPool(3)
            val completions = ExecutorCompletionService<Boolean>(executor)
            val futures = ArrayList<Future<Boolean>>()

            futures.add(completions.submit(Callable<Boolean> {
                Thread.sleep(2000)
                activityThread.sendEmptyMessage(100)
            }))

            futures.add(completions.submit(Callable {
                Thread.sleep(1000)
                activityThread.sendEmptyMessage(101)
            }))

            futures.add(completions.submit(Callable {
                Thread.sleep(3000)
                activityThread.sendEmptyMessage(201)
            }))

            futures.add(completions.submit(Callable {
                Thread.sleep(1000)
                activityThread.sendEmptyMessage(202)
            }))

            activityThread.sendEmptyMessage(11)

            for (t in futures) {
                t.get()
            }

            Thread {
                activityThread.sendEmptyMessage(ActivityThread.QUIT)
            }.start()
        }

        Looper.loop()
    }

    @Test
    fun `test looper quit by thread state`() {
        printlnWithTime("test looper quit by thread state")
        Looper.prepare()

        val activityThread = ActivityThread()

        thread {
            val threads = ArrayList<Thread>()

            threads.add(thread {
                Thread.sleep(2000)
                activityThread.sendEmptyMessage(100)
            })

            threads.add(thread {
                Thread.sleep(1000)
                activityThread.sendEmptyMessage(101)
            })

            activityThread.sendEmptyMessage(11)

            var allThreadFinished = false
            while (!allThreadFinished) {
                allThreadFinished = true

                for (t in threads) {
                    if (t.state != Thread.State.TERMINATED) {
                        allThreadFinished = false
                        break
                    }
                }
            }

            Thread {
                activityThread.sendEmptyMessage(ActivityThread.QUIT)
            }.start()
        }

        Looper.loop()
    }


    @Test
    fun `test looper quit by cyclic barrier`() {
        printlnWithTime("test looper quit by cyclic barrier")
        Looper.prepare()

        val activityThread = ActivityThread()

        thread {
            val cyclicBarrier = CyclicBarrier(2) {
                Thread {
                    activityThread.sendEmptyMessage(ActivityThread.QUIT)
                }.start()
            }

            val threads = ArrayList<Thread>()

            threads.add(thread {
                Thread.sleep(2000)
                activityThread.sendEmptyMessage(100)
                cyclicBarrier.await()
            })

            threads.add(thread {
                Thread.sleep(1000)
                activityThread.sendEmptyMessage(101)
                cyclicBarrier.await()
            })

            activityThread.sendEmptyMessage(11)

        }

        Looper.loop()
    }

}