//**********************************************************************************************************************
// * Documentation
// * Author: zilin.li
// * Date: 12/22
// * Definition: Implementation of SearchNewsAdapter class.
// * Node: adapter class for binding data to view holder
//**********************************************************************************************************************
package com.tzllzt.tinnews.ui.search;

//**********************************************************************************************************************
// * Includes
//**********************************************************************************************************************

// Project includes
import com.tzllzt.tinnews.R;
import com.tzllzt.tinnews.databinding.SearchNewsItemBinding;
import com.tzllzt.tinnews.model.Article;

// Framework includes
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

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
        holder.itemView.setOnClickListener(v -> itemCallback.onOpenDetails(article));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void setItemCallback(ItemCallback itemCallback) {
        this.itemCallback = itemCallback;
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
// * Inner interface definition

//**********************************************************************************************************************
    public interface ItemCallback {
        void onOpenDetails(Article article);

    }

//**********************************************************************************************************************
// * Private attributes
//**********************************************************************************************************************
    private ItemCallback itemCallback;
    private List<Article> articles = new ArrayList<>();
}
