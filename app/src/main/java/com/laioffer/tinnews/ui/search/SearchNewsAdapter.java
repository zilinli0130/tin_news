//**********************************************************************************************************************
// * Documentation
// * Author: zilin.li
// * Date: 12/22
// * Definition: Implementation of SearchNewsAdapter class.
// * Node: adapter class for binding data to view holder
//**********************************************************************************************************************
package com.laioffer.tinnews.ui.search;

//**********************************************************************************************************************
// * Includes
//**********************************************************************************************************************

// Project includes
import com.laioffer.tinnews.R;
import com.laioffer.tinnews.databinding.SearchNewsItemBinding;
import com.laioffer.tinnews.model.Article;
import com.squareup.picasso.Picasso;

// Framework includes
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

// System includes
import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************************************
// * Class definition
//**********************************************************************************************************************
public class SearchNewsAdapter extends RecyclerView.Adapter<SearchNewsAdapter.SearchNewsViewHolder> {


//**********************************************************************************************************************
// * Public methods
//**********************************************************************************************************************

    // Set the article data
    public void setArticles(List<Article> newsList) {
        articles.clear();
        articles.addAll(newsList);
        notifyDataSetChanged();
    }

    // Create view holder
    @NonNull
    @Override
    public SearchNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_news_item, parent, false);
        SearchNewsViewHolder searchNewsViewHolder = new SearchNewsViewHolder(itemView);
        Log.d("onCreateViewHolder", searchNewsViewHolder.toString());
        return searchNewsViewHolder;
    }

    // Bind article data to view holder
    @Override
    public void onBindViewHolder(@NonNull SearchNewsViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.itemTitleTextView.setText(article.title);
        if (article.urlToImage != null) {
            Picasso.get().load(article.urlToImage).resize(200, 200).into(holder.itemImageView);
        }
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

//**********************************************************************************************************************
// * Static inner class definition
//**********************************************************************************************************************

    // Create view holder through item view fields
    public static class SearchNewsViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImageView;

        TextView itemTitleTextView;
        public SearchNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            SearchNewsItemBinding binding = SearchNewsItemBinding.bind(itemView);
            itemImageView = binding.searchItemImage;
            itemTitleTextView = binding.searchItemTitle;
        }
    }

//**********************************************************************************************************************
// * Private attributes
//**********************************************************************************************************************
    private List<Article> articles = new ArrayList<>();
}
