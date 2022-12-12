//**********************************************************************************************************************
// * Documentation
// * Author: zilin.li
// * Date: 12/22
// * Definition: Implementation of NewsRepository class.
// * Node: Class for creating news repository which uses Retrofit service client to fetch news data through news API
//**********************************************************************************************************************
package com.laioffer.tinnews.repository;

//**********************************************************************************************************************
// * Includes
//**********************************************************************************************************************

// Project includes
import com.laioffer.tinnews.TinNewsApplication;
import com.laioffer.tinnews.database.TinNewsDatabase;
import com.laioffer.tinnews.model.Article;
import com.laioffer.tinnews.model.NewsResponse;
import com.laioffer.tinnews.network.NewsApi;
import com.laioffer.tinnews.network.RetrofitClient;

// Framework includes
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

// Network includes
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// System includes
import java.util.List;

//**********************************************************************************************************************
// * Class definition
//**********************************************************************************************************************

public class NewsRepository {

//**********************************************************************************************************************
// * Class constructor
//**********************************************************************************************************************
    public NewsRepository() {
        newsApi = RetrofitClient.newInstance().create(NewsApi.class);
        database = TinNewsApplication.getDatabase();
    }

//**********************************************************************************************************************
// * Public method
//**********************************************************************************************************************

    // Implement interface methods
    // View model provides LiveData<T> through calling the APIs here to view, view observes LiveData<T>
    public LiveData<NewsResponse> getTopHeadlines(String country) {
        MutableLiveData<NewsResponse> topHeadlinesLiveData = new MutableLiveData<>();
        newsApi.getTopHeadlines(country)
                .enqueue(new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                        if (response.isSuccessful()) {
                            topHeadlinesLiveData.setValue(response.body());
                        } else {
                            topHeadlinesLiveData.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {
                        topHeadlinesLiveData.setValue(null);
                    }
                });
        // LiveData<T> might be empty when returning, the data will be updated from the worker in queue
        return topHeadlinesLiveData;
    }

    public LiveData<NewsResponse> searchNews(String query) {
        MutableLiveData<NewsResponse> everyThingLiveData = new MutableLiveData<>();
        newsApi.getEverything(query, 40)
                .enqueue(
                        new Callback<NewsResponse>() {
                            @Override
                            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                                if (response.isSuccessful()) {
                                    everyThingLiveData.setValue(response.body());
                                } else {
                                    everyThingLiveData.setValue(null);
                                }
                            }

                            @Override
                            public void onFailure(Call<NewsResponse> call, Throwable t) {
                                everyThingLiveData.setValue(null);
                            }
                        });
        return everyThingLiveData;
    }

    public LiveData<Boolean> favoriteArticle(Article article, Context context) {

        // doInBackground() and onPostExecute()
        MutableLiveData<Boolean> resultLiveData = new MutableLiveData<>();
        new FavoriteAsyncTask(database, resultLiveData).execute(article);
//        Toast.makeText(context, FAV_SUCCESS_MSG, Toast.LENGTH_SHORT).show();

//        TinnewsDatabaseAsyncTask<Article, Void, Boolean> tinnewsDatabaseAsyncTask = new TinnewsDatabaseAsyncTask<Article, Void, Boolean>() {
//            @Override
//            protected Boolean doInBackground(Article article) {
//                return null;
//            }
//        };
//        tinnewsDatabaseAsyncTask.saveFavoriteArticle(database,resultLiveData,article);
        return resultLiveData;
    }

    public LiveData<List<Article>> getAllSavedArticles() {
        return database.articleDao().getAllArticles();
    }

    public void deleteSavedArticle(Article article) {

        // Only doInBackground(), no onPostExecute()
        AsyncTask.execute(() -> database.articleDao().deleteArticle(article)); // Runnable
    }

//**********************************************************************************************************************
// * Static inner class definition
//**********************************************************************************************************************

    // Async task for DB query
    private static class FavoriteAsyncTask extends AsyncTask<Article, Void, Boolean> {

        private final TinNewsDatabase database;
        private final MutableLiveData<Boolean> liveData;

        private FavoriteAsyncTask(TinNewsDatabase database, MutableLiveData<Boolean> liveData) {
            this.database = database;
            this.liveData = liveData;
        }

        // Background thread
        @Override
        protected Boolean doInBackground(Article... articles) {
            Article article = articles[0];
            try {
                database.articleDao().saveArticle(article);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        // Main thread
        @Override
        protected void onPostExecute(Boolean success) {
            liveData.setValue(success);
        }
    }


//**********************************************************************************************************************
// * Private attributes
//**********************************************************************************************************************
    // Singleton instance of Retrofit web service client
    private final NewsApi newsApi;
    private final TinNewsDatabase database;
    private final CharSequence FAV_SUCCESS_MSG = "Liked Item Saved";
    private final CharSequence FAV_FAIL_MSG = "Item Already In Saved List Before";


}

