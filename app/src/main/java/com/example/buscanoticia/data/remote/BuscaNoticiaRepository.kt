package com.example.buscanoticia.data.remote

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BuscaNoticiaRepository(
    val context: Context
) {
    val resposta = Retrofit.Builder()
        .baseUrl("https://apinoticias.tedk.com.br/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val noticiaService = resposta.create(BuscaNoticiaApi::class.java)


}