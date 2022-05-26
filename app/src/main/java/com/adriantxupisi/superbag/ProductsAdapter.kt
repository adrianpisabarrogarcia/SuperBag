package com.adriantxupisi.superbag

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ProductsAdapter(val products: List<Product>) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    //Cuando el recyclerView necesita una vista para mostrar un elemento,
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    //Cuando actualizamos el recyclerView, se llama a este método
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    //Devuelve el número de elementos que contiene el recyclerView
    override fun getItemCount(): Int {
        return products.size
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    }



}
