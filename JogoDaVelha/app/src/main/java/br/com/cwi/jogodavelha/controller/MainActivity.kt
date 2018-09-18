package br.com.cwi.jogodavelha.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import br.com.cwi.jogodavelha.R
import br.com.cwi.jogodavelha.models.Board
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var model: Board? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        model = Board()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_tictactoe, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_reset -> {
                reset()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun onCellClicked(v: View) {

        val button = v as Button

        val tag = button.tag.toString()
        val row = Integer.valueOf(tag.substring(0, 1))
        val col = Integer.valueOf(tag.substring(1, 2))
        Log.i(TAG, "Click Row: [$row,$col]")

        val playerThatMoved = model!!.mark(row, col)

        if (playerThatMoved != null) {
            button.text = playerThatMoved.toString()
            if (model!!.winner != null) {
                winnerPlayerLabel?.text = playerThatMoved.toString()
                winnerPlayerViewGroup?.visibility = View.VISIBLE
            }
        }

    }

    private fun reset() {
        winnerPlayerViewGroup!!.visibility = View.GONE
        winnerPlayerLabel!!.text = ""

        model!!.restart()

        for (i in 0 until buttonGrid!!.childCount) {
            (buttonGrid!!.getChildAt(i) as Button).text = ""
        }
    }

    companion object {

        private val TAG = MainActivity::class.java.name
    }

}
