package com.sts.api_libre_2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.sts.api_libre_2.databinding.ActivityListaAnimeBinding

class Lista_Anime : AppCompatActivity() {
    private lateinit var binding: ActivityListaAnimeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityListaAnimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val Nombre=intent.getStringExtra("nombre")
        val Descrip=intent.getStringExtra("descripcion")
        val Img=intent.getStringExtra("poster")
        val Anio=intent.getStringExtra("anio")
        val Episodio=intent.getStringExtra("episodio")
        val Estado=intent.getStringExtra("estado")
        val Japones=intent.getStringExtra("japones")


        binding.txtNombre.text=Nombre
        binding.txtDescrip.text=Descrip
        binding.txtE.text=Episodio
        binding.txtestado.text=Estado
        binding.txtanio.text=Anio
        binding.txtNombreJ.text=Japones
        //binding.textView.text=Img
        Glide.with(this)
            .load(Img)
            .into(binding.poster)
    }
}