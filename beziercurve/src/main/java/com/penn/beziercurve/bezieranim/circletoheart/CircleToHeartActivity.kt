package com.penn.beziercurve.bezieranim.circletoheart

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.penn.beziercurve.R
import kotlinx.android.synthetic.main.activity_circle_to_heart.*

class CircleToHeartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circle_to_heart)
        btn_start_anim.setOnClickListener{
            c2h.startAnim()
        }

        btn_reset.setOnClickListener {
            c2h.reset()
        }
    }
}
