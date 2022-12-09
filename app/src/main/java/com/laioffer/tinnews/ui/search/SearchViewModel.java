//**********************************************************************************************************************
// * Documentation
// * Author: zilin.li
// * Date: 12/22
// * Definition: Implementation of SearchViewModel class.
// * Node: view model class for fetching data through news repository
//**********************************************************************************************************************
package com.laioffer.tinnews.ui.search;

//**********************************************************************************************************************
// * Includes
//**********************************************************************************************************************

// Project includes
import com.laioffer.tinnews.model.NewsResponse;
import com.laioffer.tinnews.repository.NewsRepository;

// Framework includes
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

//**********************************************************************************************************************
// * Class definition
//**********************************************************************************************************************
public class SearchViewModel extends ViewModel {

//**********************************************************************************************************************
// * Public methods
//**********************************************************************************************************************

    public SearchViewModel(NewsRepository repository) {
        this.repository = repository;
    }
    public void setSearchInput(String query) {
        searchInput.setValue(query);
    }

    // When countryInput changes, repository::getTopHeadlines is called
    public LiveData<NewsResponse> searchNews() {
        return Transformations.switchMap(searchInput, repository::searchNews);
    }


//**********************************************************************************************************************
// * Private attributes
//**********************************************************************************************************************
    private final NewsRepository repository;
    private final MutableLiveData<String> searchInput = new MutableLiveData<>();
}

