package model.entities

import com.google.gson.annotations.SerializedName
import java.util.*

data class SingleQuake(
        @SerializedName("type") val type: String,
        @SerializedName("properties") val details: QuakeDetails,
        @SerializedName("geometry") val geometry: QuakeGeometry,
        @SerializedName("id") val id: String)

data class RawQuakes(@SerializedName("type") val quakeType: String,
                     @SerializedName("metadata") val quakesMetaData: QuakesMetaData,
                     @SerializedName("features") val quakes: List<SingleQuake>)

data class QuakeDetails(@SerializedName("mag") val magnitude: Double?,
                        @SerializedName("place") val location: String?,
                        @SerializedName("time") val timestamp: Long?,
                        @SerializedName("updated") val updateTimeStamp: Long?,
                        @SerializedName("tz") val timezone: Int?,
                        @SerializedName("url") val url: String?,
                        @SerializedName("detail") val detail: String?,
                        @SerializedName("felt") val felt: String?,
                        @SerializedName("cdi") val cdi: String?,
                        @SerializedName("mmi") val mmi: String?,
                        @SerializedName("alert") val alert: String?,
                        @SerializedName("status") val status: String?,
                        @SerializedName("tsunami") val tsunami: Int?,
                        @SerializedName("sig") val sig: Int?,
                        @SerializedName("net") val net: String?,
                        @SerializedName("code") val code: String?,
                        @SerializedName("ids") val ids: String?,
                        @SerializedName("sources") val sources: String?,
                        @SerializedName("types") val types: String?,
                        @SerializedName("nst") val nst: Int?,
                        @SerializedName("dmin") val dmin: Double?,
                        @SerializedName("rms") val rms: Double?,
                        @SerializedName("gap") val gap: Double?,
                        @SerializedName("mapType") val magType: String?,
                        @SerializedName("type") val type: String?,
                        @SerializedName("title") val title: String?)

data class QuakeGeometry(val type: String,
                         val coordinates: ArrayList<Double>)

data class QuakesMetaData(val generated: Long,
                          val url: String,
                          val title: String,
                          val status: Short,
                          val api: String,
                          val count: Int)

data class ProcessedOutput(val title: String? = "Empty",
                           val distanceFromEpicenter:Int? = 0){

    override fun toString(): String {
        return StringBuilder()
                .append(title)
                .append(" || ")
                .append(distanceFromEpicenter)
                .append("km")
                .toString()
    }
}