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
    }

    private fun bindings() {
        binding.btnSignInRegister.setOnClickListener {
            if(binding.editTextSignInUser.text.toString().isEmpty()) {
                Toast.makeText(this, "Informe um usuário", Toast.LENGTH_LONG).show()
            } else if(binding.editTextSignInPass.text.toString().isEmpty()) {
                Toast.makeText(this, "Informe a senha", Toast.LENGTH_LONG).show()
            } else if(binding.editTextSignInConfirmPass.text.toString().isEmpty()) {
                Toast.makeText(this, "Confirme a senha digitando novamente", Toast.LENGTH_LONG).show()
            } else if(!binding.editTextSignInPass.text.toString().equals(binding.editTextSignInConfirmPass.text.toString())) {
                Toast.makeText(this, "A senha e a confirmação da senha não são iguais", Toast.LENGTH_LONG).show()
            } else {
                val nextScreen = Intent(this, CrudActivity::class.java)
                startActivity(nextScreen)
                finish()
            }
        }
    }
}