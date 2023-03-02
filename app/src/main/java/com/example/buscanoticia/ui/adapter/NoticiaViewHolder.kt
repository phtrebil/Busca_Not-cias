package com.example.buscanoticia.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.buscanoticia.databinding.ItemListaDeNoticiasBinding
import com.example.buscanoticia.model.Noticias

class NoticiaViewHolder(
    private val binding: ItemListaDeNoticiasBinding
): RecyclerView.ViewHolder(binding.root) {

    private lateinit var noticia: Noticias

    fun vincula(noticia: Noticias) {
        this.noticia = noticia
        binding.manchete.text = noticia.title
        binding.dataNoticia.text = noticia.date
    }

}
