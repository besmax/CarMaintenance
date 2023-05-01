package bes.max.carmaintenance.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import bes.max.carmaintenance.data.CheckDatabase
import bes.max.carmaintenance.databinding.FragmentChecksBinding

class ChecksFragment : Fragment() {

    private var _binding: FragmentChecksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChecksBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = CheckDatabase.getInstance(application).checkDao
        val viewModelFactory = ChecksViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ChecksViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = CheckItemAdapter()
        binding.checksList.adapter = adapter

        viewModel.checks.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}