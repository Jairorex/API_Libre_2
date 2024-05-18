package com.sts.api_libre_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AnimeAdapter(private val anime:List<anime>, private val onItemClick:(anime)->Unit):
    RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>(){

    inner class AnimeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //Definir campos
        private val textName=itemView.findViewById<TextView>(R.id.txtAnime)
        private val Japo=itemView.findViewById<TextView>(R.id.txtAnimeJ)

        private val imgP=itemView.findViewById<ImageView>(R.id.poster)



        fun bind(anime: anime){
            textName.text=anime.Nombre
            Japo.text=anime.japones


            Glide.with(itemView.context)
                .load(anime.Poster.jpg.urljpg)
                .into(imgP)

            itemView.setOnClickListener{
                onItemClick(anime)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.listaanime, parent,false)
        return AnimeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return anime.size
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val agent=anime[position]
        holder.bind(agent)
    }
}