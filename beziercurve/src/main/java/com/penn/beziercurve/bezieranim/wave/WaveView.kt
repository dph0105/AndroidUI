package com.penn.beziercurve.bezieranim.wave

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
class WaveView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {

    constructor(context: Context?, attrs: AttributeSet?):this(context, attrs, 0)

    constructor(context: Context?):this(context, null)


    //波浪的路径
    val mWavePath = Path()
    val WAVE_WIDTH = 200f;
    val WAVE_HEIGHT = 50f;

    val mDst = Path()
    //画笔
    val mPaint = Paint()
    //PathMeasure
    val mPathMeasure = PathMeasure()

    val posArray = FloatArray(2)
    val tanArray = FloatArray(2)
    val boatBitmap : Bitmap
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

        val options =  BitmapFactory.Options()
        options.inSampleSize = 2

        boatBitmap = BitmapFactory.decodeResource(getContext().resources, R.drawable.boat,options)


        mWavePath.addCircle(150f,150f,100f,Path.Direction.CCW)

        mValueAnimator = ValueAnimator.ofFloat(0f,1f)
        mValueAnimator.duration = 2000
        mValueAnimator.repeatCount = -1
        mValueAnimator.interpolator = LinearInterpolator()
        mValueAnimator.addUpdateListener {
            percent = it.animatedValue as Float
            invalidate()
        }
    }



    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWavePath.reset()
        mWavePath.moveTo(-WAVE_WIDTH,h/2.toFloat())
        for (i in 0..width/WAVE_WIDTH.toInt()+1){
            mWavePath.rQuadTo(WAVE_WIDTH / 4,
                -WAVE_HEIGHT,
                WAVE_WIDTH / 2,
                0f);
            mWavePath.rQuadTo(WAVE_WIDTH / 4,
                WAVE_HEIGHT,
                WAVE_WIDTH / 2,
                0f);
        }
        mPathMeasure.setPath(mWavePath,false)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas==null){
            return
        }

        mPathMeasure.getPosTan(mPathMeasure.length*percent,posArray,tanArray)
        val degree :Float = (Math.atan2(tanArray[1].toDouble(),tanArray[1].toDouble())*180/Math.PI).toFloat()
        mMatrix.setTranslate(posArray[0]+boatBitmap.width,posArray[1]-boatBitmap.height)
//        mMatrix.postRotate(degree)
//        mMatrix.preTranslate(-boatBitmap.getWidth() / 2f, -boatBitmap.getHeight() * 5f / 6f);

        canvas.drawBitmap(boatBitmap,mMatrix,mPaint)
        canvas.translate(WAVE_WIDTH*percent,0f)
        canvas.drawPath(mWavePath,mPaint)
    }

    public fun startLoading(){
        mValueAnimator.start()
    }

    public fun stopLoading(){
        mValueAnimator.cancel()
    }

}