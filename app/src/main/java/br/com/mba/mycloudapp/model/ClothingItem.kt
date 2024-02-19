package br.com.mba.mycloudapp.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ClothingItem (
    var id: String = "",
    var name: String = "",
    var type: String = "",
    var size: String = "",
    var color: String = "",
    var brand: String = "",
    var price : Double = 0.0,
    var imageUrl: String = ""
) : Parcelable

