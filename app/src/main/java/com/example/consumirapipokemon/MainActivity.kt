package com.example.consumirapipokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.consumirapipokemon.data.model.PokemonResult
import com.example.consumirapipokemon.data.service.IAccesoRemoto
import com.example.consumirapipokemon.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var adapter:PokemonAdapter

    
    private var listaPokemones=mutableListOf<PokemonResult>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycle()
        consultarTodos()
        

        binding.editTextSearch.addTextChangedListener {filtro->
            
            var cambio=listaPokemones.filter { pokemon ->
                pokemon.name.contains(filtro.toString(), ignoreCase = true)
            }
            
        adapter.actualizarLista(cambio)
        }
    }

    
    private fun initRecycle(){
        adapter=PokemonAdapter(listaPokemones)
        binding.lista.layoutManager=LinearLayoutManager(this)
        binding.lista.adapter=adapter
    }

    //obtengo una instancia de retrofit con la url de pokemon api
    private fun getRetrofit():Retrofit{
        var url="https://pokeapi.co/api/"
        return  Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
//esta funcion consume la api
    private fun consultarTodos(){
    
        CoroutineScope(Dispatchers.IO).launch {
            
            val respuesta=getRetrofit().create(IAccesoRemoto::class.java).GetPokemons()
            val pokemones=respuesta
            
            runOnUiThread(){

                    var lista:List<PokemonResult> = emptyList()
                //mapeo la lista y solo capturo la lista de result que contiene el name
                    pokemones?.results?.let{ pokemonlista->
                        lista = pokemonlista.mapIndexed { index, pokemon ->

                            
                            val urlImage = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${index + 1}.png"
                            PokemonResult(urlImage, pokemon.name)
                        }
                    }
                
                    listaPokemones.clear()
                
                    listaPokemones.addAll(lista)
                
                    adapter.notifyDataSetChanged()

            }

        }
    }
}