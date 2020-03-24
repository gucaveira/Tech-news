package com.technews.di.modules

import androidx.room.Room
import com.technews.database.AppDatabase
import org.koin.dsl.module

private const val NOME_BANCO_DE_DADOS = "news.db"

val appModules = module {
    single<AppDatabase> {
        Room.databaseBuilder(get(), AppDatabase::class.java, NOME_BANCO_DE_DADOS).build()
    }
}