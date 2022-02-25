package fr.ut3.minijeumobe

import android.graphics.Canvas
import android.view.SurfaceHolder

class GameDrawThread (private val surfaceHolder: SurfaceHolder, private val gameView: GameView) : Thread() {

    override fun run() {
        var canvas : Canvas? = null
        try {
            canvas = surfaceHolder.lockCanvas()
            synchronized(surfaceHolder) {
                gameView.draw(canvas)
            }
        } catch (e: Exception) {
        } finally {
            if (canvas != null) {
                try {
                    surfaceHolder.unlockCanvasAndPost(canvas)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        gameView.mHandler.postDelayed(this, 50)
    }


}
