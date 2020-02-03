package com.technews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.technews.model.Noticia
import com.technews.repository.NoticiaRepository
import com.technews.repository.Resource

class ListaNoticiasViewModel(private val repository: NoticiaRepository) : ViewModel() {

    fun buscaTodos(): LiveData<Resource<List<Noticia>?>> {
        return repository.buscaTodos()
    }
}