package com.polish.firebasecontactapp.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Contact(
    val firstName:String? = null,
    val lastName:String? = null,
    val phoneNumber:String? = null
)