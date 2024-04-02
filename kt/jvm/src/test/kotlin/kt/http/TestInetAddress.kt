package kt.http

import org.junit.Test
import java.net.InetAddress



class TestInetAddress {

    @Test
    fun `test inet address`() {
        val str = "www.google.com"
        for (inetAddress in InetAddress.getAllByName(str)) {
            val hostAddress = inetAddress.hostAddress
            println(hostAddress)
        }
    }
}