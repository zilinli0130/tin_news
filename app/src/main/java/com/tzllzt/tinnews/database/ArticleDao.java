//**********************************************************************************************************************
// * Documentation
// * Author: zilin.li
// * Date: 12/22
// * Definition: Implementation of ArticleDao interface.
// * Note: interface for accessing database
//**********************************************************************************************************************
package com.tzllzt.tinnews.database;

//**********************************************************************************************************************
// * Includes
//**********************************************************************************************************************

// Project includes
import com.tzllzt.tinnews.model.Article;

// Framework includes
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

// System includes
import java.util.List;

//**********************************************************************************************************************
// * Interface definition
//**********************************************************************************************************************
@Dao
public interface ArticleDao {

//**********************************************************************************************************************
// * Public methods
//**********************************************************************************************************************
    @Insert
    void saveArticle(Article article);

    // Async task by other thread by wrapping data inside LiveData<T>
    @Query("SELECT * FROM article")
    LiveData<List<Article>> getAllArticles();

    @Delete
    void deleteArticle(Article article);
}
