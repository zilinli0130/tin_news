//**********************************************************************************************************************
// * Documentation
// * Author: zilin.li
// * Date: 12/22
// * Definition: Implementation of SaveViewModel class.
// * Note: view model class for fetching data through news repository
//**********************************************************************************************************************
package com.tzllzt.tinnews.ui.save;

//**********************************************************************************************************************
// * Includes
//**********************************************************************************************************************

// Project includes
import com.tzllzt.tinnews.model.Article;
import com.tzllzt.tinnews.repository.NewsRepository;

// Framework includes
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

// System includes
import java.util.List;

//**********************************************************************************************************************
// * Class definition
//**********************************************************************************************************************
public class SaveViewModel extends ViewModel {

//**********************************************************************************************************************
// * Public methods
//**********************************************************************************************************************
    public SaveViewModel(NewsRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Article>> getAllSavedArticles() {
        return repository.getAllSavedArticles();
    }

    public void deleteSavedArticle(Article article) {
        repository.deleteSavedArticle(article);
    }

//**********************************************************************************************************************
// * Private attributes
//**********************************************************************************************************************
    private final NewsRepository repository;

}
