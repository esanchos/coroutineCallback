# Coroutine example to wrap up a callback

## Overview

Deal with a repository that uses callback but you ViewModel is using coroutines.

Interactor will be responsible to do the convertion and handle errors.

```kotlin
class Interactor(private val repository: Repository) {

    suspend fun getSubscriptions() = withContext(IO) {
        CompletableDeferred<String>().also { cont ->
            repository.getSubscriptions {
                it?.let {
                    cont.complete(it)
                } ?: cont.cancel("erro from repo")

            }
        }
    }

}
```

```kotlin
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
```


## References

https://code.luasoftware.com/tutorials/kotlin/convert-callback-into-kotlin-coroutines-suspend-or-deferred/
https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/
https://discuss.kotlinlang.org/t/generic-function-to-wrap-callbacks-with-coroutines/8885/2