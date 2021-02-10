package com.polish.firebasecontactapp.database

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object Storage {
    /**
     * instantiate the realtime database
     */
    fun provideDatabaseInstance():DatabaseReference{
        return Firebase.database.reference
    }
}