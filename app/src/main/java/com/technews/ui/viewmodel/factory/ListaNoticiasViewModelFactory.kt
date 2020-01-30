package com.technews.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.technews.repository.NoticiaRepository
import com.technews.ui.viewmodel.ListaNoticiasViewModel

class ListaNoticiasViewModelFactory(private val repository: NoticiaRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListaNoticiasViewModel(repository) as T
    }
}