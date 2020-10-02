package id.andiwijaya.shoppinglist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ShoppingItem::class], version = 1)
abstract class ShoppingRoomDatabase : RoomDatabase() {

    abstract fun shoppingItemDao(): ShoppingItemDao

    companion object {
        @Volatile
        private var INSTANCE: ShoppingRoomDatabase? = null

        fun getDatabase(
            context: Context
        ): ShoppingRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShoppingRoomDatabase::class.java,
                    "shopping_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}