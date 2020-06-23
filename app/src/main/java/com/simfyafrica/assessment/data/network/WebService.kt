package com.simfyafrica.assessment.data.network

import com.simfyafrica.assessment.data.model.Cat
import io.reactivex.Single
import com.simfyafrica.assessment.data.model.CatsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {
    @GET("search?limit=100&page=100")
    fun getJSONCats(): Single<List<Cat>>

    @GET("{id}")
    fun getAJSONCat(@Path("id") id: String): Single<Cat>

    @GET("https://api.thecatapi.com/v1/images/search?limit=100&page=100&order=Desc")
    fun getXMLCats(): Single<Response<CatsResponse>>
}