package com.polish.firebasecontactapp.helper

import com.polish.firebasecontactapp.model.Contact

interface OnItemClickListener {
    /**
     * this function handles on click on an item
     */
    fun onClick(item:Contact)
}