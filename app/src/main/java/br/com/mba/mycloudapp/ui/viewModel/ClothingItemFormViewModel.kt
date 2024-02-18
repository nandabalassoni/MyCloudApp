package br.com.mba.mycloudapp.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.mba.mycloudapp.model.ClothingItem
import br.com.mba.mycloudapp.repository.ClothingItemRepository

class ClothingItemFormViewModel(private val repository: ClothingItemRepository): ViewModel() {
    fun save(clothingItem: ClothingItem): LiveData<Boolean> = repository.save(clothingItem)
}
