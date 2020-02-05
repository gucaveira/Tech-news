package com.technews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.technews.repository.NoticiaRepository
import com.technews.repository.Resource

class VisualizaNoticiaViewModel(
    private val id: Long, private val repository: NoticiaRepository
) : ViewModel() {

    private val noticiaEncontrada = buscaPorId()

    fun buscaPorId() = repository.buscaPorId(id)

    fun remove(): LiveData<Resource<Void?>> {
        return noticiaEncontrada.value?.run {
            repository.remove(this)
        } ?: MutableLiveData<Resource<Void?>>().also {
            it.value = Resource(null, "Notícia não encontrada")
        }
    }
}
