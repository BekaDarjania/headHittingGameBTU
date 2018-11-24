package online.thebekka.thegame2

import android.animation.TimeAnimator
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v4.content.res.ResourcesCompat
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    var score: Int = 0

    internal var gameStarted = false
    internal lateinit var countDownTimer: CountDownTimer
    internal val initialCountDown: Long = 15000
    internal val countDownInterval: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resetGame()
    }

    private fun resetGame() {

        val initialTimerLeft = initialCountDown / 1000

        countDownTimer = object : CountDownTimer(initialCountDown, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished / 1000 // translates miliseconds to seconds
                timeTextView.text = "Time: " + timeLeft.toString() // updates time ui
            }

            override fun onFinish() {
                val intent = Intent(this@MainActivity, Main2Activity::class.java)
                intent.putExtra("score", score.toString())
                startActivity(intent)
            }
        }
        gameStarted = false
        var btns: MutableList<ImageView> = mutableListOf(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8)

        for (item: ImageView in btns) {
            item.tag = "deactive"
            item.setImageDrawable(getDrawable(R.drawable.ic_hole))
        }

        score = 0
        scoreTextView.text = "Score: " + score.toString()
        timeTextView.text = "Time: " + (initialCountDown / countDownInterval).toString()
        chooseRandomGrid() // start new game whe page is created
    }

    fun onClickButton(view: View) {
        if (gameStarted == false) {
            // start a new game
            gameStarted = true
            countDownTimer.start()
        }

        var img: ImageView = view as ImageView

        if (img.tag.toString().equals("active")) {
            img.setImageDrawable(getDrawable(R.drawable.ic_hole))
            // + point
            score++
            scoreTextView.text = "Score: " + score.toString()
            img.tag = "deActive"
            chooseRandomGrid()
        } else {
            // - point
            score -= 5
            scoreTextView.text = "Score: " + score.toString()
        }
    }

    private fun chooseRandomGrid() {
        var randNum: Int = Random().nextInt(9)
        //Toast.makeText(applicationContext,randNum.toString(),Toast.LENGTH_SHORT).show()
        var btns: MutableList<ImageView> = mutableListOf(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8)
        setGridActive(btns[randNum])
    }

    private fun setGridActive(view: ImageView) {
        view.tag = "active"
        view.setImageDrawable(getDrawable(R.drawable.ic_beaver))
    }
}