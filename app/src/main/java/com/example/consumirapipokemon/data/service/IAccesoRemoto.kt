package com.example.consumirapipokemon.data.service

import com.example.consumirapipokemon.data.PokemonRespuesta
import com.example.consumirapipokemon.data.RespuestaLista
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface IAccesoRemoto {
    //esto hace que cosnuma el endpoint para obtener lospoquemones y tengo un objeto
    ///pokemon respuesta que tiene dentro una lista de result que es donde estan los nombres
    //de los pokemosnes
    @GET("v2/pokemon")
    suspend fun GetPokemons(): PokemonRespuesta
}