package com.example.mapcodeproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var onceLat: EditText
    private lateinit var onceLon: EditText
    private lateinit var secondaryLat: EditText
    private lateinit var secondaryLon: EditText
    private lateinit var tertiaryLat: EditText
    private lateinit var tertiaryLon: EditText
    private lateinit var halfRegionValue: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpComponents()
        setUpMapsActivity()
    }

    fun setUpComponents() {
        onceLat = findViewById(R.id.once_lat)
        onceLon = findViewById(R.id.once_lon)
        secondaryLat = findViewById(R.id.secondary_lat)
        secondaryLon = findViewById(R.id.secondary_lon)
        tertiaryLat = findViewById(R.id.tertiary_lat)
        tertiaryLon = findViewById(R.id.tertiary_lon)
        halfRegionValue = findViewById(R.id.half_region_value)
    }
    //Send input value to MapsActivity
    fun setUpMapsActivity() {
        findViewById<Button>(R.id.btn_mesh_make).setOnClickListener {
            val meshCode =  connectMeshCode()
            val intent = Intent(this, MapsActivity::class.java)
            intent.putExtra("meshCode", meshCode)
            startActivity(intent)
        }
    }

    fun connectMeshCode(): String {
        val meshCode: String = onceLat.text.toString() + onceLon.text + "-" +
                secondaryLat.text + secondaryLon.text + "-" +
                tertiaryLat.text + tertiaryLon.text + "-" +
                halfRegionValue.text
        return meshCode
    }
}