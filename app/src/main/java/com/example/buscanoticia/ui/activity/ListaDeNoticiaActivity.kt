package com.example.buscanoticia.ui.activity

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buscanoticia.data.remote.BuscaNoticiaRepository
import com.example.buscanoticia.databinding.ActivityListaDeNoticiaBinding
import com.example.buscanoticia.formatador.FormatadorDeEspacos
import com.example.buscanoticia.listeners.OnClickButton
import com.example.buscanoticia.model.BuscaNoticiaResponse
import com.example.buscanoticia.ui.adapter.ListaDeNoticiaAdapter
import java.time.format.DateTimeFormatter
import java.util.*


class ListaDeNoticiaActivity : AppCompatActivity() {


    private val binding by lazy {
        ActivityListaDeNoticiaBinding.inflate(layoutInflater)
    }
    var adapter = ListaDeNoticiaAdapter(this)
    val buscaNoticiaRepository = BuscaNoticiaRepository(this)
    var buscaData = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        buscaApi()
        configuraRecyclerView()
        carregaCalendario()

    }

    private fun carregaCalendario() {
        val calendario = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { datePicker, ano, mes, dia ->
            formataCalendario(mes, calendario, ano, dia)
        }

        mostraCalendario(datePicker, calendario)
    }

    private fun mostraCalendario(
        datePicker: DatePickerDialog.OnDateSetListener,
        calendario: Calendar
    ) {
        binding.buscaData.setOnClickListener {
            DatePickerDialog(
                this,
                datePicker,
                calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun formataCalendario(
        mes: Int,
        calendario: Calendar,
        ano: Int,
        dia: Int
    ) {
        val messs = mes + 1

        calendario.set(Calendar.YEAR, ano)
        calendario.set(Calendar.MONTH, messs)
        calendario.set(Calendar.DAY_OF_MONTH, dia)


        var diaa = "${dia}"
        var mess = "${messs}"
        if (dia < 10) {
            diaa = "0" + dia
        }
        if (mes < 10) {
            mess = "0" + messs
        }
        buscaData = "${dia}/${mes}/${ano}"

        binding.buscaData.setText(buscaData)
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
            val noticiaSemEspaco = FormatadorDeEspacos(noticia).tiraEspaco()
            buscaNoticiaRepository.buscaNews(listener, noticiaSemEspaco, buscaData)
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



