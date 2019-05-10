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
class AirPlaneLoadingView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {

    constructor(context: Context?, attrs: AttributeSet?):this(context, attrs, 0)

    constructor(context: Context?):this(context, null)

    //圆上的小飞机的点的位置
    val positionArray = FloatArray(2)
    //小飞机箭头的正切
    val tanArray = FloatArray(2)

    //圆的路径
    val mCirclePath = Path()
    //画笔
    val mPaint = Paint()
    //PathMeasure
    val mPathMeasure = PathMeasure()
    //小飞机的动画
    private val mValueAnimator : ValueAnimator;
    private var percent : Float = 0f
    val mMatrix = Matrix()
    val arrowBitmap by lazy {
        BitmapFactory.decodeResource(getContext().resources, R.drawable.arrow)
    }

    init {
        //画笔初始化 抗锯齿，黑色，不填充，2px
        mPaint.isAntiAlias = true
        mPaint.color = Color.parseColor("#000000")
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 3f

        mCirclePath.addCircle(200f,200f,100f,Path.Direction.CW)
        mPathMeasure.setPath(mCirclePath, false)

        mValueAnimator = ValueAnimator.ofFloat(0f,1f)
        mValueAnimator.duration = 5000
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
        canvas.drawPath(mCirclePath,mPaint)
        mPathMeasure.getPosTan(mPathMeasure.length*percent,positionArray,tanArray)

        var degree = Math.atan2(tanArray[1].toDouble(), tanArray[0].toDouble()) * 180 / Math.PI
        mMatrix.reset()
        mMatrix.postRotate(degree.toFloat(),arrowBitmap.width/2.toFloat(),arrowBitmap.height/2.toFloat())
        mMatrix.postTranslate(positionArray[0]-arrowBitmap.width/2,positionArray[1]-arrowBitmap.height/2)

        canvas.drawBitmap(arrowBitmap,mMatrix,mPaint)

    }

    public fun startLoading(){
        mValueAnimator.start()
    }

    public fun stopLoading(){
        mValueAnimator.cancel()
    }

}