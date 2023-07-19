package bes.max.carmaintenance.ui.new_check

import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import bes.max.carmaintenance.BaseApplication
import bes.max.carmaintenance.R
import bes.max.carmaintenance.databinding.FragmentNewCheckBinding
import bes.max.carmaintenance.domain.CheckRepository
import bes.max.carmaintenance.ui.checks.ChecksViewModel
import bes.max.carmaintenance.ui.checks.ChecksViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewCheckFragment : Fragment() {

    private var _binding: FragmentNewCheckBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var checkRepository: CheckRepository

    private val sharedViewModel: ChecksViewModel by activityViewModels {
        ChecksViewModelFactory(checkRepository = checkRepository)
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

        val safeArgs: NewCheckFragmentArgs by navArgs()
        val checkDesriptionArgs = safeArgs.checkDescription
        binding.fragmentNewCheckEditText.setText(checkDesriptionArgs)

        binding.fragmentNewCheckChooseDate.setText(R.string.fragment_new_check_choose_date)

        binding.fragmentNewCheckChooseDate.setOnClickListener {
            showDatePickerDialog(view)
            binding.fragmentNewCheckChooseDate.setText(sharedViewModel.date?.value)
        }

        sharedViewModel.date.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                binding.fragmentNewCheckChooseDate.setText(sharedViewModel.date?.value)
            }
        }

        binding.fragmentNewCheckButton.setOnClickListener {
            val plannedCheckIsInserted = insertPlannedCheck()
            if (plannedCheckIsInserted && binding.fragmentNewCheckCheck.isChecked) {
                addToCalendar()
            }
            if (plannedCheckIsInserted) {
                binding.fragmentNewCheckEditText.text?.clear()
                sharedViewModel.date?.value = getString(R.string.fragment_new_check_choose_date)
                binding.fragmentNewCheckCheck.isChecked = false
            }
        }

    }

    private fun showDatePickerDialog(v: View) {
        val newFragment = DatePickerFragment()
        newFragment.show(childFragmentManager, "datePicker")
    }

    private fun insertPlannedCheck(): Boolean {
        if (!binding.fragmentNewCheckEditText.text.isNullOrEmpty() &&
            !binding.fragmentNewCheckChooseDate.text.isNullOrEmpty() &&
            binding.fragmentNewCheckChooseDate.text != getString(R.string.fragment_new_check_choose_date)
        ) {
            newCheckViewModel.insertPlannedCheck(
                binding.fragmentNewCheckEditText.text.toString(),
                sharedViewModel.date.value!!
            )
            return true
        } else {
            Toast.makeText(requireContext(), R.string.fragment_new_check_error, Toast.LENGTH_LONG)
                .show()
            return false
        }
    }

    private fun addToCalendar() {
        if (!binding.fragmentNewCheckEditText.text.isNullOrEmpty() &&
            sharedViewModel.date?.value != null &&
            sharedViewModel.date?.value != getString(R.string.fragment_new_check_choose_date)
        ) {
            val dateList = sharedViewModel.date?.value!!.split(".")
            val begin: Long = Calendar.getInstance().run {
                set(dateList[0].toInt(), dateList[1].toInt(), dateList[2].toInt(), 7, 0)
                timeInMillis
            }
            val intent = Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin)
                .putExtra(CalendarContract.Events.TITLE, "CarMaintenance")
                .putExtra(
                    CalendarContract.Events.DESCRIPTION,
                    binding.fragmentNewCheckEditText.text.toString()
                )
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}