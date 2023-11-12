package com.example.project7

class Balloon {

    private var x : Int
    private var y : Int
    private var radius : Int

    fun setX(text: String) {
        this.x = text.toInt()
    }
    fun setY(text: String) {
        this.y = text.toInt()
    }
    fun setR(text: String) {
        this.radius = text.toInt()
    }

    constructor(x : Int, y : Int, radius : Int)
    {
        this.x = x
        this.y = y
        this.radius = radius
    }

    fun getX() : Int {return this.x}
    fun getY() : Int {return this.y}
    fun getRadius() : Int {return this.radius}

    override fun toString() : String{
        return "Balloon : "+getX() + " "+getY() + " " + getRadius()
    }
}