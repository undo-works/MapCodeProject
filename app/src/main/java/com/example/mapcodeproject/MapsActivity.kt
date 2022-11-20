package com.example.mapcodeproject

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.graphics.alpha

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.example.mapcodeproject.databinding.ActivityMapsBinding
import com.example.mapcodeproject.util.MapUtil
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private var meshCode: String? = null
    private var meshBaseLatLng: LatLng? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getMainIntent()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    //Receiving input values from MainActivity
    fun getMainIntent() {
        val intent: Intent = getIntent()
        meshCode = intent.getStringExtra("meshCode")
        //Set the reference coordinates of the mesh
        meshBaseLatLng = meshCode?.let { MapUtil.meshcodeToCie(it) }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        setCameraLatLng()
        makeMesh()
    }
    //Set the camera to the reference coordinates of the mesh
    private fun setCameraLatLng() {
        meshBaseLatLng.let {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(meshBaseLatLng!!))
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f))
        }
    }

    //Create polygons based on input values
    fun makeMesh() {
        meshBaseLatLng.let {
            val rectOptions = PolygonOptions()
                .add(
                    meshBaseLatLng,
                    LatLng(meshBaseLatLng!!.latitude, meshBaseLatLng!!.longitude + 1.0 / 8 / 10 / 2),
                    LatLng(meshBaseLatLng!!.latitude + 1.0 / 1.5 / 8 / 10 / 2, meshBaseLatLng!!.longitude + 1.0 / 8 / 10 / 2),
                    LatLng(meshBaseLatLng!!.latitude + 1.0 / 1.5 / 8 / 10 / 2, meshBaseLatLng!!.longitude),
                )
                .fillColor(Color.argb(0.2f, 0/255f, 255/255f, 0/255f))
            mMap.addPolygon(rectOptions)
        }
    }
}

