package com.example.mapcodeproject.util

import com.google.android.gms.maps.model.LatLng

class MapUtil {
    companion object {
        //meshcode → coordinate
        fun meshcodeToCie(meshcode: String): LatLng? {
            try {
                val meshCodeStrArray = meshcode.split("-")
                val onceMesh = onceMeshcodeToCie(meshCodeStrArray[0])
                val secondaryMesh = secondaryMeshcodeToCie(meshCodeStrArray[1])
                val tertiaryMesh = tertiaryMeshcodeToCie(meshCodeStrArray[2])
                val halfMesh = halfMeshcodeToCie(meshCodeStrArray[3])
                if (onceMesh != null &&
                        secondaryMesh != null &&
                        tertiaryMesh != null &&
                        halfMesh != null) {
                    val cie = LatLng(
                        onceMesh.latitude + secondaryMesh.latitude + tertiaryMesh.latitude + halfMesh.latitude,
                        onceMesh.longitude + secondaryMesh.longitude + tertiaryMesh.longitude + halfMesh.longitude
                    )
                    return cie
                } else {
                    return null
                }
            } catch (e: Exception) {
                return null
            }
        }

        //once meshcode → coordinate
        private fun onceMeshcodeToCie(meshcode: String): LatLng? {
            try {
                val cieStrArr = meshcode.chunked(2)
                val lat = cieStrArr[0].toDouble() / 1.5
                val long = cieStrArr[1].toDouble() + 100
                return LatLng(lat, long)
            } catch (e: Exception) {
                return null
            }
        }

        //secondary meshcode → coordinate
        private fun secondaryMeshcodeToCie(meshcode: String): LatLng? {
            try {
                val cieStrArr = meshcode.chunked(1)
                val lat = cieStrArr[0].toDouble() / 1.5 / 8
                val long = cieStrArr[1].toDouble() / 8
                return LatLng(lat, long)
            } catch (e: Exception) {
                return null
            }
        }

        //tertiary meshcode → coordinate
        private fun tertiaryMeshcodeToCie(meshcode: String): LatLng? {
            try {
                val cieStrArr = meshcode.chunked(1)
                val lat = cieStrArr[0].toDouble() / 1.5 / 8 / 10
                val long = cieStrArr[1].toDouble() / 8 / 10
                return LatLng(lat, long)
            } catch (e: Exception) {
                return null
            }
        }

        //half region meshcode → coordinate
        private fun halfMeshcodeToCie(meshcode: String): LatLng? {
            try {
                var cie = LatLng(0.0, 0.0)
                when (meshcode) {
                    "1" -> {}
                    "2" -> {
                        cie = LatLng(0.0, 1.0 / 8 / 10 / 2)
                    }
                    "3" -> {
                        cie = LatLng(1.0 / 1.5 / 8 / 10 / 2, 0.0)
                    }
                    "4" -> {
                        cie = LatLng(1.0 / 1.5 / 8 / 10 / 2, 1.0 / 8 / 10 / 2)
                    }
                    else -> {
                        return null
                    }
                }
                return cie
            } catch (e: Exception) {
                return null
            }
        }
    }
}