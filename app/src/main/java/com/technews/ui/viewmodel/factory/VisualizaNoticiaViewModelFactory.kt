package com.technews.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.technews.repository.NoticiaRepository
import com.technews.ui.viewmodel.VisualizaNoticiaViewModel

class VisualizaNoticiaViewModelFactory(
    private val id: Long,
    private val repository: NoticiaRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VisualizaNoticiaViewModel(id, repository) as T
    }
}
