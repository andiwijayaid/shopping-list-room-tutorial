package id.andiwijaya.shoppinglist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import id.andiwijaya.shoppinglist.MainActivity.Companion.EXTRA_SHOPPING_ITEM
import kotlinx.android.synthetic.main.activity_add_or_edit.*

class AddOrEditActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_REPLY = "EXTRA_REPLY"
        const val EXTRA_RETURN_TYPE = "EXTRA_RETURN_TYPE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_or_edit)

        var shoppingItem = intent.getParcelableExtra<ShoppingItem>(EXTRA_SHOPPING_ITEM)

        if (shoppingItem != null) {
            saveBT.text = applicationContext.getString(R.string.edit)
            shoppingItemET.setText(shoppingItem.name)
        }

        saveBT.setOnClickListener {
            val replyIntent = Intent()

            val mShoppingItem = shoppingItemET.text

            when {
                TextUtils.isEmpty(mShoppingItem) -> {
                    shoppingItemET.error =
                        applicationContext.getString(R.string.error_empty_edit_text)
                    return@setOnClickListener
                }
                shoppingItem != null -> {
                    shoppingItem?.name = mShoppingItem.toString()
                    replyIntent.putExtra(EXTRA_RETURN_TYPE, "update")
                }
                else -> {
                    shoppingItem = ShoppingItem(
                        mShoppingItem.toString(),
                        0
                    )
                    replyIntent.putExtra(EXTRA_RETURN_TYPE, "insert")
                }
            }

            replyIntent.putExtra(EXTRA_REPLY, shoppingItem)
            setResult(Activity.RESULT_OK, replyIntent)

            finish()
        }
    }
}