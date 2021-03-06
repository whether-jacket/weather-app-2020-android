package com.seljabali.core.utilities

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.json.JSONArray
import org.json.JSONObject
import java.util.ArrayList

object GsonUtil {

    @JvmStatic
    fun toJsonString(`object`: Any): String = GsonBuilder().create().toJson(`object`)

    @JvmStatic
    fun <T : Any> getModelsFromJsonArray(args: JSONArray,clazz: Class<T>): List<T> {
        val parsedModels = ArrayList<T>()
        try {
            for (i in 0 until args.length()) {
                val jsonObject = args.getJSONObject(i)
                val model = getModelFromJson(jsonObject, clazz)
                if (model != null) {
                    parsedModels.add(model)
                }
            }
        } catch (e: Exception) {
        }
        return parsedModels
    }

    @JvmStatic
    fun <T : Any> getModelFromJson(jsonObject: JsonObject, clazz: Class<T>): T {
        val jsonText = jsonObject.toString()
        val gsonJson = JsonParser().parse(jsonText).asJsonObject
        return GsonBuilder().create().fromJson(gsonJson, clazz)
    }

    @JvmStatic
    fun <T : Any> getModelFromJson(jsonObject: JSONObject, clazz: Class<T>): T? {
        val jsonText = jsonObject.toString()
        val gsonJson = JsonParser().parse(jsonText).asJsonObject
        return GsonBuilder().create().fromJson(gsonJson, clazz)
    }

    @JvmStatic
    fun <T : Any> getModelFromString(jsonString: String, clazz: Class<T>): T {
        val gsonJson = JsonParser().parse(jsonString).asJsonObject
        return GsonBuilder().create().fromJson(gsonJson, clazz)
    }

    @JvmStatic
    fun <T : Any> getModelClone(model: T, clazz: Class<T>): T {
        val gsonJson = JsonParser().parse(toJsonString(model)).asJsonObject
        return GsonBuilder().create().fromJson(gsonJson, clazz)
    }

}