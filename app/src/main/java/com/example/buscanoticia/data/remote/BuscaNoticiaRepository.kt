package com.example.buscanoticia.data.remote

import android.content.Context
import android.widget.Toast
import com.example.buscanoticia.listeners.OnClickButton
import com.example.buscanoticia.model.BuscaNoticiaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BuscaNoticiaRepository(
    val context: Context
) {
    val resposta = Retrofit.Builder()
        .baseUrl("https://apinoticias.tedk.com.br/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun buscaNews(listener: OnClickButton, assuntoNoticia: String?, dataNoticia: String?){
        val noticiaService = resposta.create(BuscaNoticiaApi::class.java)
        val call: Call<BuscaNoticiaResponse> = noticiaService.buscaNoticia(assuntoNoticia, dataNoticia)
        call.enqueue(object: Callback<BuscaNoticiaResponse>{
            override fun onResponse(
                call: Call<BuscaNoticiaResponse>,
                response: Response<BuscaNoticiaResponse>
            ) {
                if(!response.isSuccessful){
                    Toast.makeText(context, "Notícia não localizada", Toast.LENGTH_SHORT).show()
                    return
                }
                listener.onResponse(response.body())
            }

            override fun onFailure(call: Call<BuscaNoticiaResponse>, t: Throwable) {
                listener.onError(t.message)
            }

        })
    }


}