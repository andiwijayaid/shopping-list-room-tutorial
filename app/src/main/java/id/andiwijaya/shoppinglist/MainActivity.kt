package id.andiwijaya.shoppinglist

import android.app.Activity
import android.app.ActivityOptions
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.andiwijaya.shoppinglist.AddOrEditActivity.Companion.EXTRA_REPLY
import id.andiwijaya.shoppinglist.AddOrEditActivity.Companion.EXTRA_RETURN_TYPE
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var shoppingItemViewModel: ShoppingItemViewModel
    private val newShoppingItemActivityRequestCode = 1

    companion object {
        const val EXTRA_SHOPPING_ITEM = "EXTRA_SHOPPING_ITEM"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menuItems = arrayListOf(
            applicationContext.getString(R.string.edit),
            applicationContext.getString(R.string.delete)
        )

        shoppingItemViewModel = ViewModelProvider(this).get(ShoppingItemViewModel::class.java)

        val adapter = ShoppingItemAdapter(this, { shoppingItem ->
            val arrayAdapter = ArrayAdapter(this, android.R.layout.select_dialog_item, menuItems)
            val builder = AlertDialog.Builder(this)
            builder.setAdapter(arrayAdapter) { dialogInterface, i ->
                when (i) {
                    0 -> {
                        val intent = Intent(this@MainActivity, AddOrEditActivity::class.java)
                        intent.putExtra(EXTRA_SHOPPING_ITEM, shoppingItem)
                        startActivityForResult(intent, newShoppingItemActivityRequestCode)
                    }
                    1 -> shoppingItemViewModel.delete(shoppingItem)
                }
                dialogInterface.dismiss()
            }
            val alertDialog = builder.create()
            alertDialog.show()
        }, {
            when (it.itemState) {
                0 -> it.itemState = 1
                1 -> it.itemState = 2
                2 -> it.itemState = 0
            }
            shoppingItemViewModel.update(it)
        })

        shoppingItemViewModel.shoppingItems.observe(this, { shoppingItems ->
            adapter.setShoppingItems(shoppingItems)
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

            when (data?.getStringExtra(EXTRA_RETURN_TYPE)) {
                "insert" -> {
                    data.getParcelableExtra<ShoppingItem>(EXTRA_REPLY)?.let { shoppingItem ->
                        shoppingItemViewModel.insert(shoppingItem)
                    }
                }
                "update" -> {
                    data.getParcelableExtra<ShoppingItem>(EXTRA_REPLY)?.let { shoppingItem ->
                        shoppingItemViewModel.update(shoppingItem)
                    }
                }
            }

        }
    }
}