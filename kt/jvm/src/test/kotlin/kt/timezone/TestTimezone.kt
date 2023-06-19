package kt.timezone

import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class TestTimezone {

    @Test
    fun `test timezone`()  {
        val timeZone = "America/Los_Angeles"
        val timeZoneRegion = TimeZone.getTimeZone(timeZone)
        val calendar = GregorianCalendar(timeZoneRegion)

        println(timeZoneRegion.toString())

        println(calendar.time)
        println(calendar.toZonedDateTime())

        assertEquals("Pacific Standard Time", timeZoneRegion.displayName)
        assertEquals("America/Los_Angeles", timeZoneRegion.id)

    }
}