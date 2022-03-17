package com.unalzafer.moviescatalog.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.unalzafer.moviescatalog.ui.detail.MovieDetailActivity
import com.unalzafer.moviescatalog.ui.dialog.CommonDialog
import com.unalzafer.moviescatalog.ui.main.MainActivity

open class BaseFragment : Fragment() {

    var errorDialog: CommonDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showDialog(message:String?) {
        CommonDialog(message).show(activity?.supportFragmentManager!!, "customdialog")
    }

    fun startIntentMoveDetailActivity(value:String){
        val intent = Intent(activity, MovieDetailActivity::class.java)
        intent.putExtra(MainActivity::class.simpleName,value)
        startActivity(intent)
    }

}