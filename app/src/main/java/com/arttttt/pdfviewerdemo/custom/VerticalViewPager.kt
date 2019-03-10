package com.arttttt.pdfviewerdemo.custom

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class VerticalViewPager: ViewPager {
    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        init()
    }

    constructor(context: Context): super(context) {
        init()
    }

    private fun init() {
        setPageTransformer(true, VerticalPageTransformer())
        overScrollMode = OVER_SCROLL_NEVER
    }

    private fun swapXY(event: MotionEvent): MotionEvent {
        val width = width
        val height = height

        val newX = (event.y / height) * width
        val newY = (event.x / width) * height

        event.setLocation(newX, newY)

        return event
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean{
        val intercepted = super.onInterceptTouchEvent(swapXY(event))
        swapXY(event)
        return intercepted
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return super.onTouchEvent(swapXY(event))
    }

    private inner class VerticalPageTransformer: ViewPager.PageTransformer {

        override fun transformPage(view: View, position: Float) {
            when {
                position < -1 ->
                    view.alpha = 0F
                position <= 1 -> {
                    view.alpha = 1F

                    view.translationX = view.width * -position

                    val yPosition = position * view.height
                    view.translationY = yPosition

                }
                else ->
                    view.alpha = 0F
            }
        }
    }
}