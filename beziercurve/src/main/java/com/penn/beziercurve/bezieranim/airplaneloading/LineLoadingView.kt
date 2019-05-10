package com.penn.beziercurve.bezieranim.airplaneloading

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.penn.beziercurve.R

/**
 * @author: DaDing
 * @description:
 * @create: 2019-05-09 10:48
 **/
class LineLoadingView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {

    constructor(context: Context?, attrs: AttributeSet?):this(context, attrs, 0)

    constructor(context: Context?):this(context, null)


    //圆的路径
    val mCirclePath = Path()
    val mDst = Path()
    //画笔
    val mPaint = Paint()
    //PathMeasure
    val mPathMeasure = PathMeasure()
    //线条的动画
    private val mValueAnimator : ValueAnimator;
    private var percent : Float = 0f
    val mMatrix = Matrix()

    init {
        //画笔初始化 抗锯齿，黑色，不填充，2px
        mPaint.isAntiAlias = true
        mPaint.color = Color.parseColor("#000000")
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 5f

        mCirclePath.addCircle(150f,150f,100f,Path.Direction.CW)
        mPathMeasure.setPath(mCirclePath, false)

        mValueAnimator = ValueAnimator.ofFloat(0f,1f)
        mValueAnimator.duration = 2000
        mValueAnimator.repeatCount = -1
        mValueAnimator.interpolator = LinearInterpolator()
        mValueAnimator.addUpdateListener {
            percent = it.animatedValue as Float
            invalidate()
        }


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas==null){
            return
        }
        mDst.reset()
        val stop = percent*mPathMeasure.length
        val start = stop-(0.5-Math.abs(0.5-percent))*mPathMeasure.length
        mPathMeasure.getSegment(start.toFloat(),stop,mDst,true)
        canvas.drawPath(mDst,mPaint)

    }

    public fun startLoading(){
        mValueAnimator.start()
    }

    public fun stopLoading(){
        mValueAnimator.cancel()
    }

}