package com.costa.jaggratsample.view.activity

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.costa.jaggratsample.presenter.BasePresenter
import com.costa.jaggratsample.view.BaseView

abstract class BaseActivity<P: BasePresenter<BaseView>> : BaseView, AppCompatActivity() {
    private lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = instantiatePresenter()
    }

    /**
     * Instantiates the presenter the Activity is based on.
     */
    protected abstract fun instantiatePresenter(): P


    override fun getContext(): Context {
        return this
    }
}
