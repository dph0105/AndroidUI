package com.penn.beziercurve.bezieranim.circletoheart

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.graphics.PointF
import android.R.attr.factor
import android.R.attr.x
import android.R.attr.y
import android.R.attr.x
import java.nio.file.Files.size







/**
 * @author: DaDing
 * @description:
 * @create: 2019-04-29 15:41
 **/
class CircleToHeartView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {

    constructor(context: Context?,attrs: AttributeSet?) : this(context,attrs,0)

    constructor(context: Context?) : this(context,null)

    private var mHeartPointList = ArrayList<PointF>()

    private var mCirclePointList = ArrayList<PointF>()

    private var mCurPointList = ArrayList<PointF>()

    init {
        mHeartPointList.add(PointF(0f, 38f))
        mHeartPointList.add(PointF(dpToPx(50), dpToPx(103)))
        mHeartPointList.add(PointF(dpToPx(112), dpToPx(61)))
        mHeartPointList.add(PointF(dpToPx(112), dpToPx(12)))
        mHeartPointList.add(PointF(dpToPx(112), dpToPx(-37)))
        mHeartPointList.add(PointF(dpToPx(50), dpToPx(-90)))
        mHeartPointList.add(PointF(0f, dpToPx(-129)))
        mHeartPointList.add(PointF(dpToPx(-50), dpToPx(-90)))
        mHeartPointList.add(PointF(dpToPx(-112), dpToPx(-37)))
        mHeartPointList.add(PointF(dpToPx(-112), dpToPx(12)))
        mHeartPointList.add(PointF(dpToPx(-112), dpToPx(61)))
        mHeartPointList.add(PointF(dpToPx(-50), dpToPx(103)))
        mHeartPointList.add(PointF(0f, 38f))

        mCirclePointList.add(PointF(0f, dpToPx(90)))
        mCirclePointList.add(PointF(dpToPx(50), dpToPx(90)))
        mCirclePointList.add(PointF(dpToPx(90), dpToPx(50)))
        mCirclePointList.add(PointF(dpToPx(90), 0f))
        mCirclePointList.add(PointF(dpToPx(90), dpToPx(-50)))
        mCirclePointList.add(PointF(dpToPx(50), dpToPx(-90)))
        mCirclePointList.add(PointF(0f, dpToPx(-90)))
        mCirclePointList.add(PointF(dpToPx(-50), dpToPx(-90)))
        mCirclePointList.add(PointF(dpToPx(-90), dpToPx(-50)))
        mCirclePointList.add(PointF(dpToPx(-90), 0f))
        mCirclePointList.add(PointF(dpToPx(-90), dpToPx(50)))
        mCirclePointList.add(PointF(dpToPx(-50), dpToPx(90)))
        mCirclePointList.add(PointF(0f, dpToPx(90)))

        mCurPointList.add(PointF(0f, dpToPx(90)))
        mCurPointList.add(PointF(dpToPx(50), dpToPx(90)))
        mCurPointList.add(PointF(dpToPx(90), dpToPx(50)))
        mCurPointList.add(PointF(dpToPx(90), 0f))
        mCurPointList.add(PointF(dpToPx(90), dpToPx(-50)))
        mCurPointList.add(PointF(dpToPx(50), dpToPx(-90)))
        mCurPointList.add(PointF(0f, dpToPx(-90)))
        mCurPointList.add(PointF(dpToPx(-50), dpToPx(-90)))
        mCurPointList.add(PointF(dpToPx(-90), dpToPx(-50)))
        mCurPointList.add(PointF(dpToPx(-90), 0f))
        mCurPointList.add(PointF(dpToPx(-90), dpToPx(50)))
        mCurPointList.add(PointF(dpToPx(-50), dpToPx(90)))
        mCurPointList.add(PointF(0f, dpToPx(90)))

    }

    private fun dpToPx(i: Int): Float {
        return i.toFloat()
    }


    var mWidth = 0
    var mHeight = 0

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = MeasureSpec.getSize(widthMeasureSpec)
        mHeight = MeasureSpec.getSize(heightMeasureSpec)

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    val bezierPath = Path()
    val paint  = Paint()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        bezierPath.reset()

        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true
        paint.strokeWidth = 5f
        var startW = mWidth.toFloat()/2
        var startH = mHeight.toFloat()/2
        bezierPath.moveTo(startW,startH-mCurPointList[0].y)

        for (i in 1 until mCurPointList.size step 3){
            bezierPath.cubicTo(startW+mCurPointList[i].x,startH-mCurPointList[i].y,startW+mCurPointList[i+1].x,startH-mCurPointList[i+1].y,startW+mCurPointList[i+2].x,startH-mCurPointList[i+2].y)
            canvas!!.drawPath(bezierPath,paint)
        }

    }
    var anim : ValueAnimator? = null
    fun startAnim(){
        anim = ValueAnimator.ofFloat(0f,1f)
        anim!!.setDuration(1000)
        anim!!.addUpdateListener {
            val x  = it.getAnimatedValue() as Float
            anim(x)
        }
        anim!!.start()
    }

    private fun anim(x : Float){
        val factor = 0.15f
        val value = Math.pow(2.0, (-10 * x).toDouble()) * Math.sin((x - factor / 4) * (2 * Math.PI) / factor) + 1

        for (i in 0 until mCurPointList.size) {

            val startPoint = mCirclePointList[i]
            val endPoint = mHeartPointList[i]

            mCurPointList[i].x = (startPoint.x + ((endPoint.x - startPoint.x) * value)).toFloat()
            mCurPointList[i].y = (startPoint.y + ((endPoint.y - startPoint.y) * value)).toFloat()

        }
        invalidate()
    }

    fun reset(){
        if (anim!=null){
            anim!!.cancel()
        }
        mCurPointList.clear()
        mCurPointList.add(PointF(0f, dpToPx(90)))
        mCurPointList.add(PointF(dpToPx(50), dpToPx(90)))
        mCurPointList.add(PointF(dpToPx(90), dpToPx(50)))
        mCurPointList.add(PointF(dpToPx(90), 0f))
        mCurPointList.add(PointF(dpToPx(90), dpToPx(-50)))
        mCurPointList.add(PointF(dpToPx(50), dpToPx(-90)))
        mCurPointList.add(PointF(0f, dpToPx(-90)))
        mCurPointList.add(PointF(dpToPx(-50), dpToPx(-90)))
        mCurPointList.add(PointF(dpToPx(-90), dpToPx(-50)))
        mCurPointList.add(PointF(dpToPx(-90), 0f))
        mCurPointList.add(PointF(dpToPx(-90), dpToPx(50)))
        mCurPointList.add(PointF(dpToPx(-50), dpToPx(90)))
        mCurPointList.add(PointF(0f, dpToPx(90)))
        invalidate()
    }


}