package onoffrice.mytopgames.ui.base;

public interface BasePresenter<V> {

    void attachView(V mvpView);

    void detachView();
}
