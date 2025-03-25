package com.elewa.si_ware_task.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Abstract Activity which binds  [ViewBinding] [VB]
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {


    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = getViewBinding()
    }

    /**
     * It returns [VB] which is assigned to [binding] and used in [onCreate]
     */
    abstract fun getViewBinding(): VB
}
