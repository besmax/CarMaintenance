package bes.max.carmaintenance.ui.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import bes.max.carmaintenance.BaseApplication
import bes.max.carmaintenance.databinding.FragmentCheckDetailBinding
import bes.max.carmaintenance.ui.viewmodels.ChecksViewModel
import bes.max.carmaintenance.ui.viewmodels.ChecksViewModelFactory


class CheckDetailFragment : Fragment() {

    private var _binding: FragmentCheckDetailBinding? = null
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
        _binding = FragmentCheckDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val safeArgs: CheckDetailFragmentArgs by navArgs()
        val positionCheck = safeArgs.checkPosition

        viewModel.check = viewModel.checks.value?.get(positionCheck)
        binding.fragmentCheckDetailDate.append(viewModel.check?.date.toString())
        binding.fragmentCheckDetailMileage.append(viewModel.check?.checkMileage)
        binding.fragmentCheckDetailCompany.append(viewModel.check?.checkCompany)
        binding.fragmentCheckDetailPrice.append(viewModel.check?.checkPrice)
        binding.fragmentCheckDetailName.append(viewModel.check?.checkName)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}