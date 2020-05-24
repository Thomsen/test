import java.util.*

fun main(args: Array<String>) {

    val timeZone = "America/Los_Angeles"
    val timeZoneRegion = TimeZone.getTimeZone(timeZone)
    val calendar = GregorianCalendar(timeZoneRegion)

    println(timeZoneRegion.toString())

    println(calendar.time)
    println(calendar.toZonedDateTime())


}