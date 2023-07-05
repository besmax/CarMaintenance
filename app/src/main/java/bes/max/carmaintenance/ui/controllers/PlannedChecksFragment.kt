package bes.max.carmaintenance.ui.controllers

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import bes.max.carmaintenance.BaseApplication
import bes.max.carmaintenance.R
import bes.max.carmaintenance.databinding.FragmentPlannedChecksBinding
import bes.max.carmaintenance.ui.PlannedCheckItemAdapter
import bes.max.carmaintenance.ui.viewmodels.PlannedChecksViewModel
import bes.max.carmaintenance.ui.viewmodels.PlannedChecksViewModelFactory
import com.google.android.material.color.MaterialColors
import kotlin.math.roundToInt

class PlannedChecksFragment : Fragment() {

    private var _binding: FragmentPlannedChecksBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private val adapter = PlannedCheckItemAdapter()

    private val viewModel: PlannedChecksViewModel by viewModels {
        PlannedChecksViewModelFactory(
            (activity?.application as BaseApplication).checkDatabase.plannedCheckDao
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlannedChecksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.fragmentPlannedChecksRecyclerview
        recyclerView.adapter = adapter

        val itemTouchHelper = ItemTouchHelper(getSwipeCallback())
        itemTouchHelper.attachToRecyclerView(recyclerView)

        viewModel.plannedChecks.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getSwipeCallback(): ItemTouchHelper.SimpleCallback {
        return object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.RIGHT
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deletePlannedCheck(adapter.getItemByPosition(viewHolder.absoluteAdapterPosition))
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val trashBinIcon = requireContext().getDrawable(R.drawable.ic_delete_24)

                c.clipRect(
                    0f, viewHolder.itemView.top.toFloat(),
                    dX, viewHolder.itemView.bottom.toFloat()
                )

                // TODO get color from theme
                val currentTheme = requireContext().theme
                val arr = intArrayOf(com.google.android.material.R.attr.colorOnSecondary)
                val typedArray = currentTheme.obtainStyledAttributes(arr)
                val colorOnSecondary = typedArray.getColor(0, 0)
//??
                if (dX < viewHolder.itemView.width / 3)
                    c.drawColor(
                        MaterialColors.getColor(
                            requireContext(),
                            com.google.android.material.R.attr.colorOnSecondary,
                            Color.BLACK
                        )
                    )
                else
                    c.drawColor(Color.RED)

                val listItemHeight =
                    resources.getDimension(R.dimen.fragment_planned_checks_ic_delete_height)
                        .roundToInt()

                if (trashBinIcon != null) {
                    trashBinIcon.bounds = Rect(
                        listItemHeight,
                        viewHolder.itemView.top + listItemHeight,
                        listItemHeight + trashBinIcon.intrinsicWidth,
                        viewHolder.itemView.top + trashBinIcon.intrinsicHeight
                                + listItemHeight
                    )
                }

                trashBinIcon?.draw(c)

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }

        }
    }


}