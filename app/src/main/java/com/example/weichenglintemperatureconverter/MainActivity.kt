package com.example.weichenglintemperatureconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fahrenheitText: TextView = findViewById(R.id.fahrenheit_text)
        val celsiusText: TextView = findViewById(R.id.celsius_text)
        val fahrenheitSeekBar: SeekBar = findViewById(R.id.fahrenheit_seek_bar)
        val celsiusSeekBar: SeekBar = findViewById(R.id.celsius_seek_bar)
        val interestingText: TextView = findViewById(R.id.interesting_text)

        fahrenheitSeekBar.max = 212
        celsiusSeekBar.max = 100

        fahrenheitSeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(fahrenheitSeekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (!fromUser) {
                    return
                }

                if (progress < 32) {
                    fahrenheitSeekBar.progress = 32
                    fahrenheitText.setText("Fahrenheit: " + 32 + "\u2109")
                    celsiusText.setText("Celsius: " + 0 + "\u2103")
                    return
                }

                val celsius = fahrenheitToCelsius(progress)
                if (celsius <= 20) {
                    interestingText.setText(R.string.interesting_message_one)
                } else {
                    interestingText.setText(R.string.interesting_message_two)
                }
                celsiusSeekBar.progress = celsius
                fahrenheitText.setText("Fahrenheit: " + progress + "\u2109")
                celsiusText.setText("Celsius: " + celsius + "\u2103")
            }
            override fun onStartTrackingTouch(fahrenheitSeekBar: SeekBar) {}
            override fun onStopTrackingTouch(fahrenheitSeekBar: SeekBar) {}
        })

        celsiusSeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(celsiusSeekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (!fromUser) {
                    return
                }
                if (progress <= 20) {
                    interestingText.setText(R.string.interesting_message_one)
                } else {
                    interestingText.setText(R.string.interesting_message_two)
                }

                val fahrenheit = celsiusToFahrenheit(progress)
                fahrenheitSeekBar.progress = fahrenheit
                fahrenheitText.setText("Fahrenheit: " + fahrenheit + "\u2109")
                celsiusText.setText("Celsius: " + progress + "\u2103")
            }
            override fun onStartTrackingTouch(celsiusSeekBar: SeekBar) {}
            override fun onStopTrackingTouch(celsiusSeekBar: SeekBar) {}
        })
    }

    private fun fahrenheitToCelsius(fahrenheit: Int) : Int{
        return ((fahrenheit - 32) * 5 / 9)
    }

    private fun celsiusToFahrenheit(celsius: Int) : Int{
        return (celsius * 9 / 5) + 32
    }
}