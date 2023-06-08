package bes.max.carmaintenance.ui.controllers

import android.app.Activity
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
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
            binding.fragmentNewCheckChooseDate.setText(viewModel.date)
            Toast.makeText(
                requireContext(),
                "${viewModel.date} was chosen as a date",
                Toast.LENGTH_LONG
            )
        }

        binding.fragmentNewCheckButton.setOnClickListener {
            addNewPlannedCheck()
            hideKeyboard()
        }

    }

    private fun addNewPlannedCheck() {
        if (!binding.fragmentNewCheckEditText.text.isNullOrEmpty() && viewModel.date.isNotEmpty()) {
            newCheckViewModel.insertPlannedCheck(
                binding.fragmentNewCheckEditText.text.toString(),
                viewModel.date
            )
            binding.fragmentNewCheckEditText.text.clear()
        } else {
            Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_LONG)
        }
    }

    private fun hideKeyboard() {
        view.let {
            val inputMethodManager =
                context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it?.windowToken, 0)
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