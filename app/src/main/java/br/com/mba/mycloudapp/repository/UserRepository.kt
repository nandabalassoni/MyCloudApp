package br.com.mba.mycloudapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.mba.mycloudapp.model.User
import com.google.firebase.firestore.FirebaseFirestore

private const val FIRESTORE_USER = "user"

class UserRepository (
    private val firestore: FirebaseFirestore
) {
    fun save(user: User): LiveData<Boolean> = MutableLiveData<Boolean>().apply {
        val doc = firestore.collection(FIRESTORE_USER).document()
        val user = User(
            id = doc.id,
            userName = user.userName,
            password = user.password
        )

        doc.set(user)

        value = true
    }
}