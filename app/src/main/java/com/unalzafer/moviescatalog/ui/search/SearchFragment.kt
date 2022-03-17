package com.unalzafer.moviescatalog.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.unalzafer.moviescatalog.R
import com.unalzafer.moviescatalog.adapter.SearchAdapter
import com.unalzafer.moviescatalog.base.BaseFragment
import com.unalzafer.moviescatalog.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var searchAdapter: SearchAdapter
    private val viewModel: SearchViewModel by viewModels()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        val root: View = binding.root

        init()

        return root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun init() {
        searchAdapter = SearchAdapter()
        searchAdapter.onItemClick={
            startIntentMoveDetailActivity(it.id.toString())
        }

        binding.rvSearchMovies.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL,
                false
            )
            setHasFixedSize(true)
        }



        viewModel.responseMovies.observe(viewLifecycleOwner) {
            if(it.movieList.isNotEmpty()) {
                binding.tvEmpty.visibility=View.GONE
                searchAdapter.movies = it.movieList
            }else{
                binding.tvEmpty.visibility=View.VISIBLE
            }
            searchAdapter.notifyDataSetChanged()
        }


        binding.svMovie.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p: String?): Boolean {
                if(!p?.trim().isNullOrEmpty()){
                    viewModel.getAllMovies(p!!)
                }
                return false
            }

            override fun onQueryTextChange(p: String?): Boolean {
                if(!p.isNullOrEmpty()&&p.trim().length>2){
                    viewModel.getAllMovies(p)
                }
                return false
            }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}