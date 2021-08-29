package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculate.setOnClickListener { calculate() }
    }

    fun calculate() {
        val input = binding.momoMeat.text.toString()
        val kawaari_amount = input.toDoubleOrNull()
        if (kawaari_amount == null) {
            binding.amountResult.text = ""
            binding.calolieResult.text = ""
            binding.proteinResult.text = ""
            return
        }
        var kawanashi_amount = kawaari_amount * 0.9
        var protein = kawaari_amount / 100 * 18.8
        val original_calorie = kawanashi_amount / 100 * 116
        val selectedId = binding.HowToEat.checkedRadioButtonId
        val add_calorie = when(selectedId) {
            R.id.radio_tomato -> 80
            else -> 0
        }
        var sum_calorie = original_calorie + add_calorie
        val roundUp = binding.Switch.isChecked
        if(roundUp) {
            kawanashi_amount = ceil(kawanashi_amount)
            protein = ceil(protein)
            sum_calorie = ceil(sum_calorie)
        }

        val formatted_kawanashi_amount = NumberFormat.getInstance().format(kawanashi_amount)
        val formatted_protein = NumberFormat.getInstance().format(protein)
        val formatted_sum_calorie = NumberFormat.getInstance().format(sum_calorie)

        // 計算結果をstringリソースの%s部分にぶちこむ
        binding.amountResult.text = getString(R.string.amount, formatted_kawanashi_amount)
        binding.proteinResult.text = getString(R.string.protein, formatted_protein)
        binding.calolieResult.text = getString(R.string.calorie, formatted_sum_calorie)
    }
}