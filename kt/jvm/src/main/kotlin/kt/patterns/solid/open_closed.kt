package kt.patterns.solid

// open-closed principle

class Rectangle0 (
     var length: Int,
     var height: Int,
)

class Circle0 (
    var radius: Float,
)

class AreaFactory0 {
    fun calculateArea(shapes: ArrayList<Any>): Double {
        var area = 0.0
        shapes.forEach { shape ->
            when (shape) {
                is Rectangle0 -> {
                    val rect = shape as Rectangle0
                    area += rect.height * rect.length
                }
                is Circle0 -> {
                    val circle = shape as Circle0
                    area += (circle.radius) * (circle.radius) * Math.PI
                }
                else -> {
                    throw RuntimeException("shape not supported")
                }
            }
        }
        return area
    }
}

// ->

interface Shape {
    fun getArea(): Double
}

data class Rectangle(
    var length: Int,
    var height: Int,
): Shape {
    override fun getArea(): Double {
        return (length * height).toDouble()
    }
}

data class Circle(
    var radius: Float,
): Shape {
    override fun getArea(): Double {
        return radius * radius * Math.PI
    }
}

class AreaFactory() {
    fun calculateArea(shapes: ArrayList<Shape>): Double {
        var area = 0.0
        for (shape in shapes) {
            area += shape.getArea()
        }
        return area
    }
}