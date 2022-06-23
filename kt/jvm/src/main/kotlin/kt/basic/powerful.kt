package kt.basic

import com.fasterxml.jackson.databind.util.JSONPObject
import com.google.gson.JsonObject
import kt.generic.json

// making a function generator

fun magnitude(times: Int): (Int) -> Int {
    return { it * times}
}

fun magnitudePower(times: Int): Int.() -> Int {
    return { this * times}
}

// reduce code duplication

class TextView() {
    fun setTextColor(color: String) {

    }
    fun setBackgroundResources(resId: Int) {

    }
}

class MonthView {
    val tvJan: TextView = TextView()
    val tvFeb: TextView = TextView()
}

fun MonthView.setMonthTextStyle(month: Int) {
    when (month) {
        0 -> {
            tvJan.setTextColor("#ffefefef")
            tvJan.setBackgroundResources(0)
        }
        1 -> {
            tvJan.setTextColor("#ffefefef")
            tvJan.setBackgroundResources(0)
        }
        // ...
    }
}

fun MonthView.setMonthTextStylePower(month: Int) {

    val select: TextView.() -> Unit = {
        setTextColor("#ffefefef")
        setMonthTextStyle(0)
    }

    when (month) {
        0 -> tvJan.select()
        1 -> tvFeb.select()
    }
}

// ease of iteration

class JSONObject {

    private val entity = mutableMapOf<String, Any>()
    var keys = entity.iterator()

    fun get(entry: MutableMap.MutableEntry<String, Any>): Any? {
        return entity[entry.key]
    }


}

fun parseJson(jsonObject: JSONObject) {
    val keys = jsonObject.keys
    while (keys.hasNext()) {
        val key = keys.next()
        if (jsonObject.get(key) is JSONObject) {
            val childObj = jsonObject.get(key) as JsonObject
        }
    }
}

inline fun JSONObject.parseJsonPower(
    block: (JsonObject) -> Unit
) {
    val _keys = keys
    while (_keys.hasNext()) {
        val key = _keys.next()
        if (get(key) is JSONObject) {
            block(get(key) as JsonObject)
        }
    }
}

// perform some code before and after the function

fun acceptTestCases(block: () -> Unit): Long {
    val time0 = System.currentTimeMillis()
    block()
    val time = System.currentTimeMillis()
    println("build time: ${time - time0} ms")
    return time
}

// kotlin dsl

fun htmlContent(): HTML {
    val content = html {                                            // 1
        head {                                                     // 2
            title { +"HTML encoding with Kotlin" }
        }
        body {                                                     // 2
            h1 { +"HTML encoding with Kotlin" }
            p {
                +"this format can be used as an"                   // 3
                +"alternative markup to HTML"                      // 3
            }

            // an element with attributes and text content
            a(href = "http://kotlinlang.org") { +"Kotlin" }

            // mixed content
            p {
                +"This is some"
                b { +"mixed" }
                +"text. For more see the"
                a(href = "http://kotlinlang.org") {
                    +"Kotlin"
                }
                +"project"
            }
            p {
                +"some text"
                ul {
                    for (i in 1..5)
                        li { +"${i}*2 = ${i*2}" }
                }
            }
        }
    }
    println(content)
    return content
}

interface Element {
    fun render(builder: StringBuilder, indent: String)
}

class TextElement(val text: String) : Element {
    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent$text\n")
    }
}

@DslMarker
annotation class HtmlTagMarker

@HtmlTagMarker
abstract class Tag(val name: String) : Element {
    val children = arrayListOf<Element>()
    val attributes = hashMapOf<String, String>()

    protected fun <T : Element> initTag(tag: T, init: T.() -> Unit): T {
        tag.init()
        children.add(tag)
        return tag
    }

    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent<$name${renderAttributes()}>\n")
        for (c in children) {
            c.render(builder, indent + "  ")
        }
        builder.append("$indent</$name>\n")
    }

    private fun renderAttributes(): String {
        val builder = StringBuilder()
        for ((attr, value) in attributes) {
            builder.append(" $attr=\"$value\"")
        }
        return builder.toString()
    }

    override fun toString(): String {
        val builder = StringBuilder()
        render(builder, "")
        return builder.toString()
    }
}

abstract class TagWithText(name: String) : Tag(name) {
    operator fun String.unaryPlus() {
        children.add(TextElement(this))
    }
}
class HTML() : TagWithText("html") {
    fun head(init: Head.() -> Unit) = initTag(Head(), init)
    fun body(init: Body.() -> Unit) = initTag(Body(), init)
}


class Head() : TagWithText("head") {
    fun title(init: Title.() -> Unit) = initTag(Title(), init)
}

class Title() : TagWithText("title")

abstract class BodyTag(name: String) : TagWithText(name) {
    fun b(init: B.() -> Unit) = initTag(B(), init)
    fun p(init: P.() -> Unit) = initTag(P(), init)
    fun h1(init: H1.() -> Unit) = initTag(H1(), init)
    fun ul(init: UL.() -> Unit) = initTag(UL(), init)
    fun a(href: String, init: A.() -> Unit) {
        val a = initTag(A(), init)
        a.href = href
    }
}

class Body() : BodyTag("body")
class UL() : BodyTag("ul") {
    fun li(init: LI.() -> Unit) = initTag(LI(), init)
}

class B() : BodyTag("b")
class LI() : BodyTag("li")
class P() : BodyTag("p")
class H1() : BodyTag("h1")

class A : BodyTag("a") {
    var href: String
        get() = attributes["href"]!!
        set(value) {
            attributes["href"] = value
        }
}

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()
    html.init()
    return html
}

