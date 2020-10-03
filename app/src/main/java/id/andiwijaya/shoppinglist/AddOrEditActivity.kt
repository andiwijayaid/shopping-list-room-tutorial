package id.andiwijaya.shoppinglist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_or_edit.*

class AddOrEditActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_REPLY = "EXTRA_REPLY"
        const val EXTRA_RETURN_TYPE = "EXTRA_RETURN_TYPE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_or_edit)

        saveBT.setOnClickListener {
            val replyIntent = Intent()

            val mShoppingItem = shoppingItemET.text

            when {
                TextUtils.isEmpty(mShoppingItem) -> {
                    shoppingItemET.error =
                        applicationContext.getString(R.string.error_empty_edit_text)
                    return@setOnClickListener
                }
                else -> {
                    replyIntent.putExtra(EXTRA_REPLY, ShoppingItem(mShoppingItem.toString(), 0))
                    setResult(Activity.RESULT_OK, replyIntent)
                }
            }

            finish()
        }
    }
}