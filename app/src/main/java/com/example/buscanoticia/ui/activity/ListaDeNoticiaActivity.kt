package com.example.buscanoticia.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buscanoticia.data.remote.BuscaNoticiaRepository
import com.example.buscanoticia.databinding.ActivityListaDeNoticiaBinding
import com.example.buscanoticia.listeners.OnClickButton
import com.example.buscanoticia.model.BuscaNoticiaResponse
import com.example.buscanoticia.ui.adapter.ListaDeNoticiaAdapter

class ListaDeNoticiaActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityListaDeNoticiaBinding.inflate(layoutInflater)
    }
    var adapter = ListaDeNoticiaAdapter(this)
    val buscaNoticiaRepository = BuscaNoticiaRepository(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        buscaApi()

        configuraRecyclerView()


    }

    private fun configuraRecyclerView() {
        binding.rvListaDeNoticias.layoutManager = LinearLayoutManager(baseContext)
        binding.rvListaDeNoticias.adapter = adapter
        adapter.quandoClicaNoItem = {
            val i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(it.link))
            startActivity(i)
        }
    }

    private fun buscaApi() {

        binding.pesquisar.setOnClickListener {
            val noticia = binding.buscaNotCia.text.toString()
            val data = binding.buscaData.text.toString()
            buscaNoticiaRepository.buscaNews(listener, noticia, data)
        }
    }

    val listener = object: OnClickButton {
        override fun onResponse(resposta: BuscaNoticiaResponse?) {
            if(resposta == null){
                Toast.makeText(baseContext, "Resposta nula", Toast.LENGTH_SHORT).show()
                return
            }
            resposta?.let { adapter.atualiza(it.list) }

        }

        override fun onError(mensagem: String?) {
            Toast.makeText(baseContext, "Um Erro aconteceu", Toast.LENGTH_SHORT).show()
        }

    }
}