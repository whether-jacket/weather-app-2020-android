package com.seljabali.templateapplication.utilities

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.json.JSONArray
import org.json.JSONObject
import java.util.ArrayList

object GsonUtil {

    @JvmStatic
    fun toJsonString(`object`: Any): String {
        val gson = GsonBuilder().create()
        return gson.toJson(`object`)
    }

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
        val gson = GsonBuilder().create()
        val jsonText = jsonObject.toString()
        val gsonJson = JsonParser().parse(jsonText).asJsonObject
        return gson.fromJson(gsonJson, clazz)
    }

    @JvmStatic
    fun <T : Any> getModelFromJson(jsonObject: JSONObject, clazz: Class<T>): T? {
        val gson = GsonBuilder().create()
        val jsonText = jsonObject.toString()
        val gsonJson = JsonParser().parse(jsonText).asJsonObject
        return gson.fromJson(gsonJson, clazz)
    }

    @JvmStatic
    fun <T : Any> getModelFromJsonString(jsonString: String, clazz: Class<T>): T {
        val gson = GsonBuilder().create()
        val gsonJson = JsonParser().parse(jsonString).asJsonObject
        return gson.fromJson(gsonJson, clazz)
    }

    @JvmStatic
    fun <T : Any> getModelClone(model: T, clazz: Class<T>): T {
        val gson = GsonBuilder().create()
        val gsonJson = JsonParser().parse(toJsonString(model)).asJsonObject
        return gson.fromJson(gsonJson, clazz)
    }
}