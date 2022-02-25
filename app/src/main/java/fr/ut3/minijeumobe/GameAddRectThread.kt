package fr.ut3.minijeumobe

import android.graphics.RectF
import android.view.SurfaceHolder

class GameAddRectThread (private val surfaceHolder: SurfaceHolder, private val gameView: GameView) : Thread() {

    override fun run() {
        var valeur_y = gameView.sharedPref.getInt("valeur_y", 0)
        gameView.listRect.add(RectF(0F, (valeur_y)*100F, 100F, (valeur_y*100F)+100F))
        gameView.mHandler.postDelayed(this, 2000)
    }

}
