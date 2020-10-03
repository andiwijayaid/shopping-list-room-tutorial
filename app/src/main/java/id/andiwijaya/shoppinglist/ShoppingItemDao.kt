package id.andiwijaya.shoppinglist

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingItemDao {

    @Query("SELECT * FROM shopping_item_table ORDER BY name ASC")
    fun getAlphabetizedShoppingItems(): LiveData<List<ShoppingItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(shoppingItem: ShoppingItem)

    @Update
    suspend fun updadate(shoppingItem: ShoppingItem)

}