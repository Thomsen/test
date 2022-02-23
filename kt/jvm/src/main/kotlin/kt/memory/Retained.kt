package kt.memory

class Retained {
}

val c1 = RetainC1()

class RetainA {
    val b: RetainB = RetainB()
    val c = RetainC()
    val ac1 = c1
}

class RetainB {
    val c = RetainC()
    val bc1 = c1
}

class RetainC {
    val d = RetainD()
}

class RetainC1 {
}

class RetainD {
}