package com.app.customseekbardemo

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var thumbView: View
    private var overSpeedLimit = 60
    lateinit var seekBar :SeekBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }
    private fun initUI() {
        seekBar = findViewById<SeekBar>(R.id.seekBar)
        thumbView = LayoutInflater.from(this).inflate(R.layout.layout_seekbar_thumb, null, false)
        seekBar.thumb= getThumb(thumbView,60)
        seekBar.progress = 60
        seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener{
                var progress = 0
                override fun onProgressChanged(seekBar: SeekBar, progresValue: Int, fromUser: Boolean) {
                    progress = progresValue
                    seekBar.thumb = getThumb(thumbView, progress)
                    overSpeedLimit = progress
                }
                override fun onStartTrackingTouch(p0: SeekBar) {

                }

                override fun onStopTrackingTouch(p0: SeekBar) {

                }
            }
        )
    }

    // method used for setting textview inside slider
    fun getThumb(thumbView: View, progress: Int): Drawable {
        (thumbView.findViewById(R.id.tvProgress) as TextView).text = progress.toString() + ""
        thumbView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        val bitmap = Bitmap.createBitmap(
            thumbView.getMeasuredWidth(),
            thumbView.getMeasuredHeight(),
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        thumbView.layout(0, 0, thumbView.getMeasuredWidth(), thumbView.getMeasuredHeight())
        thumbView.draw(canvas)
        return BitmapDrawable(resources, bitmap)
    }
}