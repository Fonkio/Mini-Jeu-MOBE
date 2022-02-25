package fr.ut3.minijeumobe

import android.graphics.Canvas
import android.os.Handler
import android.view.SurfaceHolder

class GameThread (private val surfaceHolder: SurfaceHolder, private val gameView: GameView) : Thread() {

    override fun run() {
        gameView.update()
        gameView.mHandler.postDelayed(this, 10)
    }
}
