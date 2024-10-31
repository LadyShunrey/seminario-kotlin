package ar.edu.unicen.seminario.ddl.data

import ar.edu.unicen.seminario.ddl.models.ActivityRecommendation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BoredRemoteDataSource @Inject constructor(
    private val boredApi: BoredApi
) {

    suspend fun getRecommendation(
        participants: Int
    ): ActivityRecommendation?{
        return withContext(Dispatchers.IO){
            try{
                val response = boredApi.getRecommendation(participants)
                val activityRecommendation = response.body()?.toActivityRecommendation()
                return@withContext activityRecommendation
            }catch (e: Exception){
                e.printStackTrace()
                return@withContext null
            }
        }
    }
}