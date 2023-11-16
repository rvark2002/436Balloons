//Group Members: Anvi Padiyar, Ronith Solipuram, Ritvik Varkhedkar
package com.example.project7

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import java.io.InputStream
import javax.xml.parsers.SAXParser
import javax.xml.parsers.SAXParserFactory

class MainActivity : Activity() {
    private lateinit var balloons : ArrayList<Balloon>
    private lateinit var gameView : GameView
    private lateinit var balloonsModel : Balloons
    private var statusBarHeight : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var statusBarId : Int = resources.getIdentifier("status_bar_height",
            "dimen", "android")
        statusBarHeight  = resources.getDimensionPixelSize(statusBarId)

        var factory : SAXParserFactory = SAXParserFactory.newInstance()
        var saxParser : SAXParser = factory.newSAXParser()
        var handler : SAXHandler = SAXHandler()
        var iStream : InputStream = resources.openRawResource(R.raw.balloons3) //set to balloons3 when submitting
        saxParser.parse(iStream, handler)

        balloons = handler.getBalloons()
        balloonsModel = Balloons(balloons.size)

        for(item in balloons){
            Log.w("MainActivity", item.toString())
        }

        gameView = GameView(this, balloons)
        setContentView(gameView)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN && !balloonsModel.checkIfGameOver()) {
            val x = event.getX().toInt()
            val y = event.getY().toInt() - statusBarHeight
            balloonsModel.updateAttempt()

            for (balloon in balloons) {
                if (!balloon.isPopped() && balloonsModel.isInsideBalloon(x, y, balloon)) {
                    balloon.pop()
                    gameView.postInvalidate()
                    balloonsModel.updatePopped()
                    if (balloonsModel.checkIfWon()) {
                        Toast.makeText(
                            this, "YOU WON!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            if(!balloonsModel.checkIfGameOver() && balloonsModel.checkIfLost()){
                finish()
            }
        }
        return super.onTouchEvent(event)
    }
}
