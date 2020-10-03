package id.andiwijaya.shoppinglist

import androidx.lifecycle.LiveData

class ShoppingItemRepository(private val shoppingItemDao: ShoppingItemDao) {

    val shoppingItems: LiveData<List<ShoppingItem>> =shoppingItemDao.getAlphabetizedShoppingItems()

    suspend fun insert(shoppingItem: ShoppingItem) {
        shoppingItemDao.insert(shoppingItem)
    }

    suspend fun update(shoppingItem: ShoppingItem) {
        shoppingItemDao.updadate(shoppingItem)
    }

}