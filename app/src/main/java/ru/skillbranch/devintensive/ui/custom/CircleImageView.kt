package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.Dimension
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import ru.skillbranch.devintensive.App
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.utils.Utils

class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr:Int = 0
): ImageView(context,attrs,defStyleAttr) {
    companion object{
        private const val DEFAULT_CV_BORDER_COLOR = Color.WHITE
//        private const val DEFAULT_TEXT = ""
    }

    @Dimension private val DEFAULT_CV_BORDER_WIDTH = Utils.convertDpToPx(context, 2F)

    private var cv_borderColor = DEFAULT_CV_BORDER_COLOR
    private var cv_borderWidth = DEFAULT_CV_BORDER_WIDTH
//    private var cv_text = DEFAULT_TEXT

    init {
        if (attrs!=null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)
            cv_borderColor = a.getColor(R.styleable.CircleImageView_cv_borderColor, DEFAULT_CV_BORDER_COLOR)
            cv_borderWidth = a.getDimensionPixelSize(R.styleable.CircleImageView_cv_borderWidth, DEFAULT_CV_BORDER_WIDTH)
//            cv_text = a.getString(R.styleable.CircleImageView_cv_text).toString()
            a.recycle()
        }
    }

    @Dimension
    fun getBorderWidth():Int = Utils.convertPxToDp(context, cv_borderWidth)

    @Dimension
    fun setBorderWidth(value:Int) {
        cv_borderWidth = Utils.convertDpToPx(context,value.toFloat())
        this.invalidate()
    }

    fun getBorderColor():Int = DEFAULT_CV_BORDER_COLOR

    fun setBorderColor(hex:String) {
        cv_borderColor = Color.parseColor(hex)
        this.invalidate()
    }

    fun setBorderColor(@ColorRes colorId: Int) {
        cv_borderColor = ContextCompat.getColor(App.applicationContext(), colorId)
        this.invalidate()
    }

    override fun onDraw(canvas: Canvas?) {

//        if (cv_text.isNotBlank()) {
//            val fontPaint = Paint(Paint.ANTI_ALIAS_FLAG)
////            fontPaint.color = resources.getColor(R.color.color_accent,context.theme)
//            fontPaint.color = Color.WHITE
//            fontPaint.textSize = 82f
//            fontPaint.style = Paint.Style.STROKE
//            fontPaint.textAlign = Paint.Align.CENTER
//            fontPaint.
//
//            val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
//            backgroundPaint.color = resources.getColor(R.color.color_accent,context.theme)
//            backgroundPaint.style = Paint.Style.FILL
//
//            canvas?.drawCircle(width/2f,width/2f, (width-cv_borderWidth)/2f,backgroundPaint)
//            canvas?.drawText(cv_text,width/2f,width/2f,fontPaint)
//        }
//        else {
            val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
            circlePaint.color = cv_borderColor
            circlePaint.strokeWidth = cv_borderWidth.toFloat()
            circlePaint.style = Paint.Style.STROKE

            canvas?.drawBitmap(this.drawable.toBitmap(), imageMatrix, null)
        //
            canvas?.drawCircle(width/2f,width/2f, (width-cv_borderWidth)/2f,circlePaint)
//        }

    }

}