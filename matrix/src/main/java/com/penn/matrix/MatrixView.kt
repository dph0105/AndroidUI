package com.penn.matrix

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * @author: DaDing
 * @description:
 * @create: 2019-05-14 16:09
 **/
class MatrixView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {

    constructor(context: Context?, attrs: AttributeSet?):this(context, attrs, 0)
    constructor(context: Context?):this(context, null)

    val bitmap : Bitmap
    val mMatrix  = Matrix()
    val mPaint = Paint()
    init{
        mPaint.isAntiAlias = true
        mPaint.color = Color.RED
        mPaint.strokeWidth = 3f
        val options = BitmapFactory.Options()
        options.inSampleSize = 3
        bitmap = BitmapFactory.decodeResource(resources,R.drawable.opai,options)
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas!=null){
            canvas.translate(100f,100f)

            //原图
            canvas.drawBitmap(bitmap,mMatrix,mPaint)
//            右移100，下移300
//            mMatrix.setTranslate(100f,300f)
//            canvas.drawBitmap(bitmap,mMatrix,mPaint)


//            x轴，y轴缩放0.5倍
//            mMatrix.setScale(0.5f,0.5f)
//            以(300,400)为轴心，x轴，y轴缩放0.5倍
//            mMatrix.setScale(0.5f,0.5f,300f,400f)
//            canvas.drawBitmap(bitmap,mMatrix,mPaint)
//            canvas.drawPoint(300f,400f,mPaint);


            //旋转90度
//            mMatrix.setRotate(90f)
            //以(100,100)为圆心，旋转90度
//            mMatrix.setRotate(90f,100f,100f)
//            canvas.drawBitmap(bitmap,mMatrix,mPaint)
//            canvas.drawPoint(100f,100f,mPaint);


            //假设图片上任意一点的坐标为（x0，y0）那么经过矩阵变换后新的左边为（x,y）
            //   x       cos@ , -sin@ , 0     x0
            // ( y ) = ( sin@ , cos@  , 0 )*( y0 )
            //   1        0   ,  0    , 1     1
            //https://blog.csdn.net/feather_wch/article/details/79622095
            //所以旋转90度，sin90 = 1 cos90 =0
//            mMatrix.setSinCos(1f,0f)
//            mMatrix.setSinCos(1f,0f,bitmap.width/2.toFloat(),bitmap.height/2.toFloat())
//            canvas.drawBitmap(bitmap,mMatrix,mPaint)

            //kx,ky分别为x轴与y轴的错切因子
            mMatrix.setSkew(0.5f,1f)
            canvas.drawBitmap(bitmap,mMatrix,mPaint)

        }
    }

}