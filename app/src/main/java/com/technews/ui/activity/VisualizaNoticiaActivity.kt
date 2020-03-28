package com.technews.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.technews.R
import com.technews.ui.fragment.VisualizaNoticiaFragment

private const val TITULO_APPBAR = "Notícia"

class VisualizaNoticiaActivity : AppCompatActivity() {

    private val noticiaId: Long by lazy {
        intent.getLongExtra(NOTICIA_ID_CHAVE, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visualiza_noticia)
        title = TITULO_APPBAR
        val transacao = supportFragmentManager.beginTransaction()
        val fragment = VisualizaNoticiaFragment()
        val bundle = Bundle()
        bundle.putLong(NOTICIA_ID_CHAVE, noticiaId)
        fragment.arguments = bundle
        transacao.add(R.id.activity_visualiza_noticia_container, fragment)
        transacao.commit()
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is VisualizaNoticiaFragment) {
            fragment.quandoFinalizaTela = { finish() }
            fragment.quandoSelecionaMenuEdicao = { abreFormularioEdicao() }
        }
    }

    private fun abreFormularioEdicao() {
        val intent = Intent(this, FormularioNoticiaActivity::class.java)
        intent.putExtra(NOTICIA_ID_CHAVE, noticiaId)
        startActivity(intent)
    }
}
