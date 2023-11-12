package com.example.project7

import android.util.Log
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler

class SAXHandler : DefaultHandler( ) {

    private var balloons : ArrayList<Balloon> = ArrayList<Balloon>()
    private var currentItem : Balloon? = null
    private var element : String = ""
    private var validText : Boolean = false

    override fun startElement( uri: String?, localName: String?, qName: String?, attributes: Attributes?) {
        super.startElement(uri, localName, qName, attributes)
        //Log.w( "MainActivity", "startElement = " + qName )
        element = qName!!
        validText = true
        if( qName.equals( "balloon" ) )
            currentItem = Balloon( -1,-1,-1)
    }

    override fun endElement(uri: String?, localName: String?, qName: String?) {
        super.endElement(uri, localName, qName)
        //Log.w( "MainActivity", "endElement = " + qName )
        validText = false
        if( qName != null && currentItem != null && qName!!.equals( "balloon" ) )
            balloons.add( currentItem!! )
    }

    override fun characters(ch: CharArray?, start: Int, length: Int) {
        super.characters(ch, start, length)
        if( ch != null ) {
            var text : String = String( ch!!, start, length )
            //Log.w( "MainActivity", "text is " + text )

            if( currentItem != null && validText && element.equals( "x" ) )
                currentItem!!.setX( text )
            else if( currentItem != null && validText && element.equals( "y" ) )
                currentItem!!.setY( text )
            else if( currentItem != null && validText && element.equals( "radius" ) )
                currentItem!!.setR( text )
        }
    }

    fun getBalloons( ) : ArrayList<Balloon> {
        return balloons
    }

}