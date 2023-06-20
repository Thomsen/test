package kt.advance

import org.junit.Test
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.days
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime

class TestDSL {

    @Test
    @ExperimentalTime
    fun testDate() {
        val yesterday = 1.days.toDouble(DurationUnit.DAYS)
        println(yesterday)
    }

    @Test
    fun testConference1() {
        val c = Conference("ccc", "China")
        val t1 = Talk("topic1", "speaker1", LocalDateTime.now())
        val t2 = Talk("topic2", "speaker2", LocalDateTime.now())
        c.addTalk(t1)
        c.addTalk(t2)

        val c2 = conference {
            name = "GDG 2020"
            location = "China"
//            talks {
//                conferenceTalk("Android 11", "Speaker1", LocalDateTime.parse("2020-07-04T14:00"))
//
//                conferenceTalk at "2020-07-04T15:00" by "Speaker2" titled "Kotlin"
//
//                +Talk("Classmate", "Speaker3", LocalDateTime.parse("2020-07-04T16:00"))
//            }

            talks.apply {
                conferenceTalk("Android 11", "Speaker1", LocalDateTime.parse("2020-07-04T14:00"))

                conferenceTalk at "2020-07-04T15:00" by "Speaker2" titled "Kotlin Coroutines"

                +Talk("Classmate", "Speaker3", LocalDateTime.parse("2020-07-04T16:00"))
            }
        }

        println(c.talks[0].topic)

        println(c2.talks[1].topic)
    }

}

fun testConference() {
    conference {
        name = "GDG 2020"
        location = "China"
        talks.apply {
            conferenceTalk("Android 11", "Speaker1", LocalDateTime.parse("2020-07-04T14:00"))

            conferenceTalk at "2020-07-04T15:00" by "Speaker2" titled "Kotlin"

            +Talk("Classmate", "Speaker3", LocalDateTime.parse("2020-07-04T16:00"))
        }
    }
}

class Conference(val name: String, val location: String) {
    private val schedule: MutableList<Talk> = mutableListOf()

    fun addTalk(t: Talk) {
        schedule.add(t)
    }

    val talks: List<Talk>
        get() = schedule.toList()

}

inline fun conference(config: ConferenceDSL.() -> Unit): Conference {
    val dsl: ConferenceDSL = ConferenceDSL().apply(config)
    return Conference(dsl.name, dsl.location).apply {
        dsl.talkList.forEach(this::addTalk)
    }
}

@ConfDslMarker
class ConferenceDSL() {
    private val _talkList: MutableList<Talk> = mutableListOf()
    val talkList
        get() = _talkList.toList()

    lateinit var name: String
    lateinit var location: String

    val talks = TalkConfigDSL()

    @ConfDslMarker
    inner class TalkConfigDSL() {
        private val _talkList: MutableList<Talk> = this@ConferenceDSL._talkList
        operator fun invoke(config: ConferenceDSL.() -> Unit) {
            this.apply { config }
        }

        fun conferenceTalk(topic: String, speaker: String, time: LocalDateTime) {
            _talkList.add(Talk(topic, speaker, time, TalkType.Conference))
        }

        val conferenceTalk: ConferenceDSL.TalkConfigDSL.EmptyTalk
            get() = EmptyTalk(TalkType.Conference)

        inner class EmptyTalk(val type: TalkType) {
            infix fun at(timeString: String): ConferenceDSL.TalkConfigDSL.TimedTalk =
                TimedTalk(this, LocalDateTime.parse(timeString))
        }

        inner class TimedTalk(
            val previous: EmptyTalk,
            val time: LocalDateTime
        ) {
            infix fun by(speaker: String): ConferenceDSL.TalkConfigDSL.TimedAndAuthoredTalk
                    =TimedAndAuthoredTalk(this, speaker)
        }

        inner class TimedAndAuthoredTalk(
            val previous: TimedTalk,
            val speaker: String
        ) {
            infix fun titled(topic: String) {
                _talkList.add(Talk(topic, speaker, previous.time, previous.previous.type))
            }
        }

        // member extension function
        open operator fun Talk.unaryPlus(): Boolean = _talkList.add(this)
    }
}

enum class TalkType {
    Conference,
    KeyNote
}

data class Talk(
    val topic: String,
    val speaker: String,
    val time: LocalDateTime,
    val type: TalkType = TalkType.Conference
)

@DslMarker
annotation class ConfDslMarker