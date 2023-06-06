package bes.max.carmaintenance.ui

import androidx.recyclerview.widget.DiffUtil
import bes.max.carmaintenance.model.PlannedCheck

class PlannedCheckDiffItemCallback : DiffUtil.ItemCallback<PlannedCheck>() {
    override fun areItemsTheSame(oldItem: PlannedCheck, newItem: PlannedCheck): Boolean
    = (oldItem.checkId ==newItem.checkId)

    override fun areContentsTheSame(oldItem: PlannedCheck, newItem: PlannedCheck): Boolean
    = (oldItem == newItem)
}