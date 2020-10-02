package id.andiwijaya.shoppinglist

import androidx.lifecycle.LiveData

class ShoppingItemRepository(private val shoppingItemDao: ShoppingItemDao) {

    val shoppingItems: LiveData<List<ShoppingItem>> = shoppingItemDao.getShoppingItems()

    suspend fun insert(shoppingItem: ShoppingItem) {
        shoppingItemDao.insert(shoppingItem)
    }

    suspend fun update(shoppingItem: ShoppingItem) {
        shoppingItemDao.update(shoppingItem)
    }

    suspend fun delete(shoppingItem: ShoppingItem) {
        shoppingItemDao.delete(shoppingItem)
    }

}