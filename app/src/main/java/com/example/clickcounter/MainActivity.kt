package com.example.clickcounter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    lateinit var plusBtn: Button
    lateinit var minusBtn: Button
    lateinit var displayView: TextView

    var counter = 0;

    private fun isPrime(num: Int): Boolean {
        val n = abs(num);
        if (n < 2) return false;

        val s = floor(sqrt(n.toDouble())).toInt();
        val hasIntDivider = (2..n / 2).any {
            n % it == 0
        }

        return hasIntDivider.not();
    }

    private fun displayCounter() {
        displayView.text = counter.toString()

        val color = when {
            isPrime(counter) -> Color.WHITE
            counter in Int.MIN_VALUE..-1 -> Color.rgb(255, 0, 0)
            counter in 1..Int.MAX_VALUE -> Color.GREEN
            else -> Color.BLUE
        }

        displayView.setTextColor(color)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        plusBtn.setOnClickListener {
            counter++
            displayCounter()
        }

        minusBtn.setOnClickListener {
            counter--
            displayCounter()
        }

        displayView.setOnLongClickListener {
            counter = 0
            displayCounter()

            true
        }
    }

    fun init() {
        plusBtn = findViewById(R.id.plus)
        minusBtn = findViewById(R.id.minus)
        displayView = findViewById(R.id.display)

    }
}