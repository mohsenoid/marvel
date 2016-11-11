package com.mirhoseini.marvel.base;

/**
 * Created by Mohsen on 20/10/2016.
 */

public interface BasePresenter<T> {

    void bind(T view);

    void unbind();

}
