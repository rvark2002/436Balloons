package com.example.project7
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

class GameView(context: Context, private val balloons: ArrayList<Balloon>) : View(context) {

    private val balloonPaint = Paint()
    private var attempts = 0
    private var balloonsPopped = 0
    private var maxAttempts = balloons.size + 3

    init {
        balloonPaint.color = Color.BLUE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (balloon in balloons) {
            if (!balloon.isPopped) {
                canvas.drawCircle(balloon.getX().toFloat(), balloon.getY().toFloat(), balloon.getRadius().toFloat(), balloonPaint)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val x = event.x.toInt()
            val y = event.y.toInt()

            for (balloon in balloons) {
                if (!balloon.isPopped && isInsideBalloon(x, y, balloon)) {
                    balloon.pop()
                    balloonsPopped++
                    invalidate()

                    if (balloonsPopped == balloons.size) {
                        showWinToast()
                    }

                    return true
                }
            }

            attempts++
            if (attempts >= maxAttempts) {
                showLoseToast()
            }
        }

        return super.onTouchEvent(event)
    }

    private fun isInsideBalloon(x: Int, y: Int, balloon: Balloon): Boolean {
        val dx = x - balloon.getX()
        val dy = y - balloon.getY()
        val distance = Math.sqrt((dx * dx + dy * dy).toDouble())
        return distance <= balloon.getRadius()
    }

    private fun showWinToast() {
        Toast.makeText(context, "YOU WON!", Toast.LENGTH_SHORT).show()
    }

    private fun showLoseToast() {
        Toast.makeText(context, "Game Over - YOU LOST!", Toast.LENGTH_SHORT).show()
        activity.finish()
    }
}