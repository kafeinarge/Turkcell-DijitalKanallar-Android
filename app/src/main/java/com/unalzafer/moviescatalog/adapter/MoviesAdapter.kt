package com.unalzafer.moviescatalog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.unalzafer.moviescatalog.databinding.LayoutItemMovieBinding
import com.unalzafer.moviescatalog.databinding.LayoutItemSearchBinding
import com.unalzafer.moviescatalog.model.search.Movie

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    var onItemClick: ((Movie) -> Unit)? = null

    inner class MoviesViewHolder(@NonNull val binding: LayoutItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            binding.movie = item
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var movies: List<Movie>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            LayoutItemMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.binding.clParent.setOnClickListener {
            onItemClick?.invoke(movies[position])
        }
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size
}