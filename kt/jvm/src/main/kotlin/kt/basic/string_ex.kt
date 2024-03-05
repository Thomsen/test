package kt.basic

// "Aa" = 65 97
// "BB" = 66 66
fun String.hash(): Int {
    var hash = 0
    var weight = 1
    val prime = 31
    // default hashcode algorithm
//    for (i in indices) {
//        // 65*31(2015) + 97 = 2112
//        // 66*31(2046) + 66 = 2112
//        hash = prime * hash + this[i].code
//    }
    // 66 + 66*31(2046) = 2112
    for (element in this) {
        hash += element.code * weight
        weight *= prime
    }
    return hash
}