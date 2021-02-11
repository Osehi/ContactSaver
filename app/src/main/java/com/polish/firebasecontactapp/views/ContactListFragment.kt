package com.polish.firebasecontactapp.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.polish.firebasecontactapp.R
import com.polish.firebasecontactapp.adapter.ContactListAdapter
import com.polish.firebasecontactapp.contants.Constant
import com.polish.firebasecontactapp.database.Storage
import com.polish.firebasecontactapp.databinding.FragmentContactListBinding
import com.polish.firebasecontactapp.model.Contact


/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class ContactListFragment : Fragment() {
    val TAG ="CONTACT_LIST_FRAGMENT"

    /**
     * view declaration
     */
    lateinit var fab:FloatingActionButton
    lateinit var displatContact:RecyclerView
    lateinit var contactSourceAdapter:ContactListAdapter
    lateinit var retrievedList:ArrayList<Contact>

    private var _binding:FragmentContactListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_contact_list, container, false)
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        val view = binding.root
        basicQueryValueListener()
        /**
         * initialize views
         */
        displatContact = binding.fragContactListViewRv

        /**
         * to add contact
         */
        binding.fragContactListFab.setOnClickListener {
            findNavController().navigate(R.id.addContacFragment)
        }
        /**
         * initialize list
         */
        retrievedList = ArrayList()
        Log.d(TAG, "in the fragment:$retrievedList")

        /**
         * set add to the recyclerview
         */
        displatContact.layoutManager = LinearLayoutManager(requireContext())


        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun basicQueryValueListener(){
        val contactQuery = Storage.provideDatabaseInstance().child(Constant.USER).orderByValue()
        contactQuery.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (contactSnapshot in snapshot.children){
                    val singleContact = contactSnapshot.getValue(Contact::class.java)
                    retrievedList.add(singleContact!!)
                    contactSourceAdapter = ContactListAdapter(retrievedList)
                    contactSourceAdapter.notifyDataSetChanged()
                    displatContact.adapter = contactSourceAdapter
                    Log.d(TAG, "output is ${retrievedList}")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "here is the error message:$error")
            }

        })
    }


}