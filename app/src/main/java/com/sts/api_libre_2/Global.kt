package com.sts.api_libre_2

import com.google.gson.annotations.SerializedName


data class anime(
    @SerializedName("title") val Nombre:String,
    @SerializedName("synopsis") val Descripcion:String,
    @SerializedName("images") val Poster:Img,
    @SerializedName("title_japanese") val japones:String,
    @SerializedName("episodes") val episodios:String,
    @SerializedName("status") val estado:String,
    @SerializedName("year") val anio:String





)
data class Img(@SerializedName("jpg")val jpg: jpg,@SerializedName("webp") val webp:webp)
data class jpg(@SerializedName("image_url")val urljpg:String)
data class webp(@SerializedName("image_url")val urlwebp:String)
data class AnimeResponse(
    @SerializedName("data") val data: List<anime>
)