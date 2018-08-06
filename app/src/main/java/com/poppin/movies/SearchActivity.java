package com.poppin.movies;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.poppin.movies.animation.Animator;

import java.lang.reflect.Field;
import java.util.List;

import movie.bloder.presentation.presentation.state.search.SearchState;
import movie.bloder.presentation.presentation.view_model.AppViewModel;
import movie.bloder.presentation.presentation.view_model.search.SearchViewModel;
import movie.bloder.repository.models.Movie;

public class SearchActivity extends StateActivity<SearchState> {

    private Menu searchMenu;
    private MenuItem itemSearch;
    private SearchViewModel searchViewModel;

    @Override public void onCreate(Bundle savedInstanceState) {
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSearchtollbar();
    }

    @Override protected void handleState(SearchState state) {
        if (state instanceof SearchState.OnSuccess) onSearched(((SearchState.OnSuccess) state).movies);
        else if (state instanceof SearchState.OnNotFound) onSearchNotFound();
        else if (state instanceof SearchState.OnUnknownError) onUnknownProblem();
    }

    @Override protected AppViewModel<SearchState> provideViewModel() {
        return searchViewModel;
    }

    private void onSearched(List<Movie> movies) {

    }

    private void onSearchNotFound() {

    }

    private void onUnknownProblem() {

    }

    @Override public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Animator.circleReveal(this, R.id.search_toolbar,1,true,true);
                itemSearch.expandActionView();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setSearchtollbar() {
        Toolbar searchToolbar = findViewById(R.id.search_toolbar);
        searchToolbar.inflateMenu(R.menu.menu_search);
        searchMenu = searchToolbar.getMenu();

        searchToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animator.circleReveal(SearchActivity.this, R.id.search_toolbar,1,true,false);
            }
        });

        itemSearch = searchMenu.findItem(R.id.action_filter_search);

        itemSearch.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override public boolean onMenuItemActionCollapse(MenuItem item) {
                Animator.circleReveal(SearchActivity.this, R.id.search_toolbar,1,true,false);
                return true;
            }

            @Override public boolean onMenuItemActionExpand(MenuItem item) { return true; }
        });
        initSearchView();
    }

    private void initSearchView() {
        final SearchView searchView = (SearchView) searchMenu.findItem(R.id.action_filter_search).getActionView();
        searchView.setSubmitButtonEnabled(false);
        ImageView closeButton = searchView.findViewById(R.id.search_close_btn);
        closeButton.setImageResource(R.drawable.close_icon);
        EditText txtSearch = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        txtSearch.setHint("Pesquisar...");
        txtSearch.setHintTextColor(Color.GRAY);
        txtSearch.setTextColor(getResources().getColor(R.color.colorPrimary));
        AutoCompleteTextView searchTextView = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        try {
            Field mCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            mCursorDrawableRes.setAccessible(true);
            mCursorDrawableRes.set(searchTextView, R.drawable.search_icon); //This sets the cursor resource ID to 0 or @null which will make it visible on white background
        } catch (Exception ignored) {}
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                searchViewModel.search(query);
                return true;
            }

            @Override public boolean onQueryTextChange(String newText) { return true; }
        });
    }


}
