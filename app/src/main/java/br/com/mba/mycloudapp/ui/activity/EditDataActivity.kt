package br.com.mba.mycloudapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.mba.mycloudapp.R
import br.com.mba.mycloudapp.databinding.ActivityClothingItemFormBinding
import br.com.mba.mycloudapp.model.ClothingItem
import com.google.firebase.firestore.FirebaseFirestore

class EditDataActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()

    private lateinit var binding: ActivityClothingItemFormBinding
    private lateinit var editTextItemName: EditText
    private lateinit var editSpinnerItemType: Spinner
    private lateinit var editSpinnerItemSize: Spinner
    private lateinit var editTextItemColor: EditText
    private lateinit var editTextItemBrand: EditText
    private lateinit var editTextItemPrice: EditText
    private lateinit var editTextItemUrlImage: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClothingItemFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeValues()
        getItensData()
        saveUpdate()
    }

    fun initializeValues() {
        editTextItemName = findViewById(R.id.editTextName)
        editSpinnerItemType = findViewById(R.id.spinnerType)
        editSpinnerItemSize = findViewById(R.id.spinnerSize)
        editTextItemColor = findViewById(R.id.editTextColor)
        editTextItemBrand = findViewById(R.id.editTextBrand)
        editTextItemPrice = findViewById(R.id.editTextPrice)
        editTextItemUrlImage = findViewById(R.id.editTextImageUrl)
    }

    fun getItensData() {
        val itemId = intent.getStringExtra("itemId")

        itemId?.let {
            db.collection("clothing_items").document(it).get()
                .addOnSuccessListener { document ->
                    val item = document.toObject(ClothingItem::class.java)
                    editTextItemName.setText(item?.name)
                    editSpinnerItemType.setSelection(getIndex(editSpinnerItemType, item?.type))
                    editSpinnerItemSize.setSelection(getIndex(editSpinnerItemSize, item?.size))
                    editTextItemColor.setText(item?.color)
                    editTextItemBrand.setText(item?.brand)
                    editTextItemPrice.setText(item?.price.toString())
                    editTextItemUrlImage.setText(item?.imageUrl)
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Erro ao buscar dados do item", Toast.LENGTH_SHORT).show()
                }
        }
    }

    //função usada para definir a posição selecionada dos spinners

    private fun getIndex(spinner: Spinner, value: String?): Int {
        for (i in 0 until spinner.count) {
            if (spinner.getItemAtPosition(i) == value) {
                return i
            }
        }
        return 0
    }

    //função usada para salvar a atualizacao dos dados do item no Firestore

    fun saveUpdate(){
        binding.buttonSave.setOnClickListener{
            val itemId = intent.getStringExtra("itemId")
            val item = getItemDataFromInput()

            itemId?.let {
                updateItemInFirestore(it, item)
            }
        }
    }

    //usado para obter os dados dos campos de entrada

    private fun getItemDataFromInput(): HashMap<String, Any> {
        val name = editTextItemName.text.toString()
        val type = editSpinnerItemType.selectedItem.toString()
        val size = editSpinnerItemSize.selectedItem.toString()
        val color = editTextItemColor.text.toString()
        val brand = editTextItemBrand.text.toString()
        val price = editTextItemPrice.text.toString().toDouble()
        val imageUrl = editTextItemUrlImage.text.toString()

        return hashMapOf(
            "name" to name,
            "type" to type,
            "size" to size,
            "color" to color,
            "brand" to brand,
            "price" to price,
            "imageUrl" to imageUrl
        )
    }

    // usado para atualizar o item no Firestore.

    private fun updateItemInFirestore(itemId: String, item: HashMap<String, Any>) {
        db.collection("clothing_items").document(itemId).update(item)
            .addOnSuccessListener {
                Toast.makeText(this, "Item atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ListDataActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Erro ao atualizar item", Toast.LENGTH_SHORT).show()
            }
    }
}