package onoffrice.mytopgames.utils.extension

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.singleSubscribe(onSuccess: ((t: T) -> Unit)? = null, onError: ((e: Throwable) -> Unit)? = null, subscribeOnScheduler: Scheduler? = Schedulers.io(), observeOnScheduler: Scheduler? = AndroidSchedulers.mainThread()) =
        this.subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .subscribeWith(object : DisposableSingleObserver<T>() {
                    override fun onSuccess(t: T) {
                        onSuccess?.let { it(t) }
                    }

                    override fun onError(exception: Throwable) {

                        onError?.let {
                            it(exception)
                        }
                    }
                })
