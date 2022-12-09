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
import com.laioffer.tinnews.model.NewsResponse;
import com.laioffer.tinnews.network.NewsApi;
import com.laioffer.tinnews.network.RetrofitClient;

// Framework includes
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

// Network includes
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//**********************************************************************************************************************
// * Class definition
//**********************************************************************************************************************

public class NewsRepository {

//**********************************************************************************************************************
// * Class constructor
//**********************************************************************************************************************
    public NewsRepository() {
        newsApi = RetrofitClient.newInstance().create(NewsApi.class);
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

//**********************************************************************************************************************
// * Private attributes
//**********************************************************************************************************************
    // Singleton instance of Retrofit web service client
    private final NewsApi newsApi;

}

