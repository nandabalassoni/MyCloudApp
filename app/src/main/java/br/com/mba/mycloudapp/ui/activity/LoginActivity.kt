package br.com.mba.mycloudapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.mba.mycloudapp.databinding.LoginBinding
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.digest.DigestUtils
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity: AppCompatActivity() {

    private lateinit var binding: LoginBinding
    private val db = FirebaseFirestore.getInstance()

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
                val userName = binding.editTextUser.text.toString()
                val password = DigestUtils.sha1Hex(binding.editTextPass.text.toString())

                db.collection("user")
                    .whereEqualTo("userName", userName)
                    .whereEqualTo("password", password)
                    .get()
                    .addOnSuccessListener { result ->
                        if(result.isEmpty) {
                            Toast.makeText(
                                this,
                                "Usuário e/ou senha inválidos.",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            val nextScreen = Intent(this, ListDataActivity::class.java)
                            startActivity(nextScreen)
                            finish()
                        }
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(
                            this,
                            "Erro ao tentar fazer login: ${exception.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
            }
        }

        binding.btnSignIng.setOnClickListener {
            val nextScreen = Intent(this, SignInActivity::class.java)
            startActivity(nextScreen)
            finish()
        }
    }
}