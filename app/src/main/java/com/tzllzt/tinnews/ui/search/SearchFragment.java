//**********************************************************************************************************************
// * Documentation
// * Author: zilin.li
// * Date: 12/22
// * Definition: Implementation of SearchFragment class.
// * Note: fragment class for search tab
//**********************************************************************************************************************
package com.tzllzt.tinnews.ui.search;

//**********************************************************************************************************************
// * Includes
//**********************************************************************************************************************

// Project includes
import com.tzllzt.tinnews.databinding.FragmentSearchBinding;
import com.tzllzt.tinnews.repository.NewsRepository;
import com.tzllzt.tinnews.repository.NewsViewModelFactory;

// Framework includes
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;



//**********************************************************************************************************************
// * Class definition
//**********************************************************************************************************************
public class SearchFragment extends Fragment {


//**********************************************************************************************************************
// * Public methods
//**********************************************************************************************************************

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment and create the Java view object from xml (view binding)
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

        // Switch current fragment to details fragment
        newsAdapter.setItemCallback(article -> {
            SearchFragmentDirections.ActionNavigationSearchToNavigationDetails direction = SearchFragmentDirections.actionNavigationSearchToNavigationDetails(article);
            NavHostFragment.findNavController(SearchFragment.this).navigate(direction);
        });

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
                // viewModel is observed by view, observer observes the LiveData<T>,
                // observer will execute this function as long as LiveData<T> updates
                newsResponse -> {
                    if (newsResponse != null) {
                        Log.d("SearchFragment", newsResponse.toString());
                        newsAdapter.setArticles(newsResponse.articles);
                    }
                });

    }

//**********************************************************************************************************************
// * Private attributes
//**********************************************************************************************************************
    private SearchViewModel viewModel;
    private FragmentSearchBinding binding;
}