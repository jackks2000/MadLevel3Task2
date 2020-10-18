package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapter.PortalAdapter
import com.example.myapplication.Model.Portal
import kotlinx.android.synthetic.main.fragment_reminders.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PortalsFragment : Fragment() {

    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals) { portal: Portal ->
        openPortal(
            portal
        )
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reminders, container,
            false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeAddReminderResult()
    }

    private fun initViews() {
        reminders_rv.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        reminders_rv.adapter = portalAdapter
        reminders_rv.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
    }

    private fun observeAddReminderResult(){
        setFragmentResultListener(PORTAL){ _, bundle ->
            bundle.getParcelable<Portal>(PORTAL)?.let {
                portals.add(it)
                portalAdapter.notifyDataSetChanged()
            } ?: Log.e("RemindersFragment", "Request triggered, but empty reminder text!")
        }
    }

    private fun openPortal(portal: Portal){
        val url = portal.portalURL
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(url)
        startActivity(openURL)
    }
}