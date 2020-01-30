package com.technews.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel

class ListaNoticiasViewModel : ViewModel() {

    init {
        Log.i("viewModel", "criando viewmodel")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("viewModel", "destruindo viewmodel")
    }
}