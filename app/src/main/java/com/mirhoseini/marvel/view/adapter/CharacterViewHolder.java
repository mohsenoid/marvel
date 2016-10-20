package com.mirhoseini.marvel.view.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mirhoseini.marvel.database.model.CharacterModel;

/**
 * Created by Mohsen on 20/10/2016.
 */

public class CharacterViewHolder extends RecyclerView.ViewHolder {

    public final View view;
    CharacterModel character;
    private ViewDataBinding binding;

    public CharacterViewHolder(View view) {
        super(view);
        this.view = view;

        binding = DataBindingUtil.bind(view);

    }

    public ViewDataBinding getBinding() {
        return binding;
    }

}
