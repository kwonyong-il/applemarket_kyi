package com.example.applemarket

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.applemarket.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar
import java.lang.System.exit
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private var isLike = false
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        position = intent.getIntExtra(Constants.ITEM_INDEX, 0)
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

        isLike = item?.isLike == true

        binding.iconHeartDetail.setImageResource(
            if (isLike) {
                R.drawable.heart_fill_icon
            } else {
                R.drawable.heart_icon
            }
        )

        binding.backImg.setOnClickListener {
            exit()
        }

        binding.iconHeartDetail.setOnClickListener {
            if (!isLike) {
                binding.iconHeartDetail.setImageResource(R.drawable.heart_fill_icon)
                Snackbar.make(binding.constraintLayout, "관심 목록에 추가되었습니다.", Snackbar.LENGTH_SHORT)
                    .show()
                isLike = true
            } else {
                binding.iconHeartDetail.setImageResource(R.drawable.heart_icon)
                isLike = false
            }
        }
    }
    override fun onBackPressed() {
        exit()
    }

    fun exit() {
        val resultIntent = Intent()
        resultIntent.putExtra("itemIndex", position)
        resultIntent.putExtra("islike", isLike)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}

