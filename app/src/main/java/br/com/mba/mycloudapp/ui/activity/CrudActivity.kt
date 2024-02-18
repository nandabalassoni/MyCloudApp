package br.com.mba.mycloudapp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.mba.mycloudapp.databinding.CrudBinding

class CrudActivity: AppCompatActivity() {
    private lateinit var binding: CrudBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CrudBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.crudFab.setOnClickListener{
            val nextScreen = Intent(this, ClothingItemFormActivity::class.java)
            startActivity(nextScreen)
            finish()
        }
    }
}