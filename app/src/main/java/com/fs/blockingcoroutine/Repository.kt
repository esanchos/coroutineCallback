package com.fs.blockingcoroutine

class Repository {

    fun getSubscriptions(callback: (String?) -> Unit) {
        when ((0..1).random()) {
            0 -> callback("Coroutine Callback")
            1 -> callback(null)
        }
    }

}