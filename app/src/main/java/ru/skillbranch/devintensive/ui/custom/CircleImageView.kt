package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.Dimension
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import ru.skillbranch.devintensive.R

class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr:Int = 0
): ImageView(context,attrs,defStyleAttr) {
    companion object{
        private const val DEFAULT_CV_BORDER_COLOR = Color.WHITE
        @Dimension private const val DEFAULT_CV_BORDER_WIDTH = 2f
    }
    private var cv_borderColor = DEFAULT_CV_BORDER_COLOR
    private var cv_borderWidth = DEFAULT_CV_BORDER_WIDTH

    init {
        if (attrs!=null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)
            cv_borderColor = a.getColor(R.styleable.CircleImageView_cv_borderColor, DEFAULT_CV_BORDER_COLOR)
            cv_borderWidth = a.getDimension(R.styleable.CircleImageView_cv_borderWidth, DEFAULT_CV_BORDER_WIDTH)
            a.recycle()
        }
    }

    @Dimension fun getBorderWidth():Int {
        return cv_borderWidth.toInt()
    }

    @Dimension fun setBorderWidth(@Dimension value:Int) {
        cv_borderWidth = value.toFloat()
    }

    fun getBorderColor():Int {
        return DEFAULT_CV_BORDER_COLOR
    }

    fun setBorderColor(hex:String) {
        cv_borderColor = Color.parseColor(hex)
    }

    fun setBorderColor(@ColorRes colorId: Int) {
        cv_borderColor = resources.getColor(colorId)
    }

    override fun onDraw(canvas: Canvas?) {

        val paint = Paint()
        paint.color = cv_borderColor
        paint.strokeWidth = cv_borderWidth
        paint.style = Paint.Style.STROKE

        var avatar = resources.getDrawable(R.drawable.avatar_default,null).toBitmap()
        canvas?.drawBitmap(avatar, imageMatrix,null)

        canvas?.drawCircle(width/2f,width/2f, (width-cv_borderWidth)/2f,paint)
    }

}