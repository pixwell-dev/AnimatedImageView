package sk.pixwell.android.widget.animatedimageview.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_main.*
import sk.pixwell.android.widget.animatedimageview.animations.RotateAnimation
import sk.pixwell.android.widget.animatedimageview.animations.ScaleAnimation

class MainActivity : AppCompatActivity() {
    private val animations = listOf(
            Pair("Rotate", RotateAnimation()),
            Pair("Scale", ScaleAnimation())
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val adapter = ArrayAdapter<String>(this, R.layout.spinner_item, animations.map { it.first }).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        val actionView = menu?.findItem(R.id.animation)?.actionView
        if (actionView is Spinner) {
            actionView.apply {
                this.adapter = adapter
                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                        addAnimationFromSpinner(pos)
                    }

                    override fun onNothingSelected(parent: AdapterView<out Adapter>?) {

                    }
                }
            }
        }

        return super.onCreateOptionsMenu(menu)
    }

    private fun addAnimationFromSpinner(pos: Int) {
        animatedImageView.apply {
            removeAllAnimations()
            addAnimation(animations[pos].second)
        }
    }
}