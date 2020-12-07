package kt.parser

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

val num: Number = 1


class Score {
    val num: Int = 1
    val isNum: Int = 1
}

class Score2 {
    var num: Int = 1
    var isNum: Number = 1
}

fun main() {
    val gson = Gson()

    val numStr = gson.toJson(num)
    println(numStr)

    val numTree = gson.toJsonTree(num)
    println(numTree)

    val sco = Score()
    val scoStr = gson.toJson(sco)
    println(scoStr)

    val scoTree = gson.toJsonTree(sco)
    println(scoTree)

    val sco2 = Score()
    val scoStr2 = gson.toJson(sco2)
    println(scoStr2)

    val scoTree2 = gson.toJsonTree(sco2)
    println(scoTree2)

    val sco2Obj = gson.fromJson(scoTree2, Score2::class.java)

    val sco2ObjTree = gson.toJsonTree(sco2Obj)
    println(sco2ObjTree)

    val gsonFac = GsonBuilder()
        .registerTypeAdapterFactory(GsonTypeAdapterFactory())
        .create()
//    val sco2ObjStr2 = gsonFac.toJson(sco2Obj)
//    println(sco2ObjStr2)

    val sco2ObjTree2 = gsonFac.toJson(sco2Obj)
    println(sco2ObjTree2)

    val sco3Obj = gsonFac.fromJson(sco2ObjTree2, object: TypeToken<Score2>(){}.rawType)

    val sco4Obj = gsonFac.fromJson(sco2ObjTree2, Score2::class.java)

}