package id.andiwijaya.shoppinglist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_shopping.view.*

class ShoppingItemAdapter(
    private val context: Context,
    private val itemListener: (ShoppingItem) -> Unit,
    private val cartIconListener: (ShoppingItem) -> Unit
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingItemViewHolder>() {

    private var shoppingItems = emptyList<ShoppingItem>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShoppingItemViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_shopping, parent, false)
        return ShoppingItemViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: ShoppingItemViewHolder,
        position: Int
    ) {
        holder.bindItem(context, shoppingItems[position], itemListener, cartIconListener)
    }

    fun setShoppingItems(shoppingItems: List<ShoppingItem>) {
        this.shoppingItems = shoppingItems
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = shoppingItems.size

    class ShoppingItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(
            context: Context,
            shoppingItem: ShoppingItem,
            itemListener: (ShoppingItem) -> Unit,
            cartIconListener: (ShoppingItem) -> Unit
        ) {
            itemView.shoppingItemTV.text = shoppingItem.name
            itemView.shoppingItemTV.setOnClickListener {
                itemListener(shoppingItem)
            }
            itemView.cartIV.setOnClickListener {
                cartIconListener(shoppingItem)
            }

            when (shoppingItem.itemState) {
                0 -> itemView.cartIV.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_cart_add
                    )
                )
                1 -> itemView.cartIV.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_cart_available
                    )
                )
                2 -> itemView.cartIV.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_cart_not_available
                    )
                )
            }
        }
    }

}