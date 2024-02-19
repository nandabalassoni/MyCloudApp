package br.com.mba.mycloudapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.mba.mycloudapp.adapter.ListDataAdapter
import br.com.mba.mycloudapp.databinding.ListDataActivityBinding
import br.com.mba.mycloudapp.model.ClothingItem
import com.google.firebase.firestore.FirebaseFirestore

class ListDataActivity : AppCompatActivity() {
    private lateinit var binding: ListDataActivityBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListDataActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        openListDataAdapter()
        openFormActivity()
    }

    fun openListDataAdapter() {
        val data = mutableListOf<ClothingItem>()
        //Inicia o processo de conexão com Firestore
        db.collection("clothing_items")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val item = document.toObject(ClothingItem::class.java)
                    data.add(item)
                }
                binding.recyclerView.layoutManager = LinearLayoutManager(this)
                binding.recyclerView.adapter = ListDataAdapter(data)
            }
            //Se houver erro, exibe mensagem
            .addOnFailureListener { exception ->
                Toast.makeText(
                    this,
                    "Erro ao buscar dados: ${exception.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
    }

    fun openFormActivity() {
        binding.crudFab.setOnClickListener{
            val nextScreen = Intent(this, ClothingItemFormActivity::class.java)
            startActivity(nextScreen)
        }
    }
}