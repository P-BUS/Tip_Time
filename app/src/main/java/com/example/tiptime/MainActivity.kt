package com.example.tiptime

import android.content.Context
import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Binds Button click with tip calculation function
        binding.calculateTipsButton.setOnClickListener { calculateTips() }

        // Hides the keyboard
        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(view, keyCode)
        }
    }

    /** This function is calculating tips depending of quality of service, chosen by customer
     * from radio button. There are 3 options: 10%, 5% and 0%.
     */
    private fun calculateTips() {
        // Variable takes filled sum and convert it to number
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        // Replaces null to ""
        if (cost == null) {
            binding.tipResult.text = ""
            return
        }
        // Returns percentage of tips depend of switch
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.great_button -> 0.10
            R.id.as_usual_button -> 0.05
            else -> 0.0
        }
        // If the cost is null or 0, then display 0 tip and exit this function early.
        if (cost == 0.0) {
            displayTip(0.0)
            return
        }
        // Calculate a tip amount
        var tip = tipPercentage * cost
        // Rounds tip amount if needed
        if (binding.switchShareTips.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        // Display the formatted tip value on screen
        displayTip(tip)
    }

    // Formats the tip to currency type and show formatted tips in TextView
    private fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance(Locale.US).format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}
