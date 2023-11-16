package com.example.project7

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class GameView(context: Context, balloonsArray: ArrayList<Balloon>) : View(context) {

    private val balloonPaint = Paint()
    private var balloons : ArrayList<Balloon>

    init {
        balloonPaint.color = Color.BLUE
        balloons = balloonsArray
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (balloon in balloons) {
            if (!balloon.isPopped()) {
                canvas.drawCircle(balloon.getX().toFloat(), balloon.getY().toFloat(),
                    balloon.getRadius().toFloat(), balloonPaint)
            }
        }
    }
}
