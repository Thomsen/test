package cast
interface Processor {
    fun process()
}

inline fun inlineAction(f: () -> Unit) = f()

fun nextProcessor(): Processor? = null

fun runProcessor(): Processor? {
    var processor: Processor? = null

    inlineAction {
        // If processor isn't null, processor is smart-cast
        if (processor != null) {
            // 1.9.25
//            processor?.process()
            // 2.00
//            processor.process()
        }
        processor = nextProcessor()
    }

    return processor
}

fun main() {
    runProcessor()
}