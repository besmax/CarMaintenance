package bes.max.carmaintenance.ui.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import bes.max.carmaintenance.BaseApplication
import bes.max.carmaintenance.databinding.FragmentPlannedChecksBinding
import bes.max.carmaintenance.ui.PlannedCheckItemAdapter
import bes.max.carmaintenance.ui.viewmodels.PlannedChecksViewModel
import bes.max.carmaintenance.ui.viewmodels.PlannedChecksViewModelFactory

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        viewModel.plannedChecks.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}