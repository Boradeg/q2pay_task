package com.example.apiretrofit.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.apiretrofit.R
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        supportActionBar?.hide()

        var img: ImageView = findViewById(R.id.userImage)
        var arrow: ImageView = findViewById(R.id.arrow)
        var name: TextView = findViewById(R.id.name)
        var desc: TextView = findViewById(R.id.desc)
        var brand: TextView = findViewById(R.id.brand)
        var category: TextView = findViewById(R.id.category)
        var prize: TextView = findViewById(R.id.prize)
        var rating: TextView = findViewById(R.id.rating)
        arrow.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }


        // Retrieve extras from Intent
        name.text = intent.getStringExtra("title")
        desc.text = intent.getStringExtra("description")
        brand.text = intent.getStringExtra("brand")
        rating.text = intent.getStringExtra("rating")
        prize.text = intent.getStringExtra("price") // Default value is 0.0
        category.text = intent.getStringExtra("rating") // Default value is 0.0
        val thumbnail = intent.getStringExtra("thumbnail")
        Picasso.get().load(thumbnail).into(img)
    }
}