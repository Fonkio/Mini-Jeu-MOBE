package fr.ut3.minijeumobe

import android.graphics.Canvas
import android.view.SurfaceHolder

class GameThread (private val surfaceHolder: SurfaceHolder, private val gameView: GameView) : Thread() {

    var running = false

    override fun run() {
        while (running) {
            var canvas : Canvas? = null
            try {
                canvas = surfaceHolder.lockCanvas()
                synchronized(surfaceHolder) {
                    gameView.update()
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
        }
    }


}
