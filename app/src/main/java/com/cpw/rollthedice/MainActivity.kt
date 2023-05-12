package com.cpw.rollthedice

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import kotlin.random.Random
import com.cpw.rollthedice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var rollNoise: MediaPlayer? = null
    var popNoise: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dice.setOnClickListener { rollDice() }
    }

    private fun popUp(num: Int) {
        Handler(mainLooper).postDelayed(Runnable {
            Popup(num).apply {
                listener = object : Popup.Listener {
                    override fun onDialogButtonClicked() {
                        dismiss()
                    }
                }
            }
                .show(supportFragmentManager, "popupDialog")
        }, 400L)
    }

    private fun animateDice() {
        val roll = AnimationUtils.loadAnimation(this, R.anim.roll)
        binding.dice.startAnimation(roll)
    }

    private fun soundRoll() {
        if (rollNoise == null) {
            rollNoise = MediaPlayer.create(this, R.raw.roll)
            rollNoise!!.start()
        } else {
            rollNoise!!.stop()
            popNoise = null
            rollNoise = MediaPlayer.create(this, R.raw.roll)
            rollNoise!!.start()
        }
    }

    private fun soundPop() {
        if (popNoise == null) {
            popNoise = MediaPlayer.create(this, R.raw.boom)
            popNoise!!.start()
        } else {
            popNoise!!.stop()
            popNoise = null
            popNoise = MediaPlayer.create(this, R.raw.boom)
            popNoise!!.start()
        }
    }

    private fun rollDice() {
        val rng = Random.nextInt(1, 6)

        soundRoll()
        animateDice()
        when (rng) {
            1 -> binding.dice.setImageResource(R.drawable.one)
            2 -> binding.dice.setImageResource(R.drawable.two)
            3 -> binding.dice.setImageResource(R.drawable.three)
            4 -> binding.dice.setImageResource(R.drawable.four)
            5 -> binding.dice.setImageResource(R.drawable.five)
            6 -> binding.dice.setImageResource(R.drawable.six)
            else -> binding.dice.setImageResource(R.drawable.random)
        }
        soundPop()
        popUp(rng)
    }
}