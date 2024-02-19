package br.com.mba.mycloudapp.model

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class User (
    var id: String = "",
    var userName: String = "",
    var password: String = ""
)