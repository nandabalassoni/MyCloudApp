package br.com.mba.mycloudapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.mba.mycloudapp.model.ClothingItem
import com.google.firebase.firestore.FirebaseFirestore

private const val  FIRESTORE_CLOTHING_ITEMS_COLLECTION = "clothing_items"
class ClothingItemRepository (
    private val firestore: FirebaseFirestore
){
    fun save(clothingItem: ClothingItem): LiveData<Boolean> = MutableLiveData<Boolean>().apply {
        val document = firestore.collection(FIRESTORE_CLOTHING_ITEMS_COLLECTION).document()
        val clothingItem = ClothingItem(
            id = document.id,
            name = clothingItem.name,
            type = clothingItem.type,
            size = clothingItem.size,
            color = clothingItem.color,
            brand = clothingItem.brand,
            price = clothingItem.price,
            imageUrl = clothingItem.imageUrl
        )

        document.set(clothingItem)

        value = true
    }

}
