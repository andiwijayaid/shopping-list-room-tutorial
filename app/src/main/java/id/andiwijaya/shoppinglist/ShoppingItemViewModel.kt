package id.andiwijaya.shoppinglist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingItemViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ShoppingItemRepository

    val shoppingItems: LiveData<List<ShoppingItem>>

    init {
        val shoppingItemDao = ShoppingRoomDatabase.getDatabase(application).shoppingItemDao()
        repository = ShoppingItemRepository(shoppingItemDao)
        shoppingItems = repository.shoppingItems
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(shoppingItem: ShoppingItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(shoppingItem)
    }

    fun update(shoppingItem: ShoppingItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(shoppingItem)
    }

    fun delete(shoppingItem: ShoppingItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(shoppingItem)
    }

}