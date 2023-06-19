package bes.max.carmaintenance.ui.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import bes.max.carmaintenance.BaseApplication
import bes.max.carmaintenance.databinding.FragmentNewCheckBinding
import bes.max.carmaintenance.ui.viewmodels.ChecksViewModel
import bes.max.carmaintenance.ui.viewmodels.ChecksViewModelFactory
import bes.max.carmaintenance.ui.viewmodels.NewCheckViewModel
import bes.max.carmaintenance.ui.viewmodels.NewCheckViewModelFactory

class NewCheckFragment : Fragment() {

    private var _binding: FragmentNewCheckBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ChecksViewModel by activityViewModels {
        ChecksViewModelFactory(
            (activity?.application as BaseApplication).checkDatabase.checkDao
        )
    }

    private val newCheckViewModel: NewCheckViewModel by viewModels {
        NewCheckViewModelFactory(
            (activity?.application as BaseApplication).checkDatabase.plannedCheckDao
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
            binding.fragmentNewCheckChooseDate.setText(viewModel.date?.value)
        }

        viewModel.date.observe(viewLifecycleOwner) {
            binding.fragmentNewCheckChooseDate.setText(viewModel.date?.value)
        }

        binding.fragmentNewCheckButton.setOnClickListener {
            if (!binding.fragmentNewCheckEditText.text.isNullOrEmpty() && viewModel.date?.value != null) {
                newCheckViewModel.insertPlannedCheck(
                    binding.fragmentNewCheckEditText.text.toString(),
                    viewModel.date.value!!
                )
                binding.fragmentNewCheckEditText.text.clear()
            }

        }

    }

    private fun showDatePickerDialog(v: View) {
        val newFragment = DatePickerFragment()
        newFragment.show(childFragmentManager, "datePicker")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}