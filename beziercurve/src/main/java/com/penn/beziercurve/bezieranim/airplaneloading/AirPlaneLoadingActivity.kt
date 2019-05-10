package com.penn.beziercurve.bezieranim.airplaneloading

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.penn.beziercurve.R
import kotlinx.android.synthetic.main.activity_air_plane_loading.*
import kotlinx.android.synthetic.main.activity_circle_to_heart.*

class AirPlaneLoadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_air_plane_loading)
        btn_start.setOnClickListener {
            plane.startLoading()
            line.startLoading()
            wave.startLoading()
        }
        btn_stop.setOnClickListener {
            plane.stopLoading()
            line.stopLoading()
            wave.stopLoading()
        }

    }
}
