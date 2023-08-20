package bes.max.carmaintenance.ui.new_check

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import bes.max.carmaintenance.R
import bes.max.carmaintenance.ui.checks.ChecksViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private val viewModel: ChecksViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireContext(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        viewModel.date.value = "$dayOfMonth.${month + 1}.$year"
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        viewModel.date.value = getString(R.string.fragment_new_check_choose_date)
    }
}