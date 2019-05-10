package com.penn.beziercurve

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.penn.beziercurve.basebezier.SecondBezierActivity
import com.penn.beziercurve.basebezier.ThirdBezierActivity
import com.penn.beziercurve.bezieranim.airplaneloading.AirPlaneLoadingActivity
import com.penn.beziercurve.bezieranim.circletoheart.CircleToHeartActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_second_bezier.setOnClickListener {
            val intent = Intent(this@MainActivity,SecondBezierActivity::class.java)
            startActivity(intent)
        }
        btn_third_bezier.setOnClickListener{
            val intent = Intent(this@MainActivity,ThirdBezierActivity::class.java)
            startActivity(intent)
        }
        btn_circle_to_heart.setOnClickListener {
            val intent = Intent(this@MainActivity,CircleToHeartActivity::class.java)
            startActivity(intent)
        }
        btn_airplane.setOnClickListener {
            val intent = Intent(this@MainActivity,AirPlaneLoadingActivity::class.java)
            startActivity(intent)
        }

    }

    class Box(s:String) {
        var name = s
        override fun toString(): String {
            return name
        }
    }
}
