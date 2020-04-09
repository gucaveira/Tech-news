package com.technews.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.technews.R
import com.technews.model.Noticia
import com.technews.ui.activity.extensions.transacaofragment
import com.technews.ui.fragment.ListaNoticiasFragment
import com.technews.ui.fragment.VisualizaNoticiaFragment
import kotlinx.android.synthetic.main.activity_noticias.*

private const val TAG_FRAGMENT_VISUALIZA_NOTICIA = "visualizaNoticia"

class NoticiasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noticias)
        if (savedInstanceState == null) {
            abreListaNoticias()
        } else {
            supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_VISUALIZA_NOTICIA)
                ?.let { fragment ->
                    val argumentos = fragment.arguments
                    val novoFragment = VisualizaNoticiaFragment()
                    novoFragment.arguments = argumentos

                    transacaofragment {
                        remove(fragment)
                    }
                    supportFragmentManager.popBackStack()

                    transacaofragment {
                        val container =
                            if (activity_noticias_container_secundario != null) {
                                R.id.activity_noticias_container_secundario
                            } else {
                                addToBackStack(null)
                                R.id.activity_noticias_container_primario
                            }
                        replace(container, novoFragment, TAG_FRAGMENT_VISUALIZA_NOTICIA)
                    }
                }
        }
    }

    private fun abreListaNoticias() {
        transacaofragment {
            replace(R.id.activity_noticias_container_primario, ListaNoticiasFragment())
        }
    }

    override fun onAttachFragment(fragment: androidx.fragment.app.Fragment) {
        super.onAttachFragment(fragment)
        when (fragment) {
            is ListaNoticiasFragment -> {
                configuraListaNoticias(fragment)
            }
            is VisualizaNoticiaFragment -> {
                configuraVisualizaNoticia(fragment)
            }
        }
    }

    private fun configuraVisualizaNoticia(fragment: VisualizaNoticiaFragment) {
        fragment.quandoFinalizaTela = this::finish
        fragment.quandoSelecionaMenuEdicao = this::abreFormularioEdicao
    }

    private fun configuraListaNoticias(fragment: ListaNoticiasFragment) {
        fragment.quandoNoticiaSeleciona = this::abreVisualizadorNoticia
        fragment.quandoFabSalvaNoticiaClicado = this::abreFormularioModoCriacao
    }

    private fun abreFormularioModoCriacao() {
        val intent = Intent(this, FormularioNoticiaActivity::class.java)
        startActivity(intent)
    }

    private fun abreVisualizadorNoticia(noticia: Noticia) {
        val fragment = VisualizaNoticiaFragment()
        val bundle = Bundle()
        bundle.putLong(NOTICIA_ID_CHAVE, noticia.id)
        fragment.arguments = bundle
        transacaofragment {
            val container =
                if (activity_noticias_container_secundario != null) {
                    R.id.activity_noticias_container_secundario
                } else {
                    addToBackStack(null)
                    R.id.activity_noticias_container_primario
                }
            replace(container, fragment, TAG_FRAGMENT_VISUALIZA_NOTICIA)
        }
    }

    private fun abreFormularioEdicao(noticia: Noticia) {
        val intent = Intent(this, FormularioNoticiaActivity::class.java)
        intent.putExtra(NOTICIA_ID_CHAVE, noticia.id)
        startActivity(intent)
    }
}
