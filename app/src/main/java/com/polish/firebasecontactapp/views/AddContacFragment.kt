package com.polish.firebasecontactapp.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.polish.firebasecontactapp.R
import com.polish.firebasecontactapp.contants.Constant
import com.polish.firebasecontactapp.database.Storage
import com.polish.firebasecontactapp.databinding.FragmentAddContacBinding
import com.polish.firebasecontactapp.model.Contact
import com.polish.firebasecontactapp.utility.isNotNullOrEmpty


/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class AddContacFragment : Fragment() {
    val TAG = "ADD_CONTACT_FRAGMENT"

    /**
     * declaration
     */
    private lateinit var firstName:String
    private lateinit var lastName:String
    private lateinit var phoneNumber:String
    private lateinit var addContact:Button

    private var _binding:FragmentAddContacBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_add_contac, container, false)
        _binding = FragmentAddContacBinding.inflate(inflater, container, false)
        val view = binding.root

        /**
         * initialize views
         */
        firstName = binding.fragAddContactFirstNameEdt.text.toString()
        lastName = binding.fragAddContactLastNameEdt.text.toString()
        phoneNumber = binding.fragAddContactPhoneNumberEdt.text.toString()
        addContact = binding.fragAddContactButtonBtn

        /**
         * add to save to database
         */
        binding.fragAddContactButtonBtn.setOnClickListener {
            // valid user
            val validUser = captureInput()
            Log.d(TAG, "content of validUser:$validUser")
            // call the instance of the database
            Storage.provideDatabaseInstance().child(Constant.USER).push().setValue(validUser)
            findNavController().navigate(R.id.contactListFragment)
        }

        return  view
    }

    private fun captureInput():Contact{
        // capture the user contanct
        var user = Contact()
        // check if input is empty
        if (binding.fragAddContactFirstNameEdt.isNotNullOrEmpty("Please enter firstname") &&
                (binding.fragAddContactLastNameEdt.isNotNullOrEmpty("Please enter lastname")) &&
                binding.fragAddContactPhoneNumberEdt.isNotNullOrEmpty("Please enter phonenumber")){
            firstName = binding.fragAddContactFirstNameEdt.text.toString()
            lastName = binding.fragAddContactLastNameEdt.text.toString()
            phoneNumber = binding.fragAddContactPhoneNumberEdt.text.toString()
            user = Contact(firstName, lastName, phoneNumber)
            Log.d(TAG, "user info collection point:$user")
        }
        return user
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}