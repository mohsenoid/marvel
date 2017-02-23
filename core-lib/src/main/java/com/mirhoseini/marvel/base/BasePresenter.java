package com.mirhoseini.marvel.base;

/**
 * Contains Presenter's general methods.
 */
public interface BasePresenter<T> {

    void bind(T view);

    void unbind();

}
