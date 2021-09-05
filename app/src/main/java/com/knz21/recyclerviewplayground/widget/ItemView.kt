package com.knz21.recyclerviewplayground.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import android.widget.TextView
import com.knz21.recyclerviewplayground.R
import com.knz21.recyclerviewplayground.ViewNameHolder

class ItemView : FrameLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var viewName: String = ViewNameHolder.getName(System.identityHashCode(this))

    var itemId: String = ""

    override fun onFinishInflate() {
        super.onFinishInflate()
        findViewById<TextView>(R.id.view_name).text = viewName
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.v("ItemView", "@@@onAttachedToWindow viewName: $viewName itemId: $itemId")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.v("ItemView", "@@@onMeasure viewName: $viewName itemId: $itemId")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.v("ItemView", "@@@onLayout viewName: $viewName itemId: $itemId")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.v("ItemView", "@@@onDraw viewName: $viewName itemId: $itemId")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.v("ItemView", "@@@onDetachedFromWindow viewName: $viewName itemId: $itemId")
    }
}