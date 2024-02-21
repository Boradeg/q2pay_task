package com.example.apiretrofit.Adapter

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.apiretrofit.Activity.DetailActivity
import com.example.apiretrofit.DataClass.Product
import com.example.apiretrofit.R
import com.squareup.picasso.Picasso

class MyAdapter(val context: Activity, val productArrayList: List<Product>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.productTitle)
        var image: ImageView = itemView.findViewById(R.id.productImage)
        var prize: Button = itemView.findViewById(R.id.product_prize)
        var desc: TextView = itemView.findViewById(R.id.productDescription)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedItem = productArrayList[position]
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra("title", clickedItem.title)
                    intent.putExtra("description", clickedItem.description)
                    intent.putExtra("brand", clickedItem.brand)
                    intent.putExtra("id", clickedItem.id.toString())
                    intent.putExtra("price", clickedItem.price.toString())
                    intent.putExtra("category", clickedItem.category)
                    intent.putExtra("discountPercentage", clickedItem.discountPercentage.toString())
                    intent.putExtra("rating", clickedItem.rating.toString())
                    intent.putExtra("stock", clickedItem.stock.toString())
                    intent.putExtra("thumbnail", clickedItem.thumbnail)
                    Log.d("FirstActivity", "Prize value: ${clickedItem.price}")

                    // Start the new activity
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productArrayList.size
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        val slide_in = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left)
        viewToAnimate.startAnimation(slide_in)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = productArrayList[position]
        holder.title.text = currentItem.title
        holder.desc.text = currentItem.description
        val priz = currentItem.price.toString()
        holder.prize.text = "$ $priz"
        Picasso.get().load(currentItem.thumbnail).into(holder.image)
        setAnimation(holder.itemView, position)
    }
}