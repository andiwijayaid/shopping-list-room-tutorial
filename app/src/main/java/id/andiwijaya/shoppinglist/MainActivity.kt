package id.andiwijaya.shoppinglist

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val newShoppingItemActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddOrEditActivity::class.java)

            // For transition
            val pair =
                Pair<View, String>(iconIV, applicationContext.getString(R.string.transition_name))
            val activityOptions = ActivityOptions.makeSceneTransitionAnimation(this, pair)

            startActivityForResult(
                intent,
                newShoppingItemActivityRequestCode,
                activityOptions.toBundle()
            )
        }
    }
}