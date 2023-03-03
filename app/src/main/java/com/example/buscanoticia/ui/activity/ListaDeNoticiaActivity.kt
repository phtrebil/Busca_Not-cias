package com.example.buscanoticia.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.buscanoticia.R
import com.example.buscanoticia.databinding.ActivityListaDeNoticiaBinding
import com.example.buscanoticia.ui.adapter.ListaDeNoticiaAdapter

class ListaDeNoticiaActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityListaDeNoticiaBinding.inflate(layoutInflater)
    }
    var adapter = ListaDeNoticiaAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}