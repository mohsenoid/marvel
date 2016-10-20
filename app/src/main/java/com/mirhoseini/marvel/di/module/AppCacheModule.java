package com.mirhoseini.marvel.di.module;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

import com.mirhoseini.marvel.di.scope.Cache;
import com.mirhoseini.marvel.util.AppConstants;
import com.mirhoseini.marvel.util.GridSpacingItemDecoration;
import com.mirhoseini.marvel.view.fragment.CharacterCacheFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohsen on 20/10/2016.
 */

@Module
public class AppCacheModule extends CacheModule {
    private final Context context;
    private final int columnCount;
    private final CharacterCacheFragment.OnListFragmentInteractionListener listener;

    public AppCacheModule(Context context, CharacterCacheFragment fragment, int columnCount) {
        super(fragment);

        this.context = context;
        this.columnCount = columnCount;

        if (context instanceof CharacterCacheFragment.OnListFragmentInteractionListener) {
            listener = (CharacterCacheFragment.OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Provides
    @Cache
    public CharacterCacheFragment.OnListFragmentInteractionListener provideOnListFragmentInteractionListener() {
        return listener;
    }

    @Provides
    @Cache
    public GridLayoutManager provideLayoutManager() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, columnCount);
        // Create a custom SpanSizeLookup where the first item spans both columns
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 ? columnCount : 1;
            }
        });

        return gridLayoutManager;
    }

    @Provides
    @Cache
    public GridSpacingItemDecoration provideGridSpacingItemDecoration() {
        return new GridSpacingItemDecoration(columnCount, AppConstants.RECYCLER_VIEW_ITEM_SPACE, true, 1);
    }
}
