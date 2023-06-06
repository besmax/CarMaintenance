package bes.max.carmaintenance.ui.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import bes.max.carmaintenance.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentMenuButtonChecks.setOnClickListener {
            findNavController().navigate(
                MenuFragmentDirections.actionMenuFragmentToChecksFragment()
            )
        }

        binding.fragmentMenuButtonNewPlannedCheck.setOnClickListener {
            findNavController().navigate(
                MenuFragmentDirections.actionMenuFragmentToNewCheckFragment()
            )
        }

        binding.fragmentMenuButtonPlannedChecks.setOnClickListener {
            findNavController().navigate(
                MenuFragmentDirections.actionMenuFragmentToPlannedChecksFragment2()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}