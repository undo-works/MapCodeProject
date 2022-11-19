package com.example.mapcodeproject

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
    private val tokyoCie = LatLng(35.682230683454925, 139.76717530848737)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in Sydney and move the camera
        setMarker()
        setCamera()
//        setPolyline()
        setPolygon()
    }

    private fun setMarker() {
        val coordinate = MapUtil.meshcodeToCie("6642-52-87-1")
        Log.d("coordinate", coordinate.toString())
        mMap.addMarker(MarkerOptions().position(coordinate!!).title("Marker in Tokyo"))
    }

    private fun setCamera() {
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tokyoCie))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f))
    }

    private fun setPolyline() {
        // Instantiates a new Polyline object and adds points of paths
        val polylineOptions = PolylineOptions()
            .add(LatLng(35.681510, 139.765595))
            .add(LatLng(35.682977, 139.759828))
            .add(LatLng(35.672191, 139.752856))
            .add(LatLng(35.673968, 139.747944))
            .add(LatLng(35.676362, 139.746962))
            .color(Color.RED)
        mMap.addPolyline(polylineOptions)
    }

    private fun setPolygon() {
        // Instantiates a new Polygon object and adds points to define a rectangle
        val rectOptions = PolygonOptions()
            .add(
                LatLng(35.677589, 139.743189),
                LatLng(35.673816, 139.744241),
                LatLng(35.674499, 139.747491),
                LatLng(35.678102, 139.746420),
            )
            .fillColor(Color.argb(0.2f, 0/255f, 255/255f, 0/255f))
        mMap.addPolygon(rectOptions)
    }

}

