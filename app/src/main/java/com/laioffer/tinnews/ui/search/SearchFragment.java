package com.laioffer.tinnews.ui.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laioffer.tinnews.R;
import com.laioffer.tinnews.databinding.FragmentSearchBinding;
import com.laioffer.tinnews.repository.NewsRepository;
import com.laioffer.tinnews.repository.NewsViewModelFactory;

public class SearchFragment extends Fragment {

    private SearchViewModel viewModel;
    private FragmentSearchBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        // Create the Java view object from xml (view binding)
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Create the adapter
        SearchNewsAdapter newsAdapter = new SearchNewsAdapter();

        // Create layout manager
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);

        // Assign the adapter and layout manager to recycle view
        binding.newsResultsRecyclerView.setLayoutManager(gridLayoutManager);
        binding.newsResultsRecyclerView.setAdapter(newsAdapter);

        // Set the text listener to newsSearchView
        binding.newsSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.isEmpty()) {
                    viewModel.setSearchInput(query);
                }
                binding.newsSearchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        // Create the view model
        NewsRepository repository = new NewsRepository();
        viewModel = new ViewModelProvider(this, new NewsViewModelFactory(repository)).get(SearchViewModel.class);
        viewModel.searchNews().observe( getViewLifecycleOwner(),
        newsResponse -> {
            if (newsResponse != null) {
                Log.d("SearchFragment", newsResponse.toString());
                newsAdapter.setArticles(newsResponse.articles);
            }
        });

    }

}