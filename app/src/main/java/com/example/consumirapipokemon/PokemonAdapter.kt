package com.example.consumirapipokemon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.consumirapipokemon.data.model.PokemonResult

class PokemonAdapter(private var listaPokemones:List<PokemonResult>): RecyclerView.Adapter<PokemonViewHolder>() {
    
    override fun getItemCount(): Int=listaPokemones.size
    
    override fun onBindViewHolder(holder:PokemonViewHolder,position:Int) {
        val item=listaPokemones[position]
        holder.bind(item)
    }

    
    override fun onCreateViewHolder(parent:ViewGroup,viewType:Int):PokemonViewHolder {
       val layout=LayoutInflater.from(parent.context)
        return PokemonViewHolder(layout.inflate(R.layout.item_pokemon,parent,false))
    }


    fun actualizarLista(lista:List<PokemonResult>){
        this.listaPokemones=lista
        notifyDataSetChanged()
    }
}