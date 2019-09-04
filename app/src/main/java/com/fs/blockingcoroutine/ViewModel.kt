package com.fs.blockingcoroutine

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ViewModel(private val interactor: Interactor) {

    fun getSubscriptions() {
        GlobalScope.launch(context = Dispatchers.Main) {
            try {
                val result = interactor.getSubscriptions().await()
                Log.d("VIEWMODEL", "result: $result")
            } catch (t: Throwable) {
                Log.d("VIEWMODEL", t.message)
            }
        }
    }

}