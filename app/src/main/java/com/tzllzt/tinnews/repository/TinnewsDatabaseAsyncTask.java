//**********************************************************************************************************************
// * Documentation
// * Author: zilin.li
// * Date: 12/22
// * Definition: Implementation of TinnewsDatabaseAsyncTask class.
// * Note: async task class for processing database I/O request
//**********************************************************************************************************************

package com.tzllzt.tinnews.repository;
//**********************************************************************************************************************
// * Includes
//**********************************************************************************************************************

// Project includes
import com.tzllzt.tinnews.database.TinNewsDatabase;
import com.tzllzt.tinnews.model.Article;

// Framework includes
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;

//**********************************************************************************************************************
// * Class definition
//**********************************************************************************************************************
public abstract class TinnewsDatabaseAsyncTask<Params, Progress, Result> {

//**********************************************************************************************************************
// * Protect methods
//**********************************************************************************************************************

    protected void onPreExecute() {}

    protected void publishProgress(final Progress progress) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                onProgressUpdate(progress);
            }
        });
    }

    // Background thread
    protected abstract Result doInBackground(Params params);

    protected void onProgressUpdate(Progress progress) {}

    // Main thread
    protected void onPostExecute(Result result) {}

//**********************************************************************************************************************
// * Public method
//**********************************************************************************************************************

    // Background thread
    public TinnewsDatabaseAsyncTask<Params, Progress, Result> execute(final Params params) {
        onPreExecute();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Result result = doInBackground(params);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onPostExecute(result);
                    }
                });
            }
        }).start();
        return this;
    }

    // Background thread
    public static void execute(Runnable runnable) {
        new TinnewsDatabaseAsyncTask() {

            @Override
            protected Object doInBackground(Object o) {
                runnable.run();
                return null;
            }
        };
    }

    public void saveFavoriteArticle(TinNewsDatabase database, MutableLiveData<Boolean> liveData, Article article) {
        SaveFavoriteArticleAsyncTask task = new SaveFavoriteArticleAsyncTask(database, liveData);
        task.execute(article);
    }

    public void deleteFavoriteArticle(Runnable runnable) {
        this.execute(runnable);
    }

    public static class SaveFavoriteArticleAsyncTask extends TinnewsDatabaseAsyncTask<Article, Void, Boolean> {

        private final TinNewsDatabase database;
        private final MutableLiveData<Boolean> liveData;

        private SaveFavoriteArticleAsyncTask(TinNewsDatabase database, MutableLiveData<Boolean> liveData) {
            this.database = database;
            this.liveData = liveData;
        }

        // Implement the abstract method in TinnewsDatabaseAsyncTask<T1,T2,T3> to save favorite article item
        @Override
        protected Boolean doInBackground(Article article) {

            try {
                database.articleDao().saveArticle(article);
                Log.d("SaveFavoriteArticle", "save favorite item for " + article.title);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            liveData.setValue(result);
        }
    }
//**********************************************************************************************************************
// * Private attributes
//**********************************************************************************************************************
    private Handler handler = new Handler(Looper.getMainLooper());
}
