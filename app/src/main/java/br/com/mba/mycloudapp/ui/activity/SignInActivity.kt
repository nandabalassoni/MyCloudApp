package br.com.mba.mycloudapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.mba.mycloudapp.databinding.SignInBinding

class SignInActivity: AppCompatActivity() {

    private lateinit var binding: SignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindings()
        backButtuonClickListener()
    }

    private fun bindings() {
        binding.btnSignInRegister.setOnClickListener {
            when {
                binding.editTextSignInUser.text.toString().isEmpty() -> {
                    showToast("Informe um usuário")
                }
                binding.editTextSignInPass.text.toString().isEmpty() -> {
                    showToast("Informe a senha")
                }
                binding.editTextSignInConfirmPass.text.toString().isEmpty() -> {
                    showToast("Confirme a senha digitando novamente")
                }
                binding.editTextSignInPass.text.toString() != binding.editTextSignInConfirmPass.text.toString() -> {
                    showToast("A senha e a confirmação da senha não são iguais")
                }
                else -> {
                    val nextScreen = Intent(this, CrudActivity::class.java)
                    startActivity(nextScreen)
                    finish()
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun backButtuonClickListener() {
        binding.backButton.setOnClickListener {
            val nextScreen = Intent(this, LoginActivity::class.java)
            startActivity(nextScreen)
        }
    }
}