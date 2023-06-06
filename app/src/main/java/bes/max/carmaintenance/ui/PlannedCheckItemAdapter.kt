package bes.max.carmaintenance.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import bes.max.carmaintenance.R
import bes.max.carmaintenance.model.PlannedCheck

class PlannedCheckItemAdapter() :
    ListAdapter<PlannedCheck, PlannedCheckItemAdapter.PlannedCheckItemViewHolder>(
        PlannedCheckDiffItemCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlannedCheckItemViewHolder
    = PlannedCheckItemViewHolder(parent)

    override fun onBindViewHolder(holder: PlannedCheckItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PlannedCheckItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.planned_check_item, parent, false)
    ) {

        fun bind(plannedCheck: PlannedCheck) {
            val date = itemView.findViewById<TextView>(R.id.planned_check_item_date)
            val name = itemView.findViewById<TextView>(R.id.planned_check_item_name)
            date.text = plannedCheck.checkDate
            name.text = plannedCheck.checkName
        }
    }

}