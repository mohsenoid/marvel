package com.mirhoseini.marvel.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mirhoseini.marvel.BR;
import com.mirhoseini.marvel.R;
import com.mirhoseini.marvel.database.model.CharacterModel;
import com.mirhoseini.marvel.di.component.ApplicationComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class CharacterActivity extends BaseActivity {

    private static final String ARG_CHARACTER = "characterModel";

    // injecting views via ButterKnife
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static Intent newIntent(Context context, CharacterModel character) {
        Intent intent = new Intent(context, CharacterActivity.class);
        intent.putExtra(ARG_CHARACTER, character);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_character);

        // inject views using ButterKnife
        ButterKnife.bind(this);

        // get args
        CharacterModel character = (CharacterModel) getIntent().getExtras().getSerializable(ARG_CHARACTER);

        if (null == character)
            finish();

        // bind value using Android Binding
        binding.setVariable(BR.character, character);

        setupToolbar(character.getName());

        Timber.d("Character Activity Created");
    }

    private void setupToolbar(String characterName) {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.logo);
        getSupportActionBar().setTitle(characterName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void injectDependencies(ApplicationComponent component) {
        component.inject(this);
    }

}
