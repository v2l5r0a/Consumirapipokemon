package com.example.consumirapipokemon.data

import com.example.consumirapipokemon.data.model.Pokemon
import com.google.gson.annotations.SerializedName
import java.util.concurrent.CountDownLatch


data class PokemonRespuesta(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next:String,
    @SerializedName("previous")
    val previous:String?,
    @SerializedName("results")
    val results:List<Pokemon>
)
