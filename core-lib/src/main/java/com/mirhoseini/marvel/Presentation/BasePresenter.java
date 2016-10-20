package com.mirhoseini.marvel.Presentation;

/**
 * Created by Mohsen on 20/10/2016.
 */

public interface BasePresenter<T> {

    void setView(T view);

    void destroy();

}
