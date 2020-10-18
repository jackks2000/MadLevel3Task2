package com.example.myapplication.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.Portal
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemReminderBinding
import kotlinx.android.synthetic.main.item_reminder.view.*

class PortalAdapter (private val portals: List<Portal>, private val clickListener:(Portal) -> Unit):
    RecyclerView.Adapter<PortalAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = ItemReminderBinding.bind(itemView)

        fun databind(portal: Portal, clickListener: (Portal) -> Unit){
            itemView.tvPortalTitle.text = portal.portalTitle
            itemView.tvPortalURL.text = portal.portalURL
            itemView.cardView.setOnClickListener{
                this@PortalAdapter.clickListener(portal)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :
            ViewHolder{
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_reminder,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(portals[position], clickListener)

    }

    override fun getItemCount(): Int {

        return portals.size
    }
}