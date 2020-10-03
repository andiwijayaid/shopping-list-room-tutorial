package id.andiwijaya.shoppinglist

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.andiwijaya.shoppinglist.AddOrEditActivity.Companion.EXTRA_REPLY
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var shoppingItemViewModel: ShoppingItemViewModel
    private val newShoppingItemActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shoppingItemViewModel = ViewModelProvider(this).get(ShoppingItemViewModel::class.java)

        val adapter = ShoppingItemAdapter(this, {

        }, {
            when(it.itemState) {
                0 -> it.itemState = 1
                1 -> it.itemState = 2
                2 -> it.itemState = 0
            }
            shoppingItemViewModel.update(it)
        })

        shoppingItemViewModel.shoppingItems.observe(this, {
            adapter.setShoppingItems(it)
        })
        shoppingRV.adapter = adapter
        shoppingRV.layoutManager = LinearLayoutManager(this)

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newShoppingItemActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getParcelableExtra<ShoppingItem>(EXTRA_REPLY)?.let {
                shoppingItemViewModel.insert(it)
            }
        }
    }
}