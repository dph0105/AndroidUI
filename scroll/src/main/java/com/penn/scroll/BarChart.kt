package com.penn.scroll

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewConfiguration
import android.widget.Scroller

/**
 * @author: DaDing
 * @description:
 * @create: 2019-05-16 17:04
 **/
class BarChart(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?) : this(context, null, 0)

    var barNumber = 100 //默认三十个柱
    var barInterval = 100f;//默认间隔50px
    private var velocityTracker : VelocityTracker?

    val mPaint : Paint
    val maximumVelocity : Int
    val minimumVelocity : Int
    val mScroller : Scroller
//    val mBitmap : Bitmap
    init {
        mPaint = Paint()
        mPaint.isAntiAlias = true
        mPaint.color = Color.GRAY
        mPaint.strokeWidth = 3f
//        mBitmap = BitmapFactory.decodeResource(getContext().resources, R.drawable.ic_launcher)
        maximumVelocity = ViewConfiguration.get(context).scaledMaximumFlingVelocity
        minimumVelocity = ViewConfiguration.get(context).scaledMinimumFlingVelocity

        mScroller = Scroller(context,null,false)
        velocityTracker = VelocityTracker.obtain()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas==null){
            return
        }
        var x = 0f
        for (i in 0..barNumber){
            x = i*barInterval+barInterval
            canvas.drawLine(x,0f,x,height.toFloat(),mPaint)
        }
    }


    var lastX = 0f
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (barNumber*barInterval+barInterval<width){
            return false
        }
        if(velocityTracker==null){
            velocityTracker = VelocityTracker.obtain()
        }
        velocityTracker!!.addMovement(event)
        val length = (barNumber+1)*barInterval
        if (event!=null){

            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    lastX = event.x
                    if (!mScroller.isFinished()) {
                        mScroller.abortAnimation();
                    }
                }
                MotionEvent.ACTION_MOVE -> {
                    Log.d("Scroll","getScroll = " +scrollX)
                    val moveX = event.x - lastX

                    //scrollX表示已经位移的距离，那么就是上一次的改变
                    //那么这一次将要进行的位移就是 scrollX-moveX,表示这一次变化后的scrollX值
                    val nextScrollX = scrollX -moveX
                    if (moveX>0){
                        //如果手指向右滑动
                        if (nextScrollX<=0){
                            //如果已经到达左边界，无法继续往右边移动
                            scrollTo(0,0)
                        }else{
                            scrollBy(-moveX.toInt(),0)
                        }
                    }else{
                        if (nextScrollX+width>=length){
                            //如果已经到达右边界，无法继续往左移动
                            scrollTo(length.toInt()-width,0)
                        }else{
                            scrollBy(-moveX.toInt(),0)
                        }
                    }
                    lastX = event.x
                }
                MotionEvent.ACTION_UP -> {
                    lastX = event.x
                    velocityTracker!!.computeCurrentVelocity(1000,maximumVelocity.toFloat())
                    val xVelocity = velocityTracker!!.xVelocity
                    Log.d("Vel",xVelocity.toString())
//                    if (Math.abs(xVelocity)>minimumVelocity){
                        //速度大于最小速度才滑动
//                        mScroller.abortAnimation()
//                        if (xVelocity>0){
//                            if (scrollX>=0){
//                                mScroller.fling(scrollX,0,xVelocity.toInt(),0,0, (length-width).toInt(),0,0)
//                            }
//                        }else{
//
//                        }

                        if (scrollX+width < length||scrollX>=0){
                            mScroller.fling(scrollX,0,-xVelocity.toInt(),0,0, (length-width).toInt(),0,0)
                        }
                    invalidate()
//                    }
                    velocityTracker!!.recycle()
                    velocityTracker = null
                }
            }
        }
        return true
    }


    override fun computeScroll() {
        if (mScroller.computeScrollOffset()) {
            //这里调用View的scrollTo()完成实际的滚动
            scrollTo(mScroller.getCurrX(),0);
            //必须调用该方法，否则不一定能看到滚动效果
            postInvalidate();
        }
    }

}