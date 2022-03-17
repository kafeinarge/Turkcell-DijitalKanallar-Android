package com.unalzafer.moviescatalog.ui.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.unalzafer.moviescatalog.R
import com.unalzafer.moviescatalog.adapter.MoviesAdapter
import com.unalzafer.moviescatalog.base.BaseFragment
import com.unalzafer.moviescatalog.databinding.FragmentWatchListBinding
import com.unalzafer.moviescatalog.helper.enum.ResponseEnum
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WatchListFragment : BaseFragment() {

    private var _binding: FragmentWatchListBinding? = null
    private val binding get() = _binding!!
    private lateinit var moviesAdapter: MoviesAdapter
    private val viewModel: WatchListViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_watch_list, container, false);
        val root: View = binding.root

        init()

        return root
    }

    private fun init() {
        moviesAdapter = MoviesAdapter()
        moviesAdapter.onItemClick={
            startIntentMoveDetailActivity(it.id.toString())
        }
        binding.rvWatchList.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL,
                false
            )
            setHasFixedSize(true)
        }

        viewModel.getWatchListMovies()
        viewModel.responseMovies.observe(viewLifecycleOwner) {
            if(it.responseCode==ResponseEnum.MOVIES_SUCCESS.value) {
                if(it.movieList.isNotEmpty()) {
                    binding.tvEmpty.visibility = View.GONE
                    moviesAdapter.movies = it.movieList
                    moviesAdapter.notifyDataSetChanged()
                }else{
                    binding.tvEmpty.visibility = View.VISIBLE
                }
            }else{
                showDialog(it.responseDesc)
            }
        }

    }

    override fun onResume() {
        viewModel.getWatchListMovies()
        super.onResume()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}