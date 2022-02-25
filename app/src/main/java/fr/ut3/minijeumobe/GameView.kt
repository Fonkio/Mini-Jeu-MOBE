package fr.ut3.minijeumobe

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Handler
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameView(mainActivity: MainActivity) : SurfaceView(mainActivity), SurfaceHolder.Callback {

    private var gameDrawThread : GameDrawThread
    private var gameThread : GameThread
    private var gameAddRectThread : GameAddRectThread
    val sharedPref = mainActivity.getPreferences(Context.MODE_PRIVATE)
    val mHandler = Handler()
    val listRect = mutableListOf<RectF>()

    init {
        holder.addCallback(this)
        gameDrawThread = GameDrawThread(holder, this)
        gameThread = GameThread(holder, this)
        gameAddRectThread = GameAddRectThread(holder, this)
        isFocusable = true
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        mHandler.postDelayed(gameDrawThread, 0)
        mHandler.postDelayed(gameThread, 0)
        mHandler.postDelayed(gameAddRectThread, 0)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        while (retry) {
            try {
                gameDrawThread.join()
                gameThread.join()
                gameAddRectThread.join()
            } catch (e : InterruptedException) {
                e.printStackTrace()
            }
            retry = false
        }
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        canvas.drawColor(Color.WHITE)
        val paint = Paint()
        paint.color = Color.rgb(250, 0, 0)
        for (rect in listRect) {
            canvas.drawRect(rect, paint)
        }
    }

    fun update() {
        for (rect in listRect) {
            var x = rect.left
            x = (x + 1) % this.width
            rect.left = x + 0F
            rect.right = x+100F
        }
    }

}
