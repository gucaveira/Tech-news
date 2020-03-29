package com.technews.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.technews.R
import com.technews.model.Noticia
import com.technews.ui.fragment.ListaNoticiasFragment
import com.technews.ui.fragment.VisualizaNoticiaFragment

private const val TITULO_APPBAR = "Notícias"

class NoticiasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noticias_container)
        title = TITULO_APPBAR
        val fragment = supportFragmentManager.beginTransaction()
        fragment.add(R.id.activity_noticias_container, ListaNoticiasFragment())
        fragment.commit()
    }

    override fun onAttachFragment(fragment: androidx.fragment.app.Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is ListaNoticiasFragment) {
            fragment.quandoNoticiaSeleciona = {
                abreVisualizadorNoticia(it)
            }
            fragment.quandoFabSalvaNoticiaClicado = {
                abreFormularioModoCriacao()
            }
        }
        if (fragment is VisualizaNoticiaFragment) {
            fragment.quandoFinalizaTela = { finish() }
            fragment.quandoSelecionaMenuEdicao = { noticiaSelecionada -> abreFormularioEdicao(noticiaSelecionada) }
        }
    }

    private fun abreFormularioModoCriacao() {
        val intent = Intent(this, FormularioNoticiaActivity::class.java)
        startActivity(intent)
    }

    private fun abreVisualizadorNoticia(noticia: Noticia) {
        val transacao = supportFragmentManager.beginTransaction()
        val fragment = VisualizaNoticiaFragment()
        val bundle = Bundle()
        bundle.putLong(NOTICIA_ID_CHAVE, noticia.id)
        fragment.arguments = bundle
        transacao.replace(R.id.activity_noticias_container, fragment)
        transacao.commit()
    }

    private fun abreFormularioEdicao(noticia: Noticia) {
        val intent = Intent(this, FormularioNoticiaActivity::class.java)
        intent.putExtra(NOTICIA_ID_CHAVE, noticia.id)
        startActivity(intent)
    }
}
