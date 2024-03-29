package bes.max.carmaintenance.ui.planned_checks

import androidx.recyclerview.widget.DiffUtil
import bes.max.carmaintenance.domain.models.PlannedCheck

class PlannedCheckDiffItemCallback : DiffUtil.ItemCallback<PlannedCheck>() {
    override fun areItemsTheSame(oldItem: PlannedCheck, newItem: PlannedCheck): Boolean
    = (oldItem.checkId ==newItem.checkId)

    override fun areContentsTheSame(oldItem: PlannedCheck, newItem: PlannedCheck): Boolean
    = (oldItem == newItem)
}