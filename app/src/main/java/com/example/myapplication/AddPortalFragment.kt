package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.myapplication.Model.Portal
import kotlinx.android.synthetic.main.fragment_add_reminder.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

const val PORTAL = "portal"
class AddReminderFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_reminder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_reminder_btn.setOnClickListener{
            onAddPortal()
        }

    }


    private fun onAddPortal(){
        val portalTitle = etPortalTitle.text.toString()
        val portalURL = etPortalURL.text.toString()

        val portal: Portal = Portal(portalTitle,portalURL)

        if (portalTitle.isNotBlank() && portalURL.isNotBlank()){
            setFragmentResult(PORTAL,
            bundleOf(PORTAL to portal))

            findNavController().popBackStack()
        } else{
            Toast.makeText(
                activity,
                R.string.not_valid_reminder, Toast.LENGTH_SHORT
            ).show()
        }
    }
}