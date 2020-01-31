package com.technews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.technews.model.Noticia
import com.technews.repository.NoticiaRepository

class ListaNoticiasViewModel(
    private val repository: NoticiaRepository
) : ViewModel() {

    fun buscaTodos(): LiveData<List<Noticia>> {
        return repository.buscaTodos()
    }
}