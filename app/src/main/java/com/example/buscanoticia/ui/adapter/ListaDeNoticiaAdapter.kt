package com.example.buscanoticia.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.buscanoticia.databinding.ItemListaDeNoticiasBinding
import com.example.buscanoticia.model.Noticias

class ListaDeNoticiaAdapter(
    val context: Context,
    noticias: List<Noticias> = emptyList()
): RecyclerView.Adapter<NoticiaViewHolder>() {

    val noticias = noticias.toMutableList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiaViewHolder {
        val inflater = LayoutInflater.from(context)
        return NoticiaViewHolder(ItemListaDeNoticiasBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = noticias.size

    override fun onBindViewHolder(holder: NoticiaViewHolder, position: Int) {
        val noticia = noticias[position]
        holder.vincula(noticia)
    }

    fun atualiza(noticias: List<Noticias>) {
        this.noticias.clear()
        this.noticias.addAll(noticias)
        notifyDataSetChanged()
    }
}