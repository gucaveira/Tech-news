package com.technews.di.modules

import androidx.room.Room
import com.technews.database.AppDatabase
import com.technews.database.dao.NoticiaDAO
import com.technews.repository.NoticiaRepository
import com.technews.retrofit.webclient.NoticiaWebClient
import com.technews.ui.viewmodel.FormularioNoticiaViewModel
import com.technews.ui.viewmodel.ListaNoticiasViewModel
import com.technews.ui.viewmodel.VisualizaNoticiaViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val NOME_BANCO_DE_DADOS = "news.db"

val appModules = module {
    single<AppDatabase> {
        Room.databaseBuilder(get(), AppDatabase::class.java, NOME_BANCO_DE_DADOS).build()
    }

    single<NoticiaDAO> {
        get<AppDatabase>().noticiaDAO
    }

    single<NoticiaWebClient> {
        NoticiaWebClient()
    }

    single<NoticiaRepository> {
        NoticiaRepository(get(), get())
    }

    viewModel<ListaNoticiasViewModel> {
        ListaNoticiasViewModel(get())
    }

    viewModel<VisualizaNoticiaViewModel> { (id: Long) ->
        VisualizaNoticiaViewModel(id, get())
    }

    viewModel<FormularioNoticiaViewModel> {
        FormularioNoticiaViewModel(get())
    }
}