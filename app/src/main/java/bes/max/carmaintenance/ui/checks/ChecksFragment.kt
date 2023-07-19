package bes.max.carmaintenance.ui.checks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import bes.max.carmaintenance.BaseApplication
import bes.max.carmaintenance.databinding.FragmentChecksBinding
import bes.max.carmaintenance.domain.CheckRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChecksFragment : Fragment() {

    private var _binding: FragmentChecksBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var checkRepository: CheckRepository

    private val viewModel: ChecksViewModel by activityViewModels {
        ChecksViewModelFactory(checkRepository = checkRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChecksBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.checksList

        val doOnClickItem = { checkPosition: Int ->
            val action =
                ChecksFragmentDirections.actionChecksFragmentToCheckDetailFragment(checkPosition)
            findNavController().navigate(action)
        }
        val adapter = CheckItemAdapter(doOnClickItem)
        recyclerView.adapter = adapter

        viewModel.checks.observe(viewLifecycleOwner) { checksList ->
            adapter.submitList(checksList)
        }

        binding.fab.setOnClickListener {
            viewModel.getDataFromGoogleSheets()
        }

        binding.fragmentChecksCheckboxSort.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.sortByDate()
                adapter.notifyDataSetChanged()
            }
        }

        binding.fragmentChecksButtonPlan.setOnClickListener {
            findNavController()
                .navigate(ChecksFragmentDirections.actionChecksFragmentToNewCheckFragment())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}