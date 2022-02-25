package fr.ut3.minijeumobe

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.view.WindowManager


class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(GameView(this))

        val sharedPref = getPreferences(Context.MODE_PRIVATE)

        var valeur_y = sharedPref.getInt("valeur_y", 0)
        valeur_y = (valeur_y+1) % 4

        val editor = sharedPref.edit()
        editor.putInt("valeur_y", valeur_y)
        editor.apply()
    }
}