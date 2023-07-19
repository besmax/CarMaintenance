package bes.max.carmaintenance.ui.menu

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import bes.max.carmaintenance.BaseApplication
import bes.max.carmaintenance.R
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

        val darkThemePreference = activity?.getSharedPreferences(getString(R.string.settings_preferences), Context.MODE_PRIVATE)
        val switcherIsChecked = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            darkThemePreference?.getBoolean(
                getString(R.string.dark_theme_preference_key), resources.configuration.isNightModeActive
            )
        } else {
            darkThemePreference?.getBoolean(
                getString(R.string.dark_theme_preference_key),
                (resources.configuration.uiMode == Configuration.UI_MODE_NIGHT_YES)
            )
        }
        binding.fragmentMenuSwitchTheme.isChecked = switcherIsChecked ?: false

        binding.fragmentMenuSwitchTheme.setOnCheckedChangeListener { _, checked ->
            changeTheme(checked)
            darkThemePreference?.edit()
                ?.putBoolean(getString(R.string.dark_theme_preference_key), checked)
                ?.apply()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun changeTheme(checked: Boolean) {
        (activity?.application as BaseApplication).switchTheme(checked)
    }
}