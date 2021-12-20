package kt.patterns.structural.facade

class ShapeMaker {

    private lateinit var circle: Circle
    private lateinit var rectangle: Rectangle
    private lateinit var square: Square

    constructor() {
        circle = Circle()
        rectangle = Rectangle()
        square = Square()
    }

    fun drawCircle() {
        circle.draw()
    }

    fun drawRectangle() {
        rectangle.draw()
    }

    fun drawSquare() {
        square.draw()
    }
}