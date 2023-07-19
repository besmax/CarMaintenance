package bes.max.carmaintenance.ui.checks

import androidx.recyclerview.widget.DiffUtil
import bes.max.carmaintenance.domain.models.Check

class CheckDiffItemCallback : DiffUtil.ItemCallback<Check>() {
    override fun areItemsTheSame(oldItem: Check, newItem: Check): Boolean = (oldItem.checkId == newItem.checkId)

    override fun areContentsTheSame(oldItem: Check, newItem: Check): Boolean = (oldItem == newItem)
}