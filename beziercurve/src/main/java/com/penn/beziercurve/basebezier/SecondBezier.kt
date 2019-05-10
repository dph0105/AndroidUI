package com.penn.beziercurve.basebezier

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/**
 * @author: DaDing
 * @description:
 * @create: 2019-04-29 15:41
 **/
class SecondBezier(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {

    constructor(context: Context?,attrs: AttributeSet?) : this(context,attrs,0)

    constructor(context: Context?) : this(context,null)


    init {

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
        paint.color = Color.BLUE
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        paint.strokeWidth = 1f
        bezierPath.moveTo(100f,100f)
        bezierPath.quadTo(100f, mHeight.toFloat()/2,mWidth.toFloat()/2,mHeight.toFloat()/2)
        canvas!!.drawPath(bezierPath,paint)
        paint.color = Color.RED
        paint.strokeWidth = 5f
        canvas.drawPoint(100f,100f,paint)
        canvas.drawPoint(100f, mHeight.toFloat()/2,paint)
        canvas.drawPoint(mWidth.toFloat()/2,mHeight.toFloat()/2,paint)
    }


}