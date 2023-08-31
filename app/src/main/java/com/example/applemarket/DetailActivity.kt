package com.example.applemarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.applemarket.databinding.ActivityDetailBinding
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item = intent.getParcelableExtra(Constants.ITEM_OBJECT) as MyItem?
        if (item != null) {
            binding.itemImgDetail.setImageResource(item.Img)
            binding.sellerTextDetail.text = item.Seller
            binding.addressTextDetail.text = item.Address
            binding.titleTextDetail.text = item.Title
            binding.introdutionTextDetail.text =
                item.Introduction.replace("\\n", System.getProperty("line.separator"))
            binding.priceTextDetail.text = DecimalFormat("#,###").format(item.Price) + "원"
        }
        binding.backImg.setOnClickListener {
            finish()
        }
    }
}