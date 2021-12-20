package kt.basic.pitfall


infix fun Int.divBy(r2: Int) : Rational = Rational(toInt(), r2.toInt())

open class AspectRatioScreen {
    var aspectRatio: Rational = 4 divBy  3
}

class ResolutionScreenFall: AspectRatioScreen() {

//    If you define a custom setter, it will be
//    called every time you assign a value to
//    the property, except its initialization.

    var width: Int = 1920
        set(value) {
            field = value
            println("Set width to $value")
            recalculateAspectRatio()
        }

    var height: Int = 1080
        set(value) {
            field = value
            println("Set width to $value")
            recalculateAspectRatio()
        }

    private fun recalculateAspectRatio() {
        aspectRatio = (width divBy height)
    }
}

class ResolutionScreen: AspectRatioScreen() {

    var width: Int = 1920
        set(value) {
            field = value
            println("Set width to $value")
        }

    var height: Int = 1080
        set(value) {
            field = value
            println("Set width to $value")
        }

    init {
        recalculateAspectRatio()
    }

    private fun recalculateAspectRatio() {
        aspectRatio = (width divBy height)
    }
}

fun main() {
    val screen = ResolutionScreenFall()
    println("Screen resolution fall: ${screen.width}x${screen.height}, " +
        "ratio: ${screen.aspectRatio}")

    val screen2 = ResolutionScreen()
    println("Screen resolution: ${screen2.width}x${screen2.height}, " +
            "ratio: ${screen2.aspectRatio}")

    val screen3 = ResolutionScreenFall()
    screen3.width = 1920
    screen3.height = 1080
    println("Screen resolution fall: ${screen3.width}x${screen3.height}, " +
            "ratio: ${screen3.aspectRatio}")
}