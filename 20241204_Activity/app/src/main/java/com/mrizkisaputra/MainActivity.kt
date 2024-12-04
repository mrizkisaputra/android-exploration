package com.mrizkisaputra

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var converterCategories: Array<String>
    private val units: Map<String, Array<String>> = mapOf(
        "Length" to arrayOf("mm", "cm", "m", "km", "inch"),
        "Weight" to arrayOf("mg", "g", "kg", "ounce"),
        "Temperature" to arrayOf("celsius", "fahrenheit", "kelvin")
    )

    private lateinit var spinnerConverterCategory: Spinner
    private lateinit var edtUnitInput: EditText
    private lateinit var spinnerUnitFrom: Spinner
    private lateinit var spinnerUnitTo: Spinner
    private lateinit var btnConvert: Button
    private lateinit var textViewResult: TextView

    private fun initComponents() {
        spinnerConverterCategory = findViewById(R.id.spinner_converter_category)
        edtUnitInput = findViewById(R.id.edt_unit_input)
        spinnerUnitFrom = findViewById(R.id.spinner_unit_from)
        spinnerUnitTo = findViewById(R.id.spinner_unit_to)
        btnConvert = findViewById(R.id.btn_convert)
        textViewResult = findViewById(R.id.text_result)
    }

    private fun onClick(v: View) {
        if (v.id == R.id.btn_convert) {
            val converterCategory = spinnerConverterCategory.selectedItem.toString()
            val input = edtUnitInput.text.toString().toDoubleOrNull()
            val from = spinnerUnitFrom.selectedItem.toString()
            val to = spinnerUnitTo.selectedItem.toString()

            if (input != null) {
                val result = convertUnit(converterCategory, input, from, to)
                textViewResult.text = resources.getString(R.string.unit_converter_result_text, result.toString(), to)
            } else {
                Toast.makeText(this, "Field tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun convertUnit(category: String, value: Double, from: String, to: String): Double {
        return when (category) {
            "Length" -> convertLength(value, from, to)
            "Weight" -> convertWeight(value, from, to)
            else -> convertTemperature(value, from, to)
        }
    }

    private fun convertLength(value: Double, from: String, to: String): Double {
        val inMeters = when (from) {
            "mm" -> value / 1000
            "cm" -> value / 100
            "m" -> value
            "km" -> value * 1000
            "inch" -> value * 0.0254
            else -> value
        }
        return when (to) {
            "mm" -> inMeters * 1000
            "cm" -> inMeters * 100
            "m" -> inMeters
            "km" -> inMeters / 1000
            "Inch" -> inMeters / 0.0254
            else -> inMeters
        }
    }

    private fun convertWeight(value: Double, from: String, to: String): Double {
        val inKilograms = when (from) {
            "mg" -> value / 1_000_000
            "g" -> value / 1000
            "kg" -> value
            "Once" -> value / 35.274
            else -> value
        }
        return when (to) {
            "mg" -> inKilograms * 1_000_000
            "g" -> inKilograms * 1000
            "kg" -> inKilograms
            "Once" -> inKilograms * 35.274
            else -> inKilograms
        }
    }

    private fun convertTemperature(value: Double, from: String, to: String): Double {
        return when (from to to) {
            "celsius" to "fahrenheit" -> value * 9 / 5 + 32
            "celsius" to "kelvin" -> value + 273.15
            "fahrenheit" to "celsius" -> (value - 32) * 5 / 9
            "fahrenheit" to "kelvin" -> (value - 32) * 5 / 9 + 273.15
            "kelvin" to "celsius" -> value - 273.15
            "kelvin" to "fahrenheit" -> (value - 273.15) * 9 / 5 + 32
            else -> value
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        converterCategories = resources.getStringArray(R.array.unit_converter_categories)

        // -----------------------------------------------------------------------------------------
        // initialize components
        initComponents()

        // -----------------------------------------------------------------------------------------
        // initialize data converter categories
        val adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, converterCategories)
        spinnerConverterCategory.adapter = adapter

        // mengatur unit berdasarkan converter categories
        spinnerConverterCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedCategory = converterCategories[position]
                    val unitAdapter = ArrayAdapter(
                        this@MainActivity,
                        android.R.layout.simple_spinner_item,
                        units[selectedCategory] ?: emptyArray()
                    )
                    spinnerUnitFrom.adapter = unitAdapter
                    spinnerUnitTo.adapter = unitAdapter
                    btnConvert.text =
                        getString(R.string.unit_converter_button_convert, selectedCategory)
                    edtUnitInput.hint = getString(R.string.unit_converter_hint, selectedCategory)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        btnConvert.setOnClickListener(::onClick)

    }

}