package com.fs.blockingcoroutine

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        go()
    }

    fun go() {
        val viewModel = ViewModel(Interactor(Repository()))

        for(i in 0..10) {
            viewModel.getSubscriptions()
        }
    }

}
