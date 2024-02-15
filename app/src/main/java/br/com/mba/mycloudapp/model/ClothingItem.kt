package br.com.mba.mycloudapp.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ClothingItem (
    val id: Int = 0,
    var name: String = "",
    var type: Type,
    var size: Size,
    var color: String = "",
    var brand: String = "",
    var price : Double = 0.0,
    var imageUrl: String = ""
) : Parcelable

enum class Type {
    SHIRT,
    PANTS,
    DRESS,
    SHOES,
    JACKET,
    HAT,
    ACCESSORY
}

enum class Size {
    XS,
    S,
    M,
    L,
    XL,
    XXL
}