package online.thebekka.thegame2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val extras = intent.extras
        if (extras != null) {
            val score = extras.getString("score")
            textView.text = "game over! \n your score: " + score
        }

        button.setOnClickListener {
            val intent = Intent(this@Main2Activity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
