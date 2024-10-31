package ar.edu.unicen.seminario.ddl.data

import ar.edu.unicen.seminario.ddl.models.ActivityRecommendation
import javax.inject.Inject

class BoredRepository @Inject constructor(
    private val boredRemoteDataSource: BoredRemoteDataSource
) {

    suspend fun getRecommendation(
        participants: Int
    ): ActivityRecommendation?{
        return boredRemoteDataSource.getRecommendation(participants)
    }
}