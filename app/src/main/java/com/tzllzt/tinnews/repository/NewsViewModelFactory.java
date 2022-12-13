//**********************************************************************************************************************
// * Documentation
// * Author: zilin.li
// * Date: 12/22
// * Definition: Implementation of NewsRepository class.
// * Node: class to implement factory builder design pattern for creating view model
//**********************************************************************************************************************
package com.tzllzt.tinnews.repository;

//**********************************************************************************************************************
// * Includes
//**********************************************************************************************************************

// Project includes
import com.tzllzt.tinnews.ui.home.HomeViewModel;
import com.tzllzt.tinnews.ui.save.SaveViewModel;
import com.tzllzt.tinnews.ui.search.SearchViewModel;

// Framework includes
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

//**********************************************************************************************************************
// * Class definition
//**********************************************************************************************************************
public class NewsViewModelFactory implements ViewModelProvider.Factory {

//**********************************************************************************************************************
// * Class constructor
//**********************************************************************************************************************
    public NewsViewModelFactory(NewsRepository repository) {
        this.repository = repository;
    }

//**********************************************************************************************************************
// * Public methods
//**********************************************************************************************************************
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(repository);
        } else if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            return (T) new SearchViewModel(repository);
        } else if (modelClass.isAssignableFrom(SaveViewModel.class)) {
            return (T) new SaveViewModel(repository);
        } else {
            throw new IllegalStateException("Unknown ViewModel");
        }
    }

//**********************************************************************************************************************
// * Private attributes
//**********************************************************************************************************************
    private final NewsRepository repository;
}


