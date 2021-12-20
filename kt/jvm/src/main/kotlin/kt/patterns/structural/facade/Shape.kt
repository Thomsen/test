package kt.patterns.structural.facade

interface Shape {
    fun draw()
}

class Rectangle : Shape {
    override fun draw() {
        println("Rectangle::draw()")
    }
}

class Square : Shape {
    override fun draw() {
        println("Square::draw()")
    }
}

class Circle : Shape {
    override fun draw() {
        println("Circle::draw()")
    }
}