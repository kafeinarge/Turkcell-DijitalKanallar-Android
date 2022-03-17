package com.unalzafer.moviescatalog.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.unalzafer.moviescatalog.ui.dialog.CommonDialog

open class BaseActivity : AppCompatActivity() {

    var errorDialog: CommonDialog? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    fun showDialog(message:String?) {
        CommonDialog(message).show(supportFragmentManager, "customdialog")
    }

}