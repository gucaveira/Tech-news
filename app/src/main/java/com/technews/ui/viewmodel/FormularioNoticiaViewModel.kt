package com.technews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.technews.model.Noticia
import com.technews.repository.NoticiaRepository
import com.technews.repository.Resource

class FormularioNoticiaViewModel(private val repository: NoticiaRepository) : ViewModel() {

    fun salva(noticia: Noticia): LiveData<Resource<Void?>> {
        return if (noticia.id > 0) {
            repository.edita(noticia)
        } else {
            repository.salva(noticia)
        }
    }

    fun buscaPorId(id: Long) = repository.buscaPorId(id)
}