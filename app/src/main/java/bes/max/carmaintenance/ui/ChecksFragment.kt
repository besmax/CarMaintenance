package bes.max.carmaintenance.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import bes.max.carmaintenance.BaseApplication
import bes.max.carmaintenance.databinding.FragmentChecksBinding

class ChecksFragment : Fragment() {

    private var _binding: FragmentChecksBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private val viewModel: ChecksViewModel by activityViewModels {
        ChecksViewModelFactory(
            (activity?.application as BaseApplication).checkDatabase.checkDao
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChecksBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.checksList

        val adapter = CheckItemAdapter()
        recyclerView.adapter = adapter

        viewModel.status.observe(viewLifecycleOwner) { responseStatus ->
            binding.status.setText(responseStatus.toString())
        }
        viewModel.checks.observe(viewLifecycleOwner) { checksList ->
            adapter.submitList(checksList)
        }

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}