package com.egco428.basicmap

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var flag = true;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        var list = arrayListOf<LatLng>()
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val bangkok = LatLng(13.7946, 100.3234)
        list.add(bangkok)
        mMap.addMarker(MarkerOptions().position(bangkok).title("Marker in Mahidol").icon(BitmapDescriptorFactory.fromResource(R.drawable.mahidol)))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bangkok,5f))
        /*mMap.addMarker(MarkerOptions().position(LatLng(15.5234,99.1)).title("Marker in A").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)))
        mMap.addMarker(MarkerOptions().position(LatLng(14.5,102.1)).title("Marker in B").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)))
        mMap.addMarker(MarkerOptions().position(LatLng(13.1,101.1)).title("Marker in C").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)))
*/
        /*val line = mMap.addPolyline(PolylineOptions()
                .add(LatLng(13.7563, 100.5018), LatLng(15.5234,99.1),  LatLng(14.5,102.1))
                .width(7f)
                .color(Color.RED)
        )
*/
/*        val line = mMap.addPolygon(PolygonOptions()
                .add(LatLng(13.7563, 100.5018), LatLng(15.5234,99.1),  LatLng(14.5,102.1))
                .strokeColor(Color.YELLOW)
                .fillColor(Color.RED)
        )*/

        mMap.setOnMapClickListener {
            latLng -> mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))

        }
        mMap.setOnMapLongClickListener {
            latLng ->
            if(flag){
                mMap.addMarker(MarkerOptions().position(latLng).title(latLng.toString()).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker1)))
                flag = false
            }
            else{
                mMap.addMarker(MarkerOptions().position(latLng).title(latLng.toString()).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker2)))
                flag = true
            }
            list.add(latLng)
            mMap.addPolyline(PolylineOptions()
                    .add(list.get(list.count() - 2), list.last())
                    .width(8f)
                    .color(Color.RED)
            )


        }
    }
}
