package com.example.project7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.InputStream
import javax.xml.parsers.SAXParser
import javax.xml.parsers.SAXParserFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var factory : SAXParserFactory = SAXParserFactory.newInstance()
        var saxParser : SAXParser = factory.newSAXParser()

        var handler : SAXHandler = SAXHandler()
        var iStream : InputStream = resources.openRawResource( R.raw.balloons3 )

        // start parsing
        saxParser.parse( iStream, handler )

        var balloons : ArrayList<Balloon> = handler.getBalloons()
        for( balloon in balloons ) {
            Log.w( "MainActivity", balloon.toString( ) )
        }
    }


}