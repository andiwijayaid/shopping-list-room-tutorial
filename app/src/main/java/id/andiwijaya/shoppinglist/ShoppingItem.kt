package id.andiwijaya.shoppinglist

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "shopping_item_table")
data class ShoppingItem(
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "item_state")
    var itemState: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
) : Parcelable