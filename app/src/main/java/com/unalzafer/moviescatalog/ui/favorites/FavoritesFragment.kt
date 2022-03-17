package com.unalzafer.moviescatalog.ui.favorites

import android.content.Context
import android.content.Intent
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
import com.unalzafer.moviescatalog.databinding.FragmentFavoritesBinding
import com.unalzafer.moviescatalog.helper.enum.ResponseEnum
import com.unalzafer.moviescatalog.ui.detail.MovieDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : BaseFragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var moviesAdapter: MoviesAdapter
    private val viewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false);
        val root: View = binding.root

        init()

        return root
    }

    private fun init() {
        moviesAdapter = MoviesAdapter()
        moviesAdapter.onItemClick={
            startIntentMoveDetailActivity(it.id.toString())
        }

        binding.rvFavorities.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL,
                false
            )
            setHasFixedSize(true)
        }

        viewModel.getFavorites()
        viewModel.responseMovies.observe(viewLifecycleOwner) {
            if(it.responseCode==ResponseEnum.MOVIES_SUCCESS.value) {
                if(it.movieList.isNotEmpty()) {
                    binding.tvEmpty.visibility=View.GONE
                    moviesAdapter.movies = it.movieList
                    moviesAdapter.notifyDataSetChanged()
                }else{
                    binding.tvEmpty.visibility=View.VISIBLE
                }
            }else{
                showDialog(it.responseDesc)
            }
        }

    }

    override fun onResume() {
        viewModel.getFavorites()
        super.onResume()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}