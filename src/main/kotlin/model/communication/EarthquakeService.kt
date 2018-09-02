package model.communication

import CommonValues.DEFAULT_FORMAT
import CommonValues.DEFAULT_ORDERING
import CommonValues.DEFAULT_RADIUS
import CommonValues.PATH
import model.entities.RawQuakes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EarthquakeService {
    @GET(PATH)
    fun getEarthQuakes(
            @Query("format") format: String = DEFAULT_FORMAT,
            @Query("latitude") latitude: Int,
            @Query("longitude") longitude: Int,
            @Query("maxradiuskm") radius: Int = DEFAULT_RADIUS,
            @Query("orderby") orderedBy: String = DEFAULT_ORDERING): Call<RawQuakes>
}