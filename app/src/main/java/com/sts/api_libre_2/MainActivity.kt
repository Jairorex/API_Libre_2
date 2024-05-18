package com.sts.api_libre_2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.sts.api_libre_2.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.rclAnime.layoutManager=LinearLayoutManager(this)
        fetchAnime()



    }
    private fun fetchAnime() {
        CoroutineScope(Dispatchers.IO).launch {
            Fuel.get("https://api.jikan.moe/v4/anime")
                .responseString { _, _, result ->
                    when (result) {
                        is Result.Failure -> {
                            runOnUiThread {
                                Toast.makeText(this@MainActivity, "Error fetching data", Toast.LENGTH_LONG).show()
                            }
                        }
                        is Result.Success -> {
                            val data = result.get()
                            val animeResponse = Gson().fromJson(data, AnimeResponse::class.java)


                            runOnUiThread {

                                binding.rclAnime.adapter = AnimeAdapter(animeResponse.data) { anime ->
                                    val intent = Intent(
                                        this@MainActivity,
                                        Lista_Anime::class.java
                                    ).apply {
                                        putExtra("nombre", anime.Nombre)
                                        putExtra("descripcion", anime.Descripcion)
                                        putExtra("poster", anime.Poster.jpg.urljpg)
                                        putExtra("anio",anime.anio)
                                        putExtra("japones",anime.japones)
                                        putExtra("episodio",anime.episodios)
                                        putExtra("estado",anime.estado)


                                    }
                                    startActivity(intent)
                                }
                            }
                        }
                    }
                }
        }
    }
}