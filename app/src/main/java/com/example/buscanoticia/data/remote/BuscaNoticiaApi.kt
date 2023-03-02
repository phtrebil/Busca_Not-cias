package com.example.buscanoticia.data.remote

import com.example.buscanoticia.model.BuscaNoticiaResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BuscaNoticiaApi {

    @GET("api/")
    fun buscaNoticia(
        @Query("q") noticia: String?,
        @Query("date") data: String
    ): Call<BuscaNoticiaResponse>

}