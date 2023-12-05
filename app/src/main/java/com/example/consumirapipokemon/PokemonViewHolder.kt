package com.example.consumirapipokemon

import android.text.Editable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.consumirapipokemon.data.model.PokemonResult
import com.example.consumirapipokemon.databinding.ItemPokemonBinding
import com.squareup.picasso.Picasso

class PokemonViewHolder(view: View):RecyclerView.ViewHolder(view) {
    private val binding=ItemPokemonBinding.bind(view)
    
    fun bind(pokemon:PokemonResult){
        //utilizo picasso para pode cargar la imagen desde una url
        Picasso.get().load(pokemon.url).into(binding.image)
        //agrego el nombre del pokemon
        binding.nombre.text= Editable.Factory.getInstance().newEditable(pokemon.name)
    }
}