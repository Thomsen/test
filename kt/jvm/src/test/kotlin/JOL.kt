import org.junit.Test
import org.openjdk.jol.info.ClassLayout
import org.openjdk.jol.info.GraphLayout
import org.openjdk.jol.vm.VM

class JOL {

    @Test
    fun testVM() {
        println (VM.current().details())
    }

    @Test
    fun testString() {
        println(ClassLayout.parseClass(String::class.java).toPrintable())
    }

    @Test
    fun testInstance() {
        println(ClassLayout.parseInstance("str").toPrintable())
    }

    @Test
    fun testArray() {
        println(ClassLayout.parseClass(ByteArray::class.java).toPrintable())
    }

    // auto wrapper
    @Test
    fun testLongInstance() {
        println(ClassLayout.parseClass(Long::class.java).toPrintable())
        println(ClassLayout.parseInstance(12311231314213141L).toPrintable())
    }

    @Test
    fun testReference() {
        val hashMap = hashMapOf<String, String>()
        hashMap["name"] = "thom"
        println(GraphLayout.parseInstance(hashMap).toPrintable())
    }
}