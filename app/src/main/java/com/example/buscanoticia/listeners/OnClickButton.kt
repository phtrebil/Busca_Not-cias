package com.example.buscanoticia.listeners

import com.example.buscanoticia.model.BuscaNoticiaResponse

interface OnClickButton {

    fun onResponse(resposta: BuscaNoticiaResponse?)
    fun onError(mensagem: String?)

}
