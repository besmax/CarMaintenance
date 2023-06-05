package bes.max.carmaintenance.ui.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import bes.max.carmaintenance.BaseApplication
import bes.max.carmaintenance.databinding.FragmentNewCheckBinding
import bes.max.carmaintenance.ui.ChecksViewModel
import bes.max.carmaintenance.ui.ChecksViewModelFactory

class NewCheckFragment : Fragment() {

    private var _binding: FragmentNewCheckBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ChecksViewModel by activityViewModels {
        ChecksViewModelFactory(
            (activity?.application as BaseApplication).checkDatabase.checkDao
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewCheckBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentNewCheckChooseDate.setOnClickListener {
            showDatePickerDialog(view)
            binding.fragmentNewCheckChooseDate.setText(viewModel.date)
        }


    }

    private fun showDatePickerDialog(v: View) {
        val newFragment = DatePickerFragment()
        newFragment.show(childFragmentManager, "datePicker")
    }
}