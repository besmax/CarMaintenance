package bes.max.carmaintenance.ui.checks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import bes.max.carmaintenance.databinding.CheckItemBinding
import bes.max.carmaintenance.domain.models.Check

class CheckItemAdapter(
    private val doOnClick: (checkPosition: Int) -> Unit
) : ListAdapter<Check, CheckItemAdapter.CheckItemViewHolder>(
    CheckDiffItemCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            CheckItemViewHolder = CheckItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: CheckItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener { doOnClick.invoke(position) }
    }

    class CheckItemViewHolder(val binding: CheckItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {

            fun inflateFrom(parent: ViewGroup): CheckItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CheckItemBinding.inflate(layoutInflater, parent, false)
                return CheckItemViewHolder(binding)
            }
        }

        fun bind(item: Check) {
            binding.check = item
        }
    }
}