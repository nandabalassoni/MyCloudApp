package br.com.mba.mycloudapp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.mba.mycloudapp.databinding.ActivityClothingItemFormBinding
import br.com.mba.mycloudapp.model.ClothingItem
import br.com.mba.mycloudapp.ui.viewModel.ClothingItemFormViewModel
import com.google.firebase.FirebaseApp
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.NumberFormatException


class ClothingItemFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityClothingItemFormBinding
    private val viewModel: ClothingItemFormViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClothingItemFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        saveButtuonClickListener()
        backButtuonClickListener()

    }

    private fun saveButtuonClickListener() {

        //primeiro faz a validacao caso o nome esteja vazio
        binding.buttonSave.setOnClickListener{
            val name: String = binding.editTextName.text.toString()
            if (name.isEmpty()) {
                binding.editTextName.error = "Name cannot be empty"
                return@setOnClickListener
            }
            val type: String = binding.spinnerType.selectedItem.toString()
            val size: String = binding.spinnerSize.selectedItem.toString()
            val color: String = binding.editTextColor.text.toString()
            val brand: String = binding.editTextBrand.text.toString()
            val price: Double = try { binding.editTextPrice.text.toString().toDouble() }
            catch(e: NumberFormatException) { 0.0 }
            val imageUrl: String = binding.editTextImageUrl.text.toString()
            val clothingItem = ClothingItem(
                name = name,
                type = type,
                size = size,
                color = color,
                brand = brand,
                price = price,
                imageUrl = imageUrl)

            //depois de validado, salva o item

            viewModel.save(clothingItem).observe(this){
                it?.let {saved ->
                    if(saved) {
                        val nextScreen = Intent(this, ListDataActivity::class.java)
                        startActivity(nextScreen)
                        return@observe
                    }
                    //se nao foi salvo, exibe mensagem de erro
                    Toast.makeText(this,"Failed to save product",Toast.LENGTH_SHORT)
                }
            }
        }
    }

    private fun backButtuonClickListener() {
        binding.backButton.setOnClickListener {
            val nextScreen = Intent(this, CrudActivity::class.java)
            startActivity(nextScreen)
        }
    }
}

