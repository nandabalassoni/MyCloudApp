package br.com.mba.mycloudapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.mba.mycloudapp.databinding.SignInBinding
import br.com.mba.mycloudapp.model.User
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.digest.DigestUtils
import com.google.firebase.firestore.FirebaseFirestore

class SignInActivity: AppCompatActivity() {

    private lateinit var binding: SignInBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindings()
        backButtonClickListener()
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
                    val user = User(
                        id = java.util.UUID.randomUUID().toString(),
                        userName = binding.editTextSignInUser.text.toString(),
                        password = DigestUtils.sha1Hex(binding.editTextSignInPass.text.toString())
                    )

                    db.collection("user")
                        .document()
                        .set(user)
                        .addOnSuccessListener {
                            val nextScreen = Intent(this, ListDataActivity::class.java)
                            startActivity(nextScreen)
                            finish()
                        }
                        .addOnFailureListener { exception ->
                            Toast.makeText(
                                this,
                                "Erro ao tentar cadastrar novo usuário: ${exception.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun backButtonClickListener() {
        binding.backButton.setOnClickListener {
            val nextScreen = Intent(this, LoginActivity::class.java)
            startActivity(nextScreen)
            finish()
        }
    }
}