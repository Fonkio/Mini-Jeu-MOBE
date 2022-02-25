package fr.ut3.minijeumobe

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameView(val mainActivity: MainActivity) : SurfaceView(mainActivity), SurfaceHolder.Callback {

    private val thread : GameThread
    private var x = 0
    private val sharedPref = mainActivity.getPreferences(Context.MODE_PRIVATE)

    init {
        holder.addCallback(this)
        thread = GameThread(holder, this)
        isFocusable = true
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        thread.running = true
        thread.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        while (retry) {
            try {
                thread.running = false
                thread.join()
            } catch (e : InterruptedException) {
                e.printStackTrace()
            }
            retry = false
        }
    }

    override fun draw(canvas: Canvas) {
        var valeur_y = sharedPref.getInt("valeur_y", 0)

        super.draw(canvas)
        canvas.drawColor(Color.WHITE)
        val paint = Paint()
        paint.color = Color.rgb(250, 0, 0)
        canvas.drawRect(x+0F, (valeur_y*100F), x+100F, (valeur_y*100F)+200F, paint)
    }

    fun update() {
        x = (x + 1) % 300
    }

}
