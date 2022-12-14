package com.gedehari.pubpix.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.gedehari.pubpix.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by activityViewModels { HomeViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.allPosts.observe(viewLifecycleOwner) {

        }
        binding.swipeRefresh.setOnRefreshListener { updatePosts() }
        updatePosts()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updatePosts() {
        binding.swipeRefresh.isRefreshing = true
        lifecycleScope.launch {
            if (!viewModel.refreshPosts())
                Toast.makeText(activity, "No posts :(", Toast.LENGTH_SHORT).show()
            binding.swipeRefresh.isRefreshing = false
        }
    }
}