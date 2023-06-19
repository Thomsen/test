package kt.parser

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import kt.generic.json
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.expect

class TestGsonParser {

    lateinit var gson: Gson
    lateinit var gsonFac: Gson


    lateinit var sco: Score
    lateinit var sco2: Score2

    lateinit var sco2Str: String
    lateinit var sco2Element: JsonElement

    @Before
    fun setup() {
        gson = Gson()
        gsonFac = GsonBuilder()
            .registerTypeAdapterFactory(GsonTypeAdapterFactory())
            .create()

        sco = Score()
        sco2 = Score2()
        sco2Str = gson.toJson(sco2)
        sco2Element = gson.toJsonTree(sco2)
    }

    @Test
    fun `test number to json`() {
        assertEquals("1", gson.toJson(num))
    }

    @Test
    fun `test number tree to json`() {
        val jsonEle = gson.toJsonTree(num)
        val jsonObj = JsonPrimitive(1)
        assertEquals(jsonObj, jsonEle)
    }

    @Test
    fun `test score to json`() {
        val scoStr = gson.toJson(sco)
        assertEquals("{\"num\":1,\"isNum\":1}", scoStr)
    }

    @Test
    fun `test score tree to json`() {
        val scoEle = gson.toJsonTree(sco)
        val jsonObj = JsonObject()
        jsonObj.add("num", JsonPrimitive(1))
        jsonObj.add("isNum", JsonPrimitive(1))
        assertEquals(jsonObj, scoEle)
    }

    @Test
    fun `test score2 to json`() {
        assertEquals("{\"num\":1,\"isNum\":1}", sco2Str)
    }

    @Test
    fun `test score2 tree to json`() {
        val jsonObj = JsonObject()
        jsonObj.add("num", JsonPrimitive(1))
        jsonObj.add("isNum", JsonPrimitive(1))
        assertEquals(jsonObj, sco2Element)
    }

    @Test
    fun `test a`() {
        val sco2Obj = gson.fromJson(sco2Element, Score2::class.java)
        val sco2ObjTree = gson.toJsonTree(sco2Obj)

        val jsonObj = JsonObject()
        jsonObj.add("num", JsonPrimitive(1))
        jsonObj.add("isNum", JsonPrimitive(1))

        assertEquals(jsonObj, sco2ObjTree)
    }

    @Test
    fun `test b`() {
        val sco2Obj = gson.fromJson(sco2Element, Score2::class.java)
        // number is com.google.gson.internal.LazilyParsedNumber
        val sco2ObjStr2 = gsonFac.toJson(sco2Obj)

        assertEquals("{\"num\":1,\"isNum\":{\"value\":\"1\"}}", sco2ObjStr2)
    }


    @Test
    fun `test b2`() {
        val sco2Obj = gson.fromJson(sco2Element, Score2::class.java)
        val gsonFac2 = GsonBuilder()
            .registerTypeAdapterFactory(GsonTypeNumberAdapterFactory())
            .create()
        val sco2ObjStr2 = gsonFac2.toJson(sco2Obj)

        assertEquals("{\"num\":1,\"isNum\":\"1\"}", sco2ObjStr2)
    }

    @Test
    fun `test c`() {
        val jsonObj = JsonObject()
        jsonObj.add("num", JsonPrimitive(1))
        val nObj = JsonObject()
        nObj.addProperty("value", "1")
        jsonObj.add("isNum", nObj)

        val sco2Obj = gson.fromJson(sco2Element, Score2::class.java)
        val sco2ObjTree = gsonFac.toJsonTree(sco2Obj)
        assertEquals(jsonObj, sco2ObjTree)
    }

    @Test()
    fun `test d`() {
        val sco2Obj = gson.fromJson(sco2Element, Score2::class.java)
        val sco2ObjTree = gson.toJsonTree(sco2Obj)
        val sco3Obj = gson.fromJson(sco2ObjTree, object: TypeToken<Score2>(){}.rawType)

    }

    @Test(expected = JsonSyntaxException::class)
    fun `test d3`() {
        val sco2Obj = gson.fromJson(sco2Element, Score2::class.java)
        val sco2ObjTree = gsonFac.toJson(sco2Obj)
        // Expecting number, got: BEGIN_OBJECT
        val sco3Obj = gsonFac.fromJson<Score2>(sco2ObjTree, object: TypeToken<Score2>(){}.rawType)
    }

    @Test(expected = com.google.gson.JsonSyntaxException::class)
    fun `test e`() {
        val sco2Obj = gson.fromJson(sco2Element, Score2::class.java)
        val sco2ObjTree = gsonFac.toJsonTree(sco2Obj)
        // Expecting number, got: BEGIN_OBJECT
        val sco3Obj = gsonFac.fromJson(sco2ObjTree, Score2::class.java)
    }

    @Test
    fun `test e2`() {
        val sco2Obj = gson.fromJson(sco2Element, Score2::class.java)
        val gsonFac2 = GsonBuilder()
            .registerTypeAdapterFactory(GsonTypeNumberAdapterFactory())
            .create()
        val sco2ObjTree = gsonFac2.toJsonTree(sco2Obj)
        val sco3Obj = gsonFac2.fromJson(sco2ObjTree, Score2::class.java)
        assertEquals(sco2Obj.isNum, sco3Obj.isNum)
    }
}