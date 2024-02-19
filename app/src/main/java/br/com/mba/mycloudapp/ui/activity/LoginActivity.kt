package br.com.mba.mycloudapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.mba.mycloudapp.databinding.LoginBinding

class LoginActivity: AppCompatActivity() {

    private lateinit var binding: LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindings()
    }

    private fun bindings() {
        binding.btnLogin.setOnClickListener {
            if(binding.editTextUser.text.toString().isEmpty()) {
                Toast.makeText(this, "Informe o usuário", Toast.LENGTH_LONG).show()
            } else if(binding.editTextPass.text.toString().isEmpty()) {
                Toast.makeText(this, "Informe a senha", Toast.LENGTH_LONG).show()
            } else {
                val nextScreen = Intent(this, CrudActivity::class.java)
                startActivity(nextScreen)
                finish()
            }
        }

        binding.btnSignIng.setOnClickListener {
            val nextScreen = Intent(this, SignInActivity::class.java)
            startActivity(nextScreen)
            finish()
        }
    }
}