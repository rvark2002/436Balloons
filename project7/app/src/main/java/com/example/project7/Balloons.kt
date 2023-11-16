package com.example.project7

class Balloons {
    private var numOfBalloons : Int
    private var attempts : Int
    private var balloonsPopped : Int
    private var maxAttempts = 0
    private var gameOver = false

    constructor(length : Int){
        this.numOfBalloons = length
        this.maxAttempts = length + 3
        this.attempts = 0
        this.balloonsPopped = 0
    }

    fun isInsideBalloon(x: Int, y: Int, balloon: Balloon): Boolean {
        val dx = x - balloon.getX()
        val dy = y - balloon.getY()
        val distance = Math.sqrt((dx * dx + dy * dy).toDouble())
        return distance <= balloon.getRadius()
    }

    fun updateAttempt(){
        attempts += 1
    }
    fun updatePopped(){
        balloonsPopped += 1
    }

    fun checkIfWon() : Boolean{
        if (balloonsPopped == numOfBalloons) {
            gameOver = true
            return true
        }
        return false
    }

    fun checkIfLost() : Boolean{
        if (attempts >= maxAttempts) {
            return true
        }
        return false
    }

    fun checkIfGameOver() : Boolean{
        return gameOver
    }
}
