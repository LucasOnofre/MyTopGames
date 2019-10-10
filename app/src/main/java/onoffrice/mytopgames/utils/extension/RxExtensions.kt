package onoffrice.mytopgames.utils.extension

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import onoffrice.mytopgames.data.request.services.handlers.RetrofitException

fun <T> Single<T>.singleSubscribe(onSuccess: ((t: T) -> Unit)? = null, onError: ((e: Throwable, retrofitError: RetrofitException?) -> Unit)? = null, subscribeOnScheduler: Scheduler? = Schedulers.io(), observeOnScheduler: Scheduler? = AndroidSchedulers.mainThread()) =
        this.subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .subscribeWith(object : DisposableSingleObserver<T>() {
                    override fun onSuccess(t: T) {
                        onSuccess?.let { it(t) }
                    }

                    override fun onError(exception: Throwable) {

                        onError?.let {
                            if(exception is RetrofitException) {
                                it(exception, exception)
                            } else {
                                it(exception, null)
                            }
                        }
                    }
                })

fun <T> Observable<T>.observableSubscribe(onComplete: (() -> Unit)? = null, onNext: ((t: T) -> Unit)? = null, onError: ((e: Throwable) -> Unit)? = null, subscribeOnScheduler: Scheduler? = Schedulers.io(), observeOnScheduler: Scheduler? = AndroidSchedulers.mainThread()) =
        this.subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .subscribeWith(object : DisposableObserver<T>() {
                    override fun onNext(t: T) {
                        onNext?.let { it(t) }
                    }

                    override fun onComplete() {
                        onComplete?.let { it() }
                    }

                    override fun onError(e: Throwable) {
                        onError?.let { it(e) }
                    }
                })


fun Completable.completableSubscribe(onComplete: (() -> Unit)? = null, onError: ((e: Throwable) -> Unit)? = null, subscribeOnScheduler: Scheduler? = Schedulers.io(), observeOnScheduler: Scheduler? = AndroidSchedulers.mainThread()) =
        this.subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .subscribeWith(object : DisposableCompletableObserver() {
                    override fun onComplete() {
                        onComplete?.let { it() }
                    }

                    override fun onError(e: Throwable) {
                        onError?.let { it(e) }
                    }
                })
