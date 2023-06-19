package kt.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun sharedFlow() = runBlocking {
     val sharedFlow = MutableSharedFlow<Int>(replay = 3, extraBufferCapacity = 0)

     // observe values
     val job = launch {
          sharedFlow.collect {
               println("job1 $it")
          }
     }

     val job2 = launch {
          sharedFlow.collect {
               println("job2 $it")
          }
     }

     // change values
     (1..5).forEach { it ->
          delay(500)
          sharedFlow.emit(it) // 1 2 3 4 5
          // value = 1 // 0 1
     }

     // replay = 0 don't print
     val job3 = launch {
          sharedFlow.collect {
               println("job3 $it")
          }
     }

     // wait running job end
     job.join()

     job2.join()

     job3.join()
}