package bes.max.carmaintenance.ui.new_check

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import bes.max.carmaintenance.BaseApplication
import bes.max.carmaintenance.R
import bes.max.carmaintenance.domain.CheckRepository
import bes.max.carmaintenance.ui.checks.ChecksViewModel
import bes.max.carmaintenance.ui.checks.ChecksViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import javax.inject.Inject

@AndroidEntryPoint
class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    @Inject
    lateinit var checkRepository: CheckRepository

    private val sharedViewModel: ChecksViewModel by activityViewModels {
        ChecksViewModelFactory(checkRepository = checkRepository)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireContext(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        sharedViewModel.date.value = "$dayOfMonth.${month+1}.$year"
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        sharedViewModel.date.value = getString(R.string.fragment_new_check_choose_date)
    }
}