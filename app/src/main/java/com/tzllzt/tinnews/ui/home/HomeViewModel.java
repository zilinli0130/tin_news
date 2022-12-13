//**********************************************************************************************************************
// * Documentation
// * Author: zilin.li
// * Date: 12/22
// * Definition: Implementation of HomeViewModel class.
// * Node: view model class for fetching data through news repository
//**********************************************************************************************************************
package com.tzllzt.tinnews.ui.home;

//**********************************************************************************************************************
// * Includes
//**********************************************************************************************************************

// Project includes
import com.tzllzt.tinnews.model.Article;
import com.tzllzt.tinnews.model.NewsResponse;
import com.tzllzt.tinnews.repository.NewsRepository;

// Framework includes
import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;


//**********************************************************************************************************************
// * Class definition
//**********************************************************************************************************************
public class HomeViewModel extends ViewModel {


//**********************************************************************************************************************
// * Public methods
//**********************************************************************************************************************

    public HomeViewModel(NewsRepository newsRepository) {
        this.repository = newsRepository;
    }

    public void setCountryInput(String country) {
        countryInput.setValue(country);
    }

    // LiveData<T> can be observed by anyone, but we make it only for UI view
    public LiveData<NewsResponse> getTopHeadlines() {

        // When countryInput changes, repository::getTopHeadlines is called
        return Transformations.switchMap(countryInput, repository::getTopHeadlines);
    }

    public void setFavoriteArticleInput(Article article, Context context) {
        repository.favoriteArticle(article, context);
    }


//**********************************************************************************************************************
// * Private attributes
//**********************************************************************************************************************

    private final NewsRepository repository;
    private final MutableLiveData<String> countryInput = new MutableLiveData<>(); // observed by UI

}

