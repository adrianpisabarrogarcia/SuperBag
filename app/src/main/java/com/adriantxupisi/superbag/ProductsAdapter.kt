package com.adriantxupisi.superbag

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductsAdapter(private val products: ArrayList<Product>) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    //Cuando el recyclerView necesita una vista para mostrar un elemento,
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_products , parent, false)
        return ViewHolder(view)
    }

    //Cuando actualizamos el recyclerView, se llama a este método
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    //Devuelve el número de elementos que contiene el recyclerView
    override fun getItemCount(): Int = products.size

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val name = itemView.findViewById<TextView>(R.id.productNameTextView)
        private val quantity = itemView.findViewById<TextView>(R.id.productQuantityTextView)
        private val description = itemView.findViewById<TextView>(R.id.productDescriptionTextView)

        fun bind(product: Product) {
            name.text = product.name.toString()
            quantity.text = product.quantity.toString()
            description.text = product.description.toString()
        }

    }



}
