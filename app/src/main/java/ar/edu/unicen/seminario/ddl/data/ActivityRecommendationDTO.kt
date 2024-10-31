package ar.edu.unicen.seminario.ddl.data

import ar.edu.unicen.seminario.ddl.models.ActivityRecommendation
import ar.edu.unicen.seminario.ddl.models.ActivityRecommendationInfo
import com.google.gson.annotations.SerializedName

class ActivityRecommendationDTO(
    @SerializedName("activity")
    val activity: String,
    @SerializedName("accessibility")
    val accessibility: Double,
    @SerializedName("type")
    val type: String,
    @SerializedName("participants")
    val participants: Int,
    @SerializedName("price")
    val price: Double,
    @SerializedName("link")
    val link: String,
    @SerializedName("key")
    val key: String
) {

    fun toActivityRecommendation(): ActivityRecommendation {
        return ActivityRecommendation(
            id = key,
            activity = activity,
            info = ActivityRecommendationInfo(
                type = type,
                participants = participants,
                price = price,
                accessibility = accessibility,
                link = link,
            )
        )
    }

}