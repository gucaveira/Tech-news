package com.technews.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.technews.model.Noticia
import com.technews.repository.NoticiaRepository

class ListaNoticiasViewModel(
    private val repository: NoticiaRepository
) : ViewModel() {

    init {
        Log.i("viewModel", "criando viewmodel")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("viewModel", "destruindo viewmodel")
    }

    fun buscaTodos(
        quandoSucesso: (noticiasNovas: List<Noticia>) -> Unit,
        quandoFalha: (erro: String?) -> Unit
    ) {
        repository.buscaTodos(quandoSucesso, quandoFalha)
    }
}