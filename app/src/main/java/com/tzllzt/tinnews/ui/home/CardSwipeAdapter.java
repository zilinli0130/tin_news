//**********************************************************************************************************************
// * Documentation
// * Author: zilin.li
// * Date: 12/22
// * Definition: Implementation of CardSwipeAdapter class.
// * Node: adapter class for binding data to view holder
//**********************************************************************************************************************
package com.tzllzt.tinnews.ui.home;

//**********************************************************************************************************************
// * Includes
//**********************************************************************************************************************

// Project includes
import com.tzllzt.tinnews.R;
import com.tzllzt.tinnews.databinding.SwipeNewsCardBinding;
import com.squareup.picasso.Picasso;
import com.tzllzt.tinnews.model.Article;

// Framework includes
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
public class CardSwipeAdapter extends RecyclerView.Adapter<CardSwipeAdapter.CardSwipeViewHolder>{

//**********************************************************************************************************************
// * Public methods
//**********************************************************************************************************************

    // Set article data
    public void setArticles(List<Article> newsList) {
        articles.clear();
        articles.addAll(newsList);
        notifyDataSetChanged();
    }

    // Get article data
    public List<Article> getArticles() {
        return articles;
    }

    // Create view holder
    @NonNull
    @Override
    public CardSwipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Inflate the layout for this item and create Java view item object
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.swipe_news_card, parent, false);
        return new CardSwipeViewHolder(view);
    }

    // Bind article data to view holder
    @Override
    public void onBindViewHolder(@NonNull CardSwipeViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.titleTextView.setText(article.title);
        holder.descriptionTextView.setText(article.description);
        if (article.urlToImage != null) {
            Picasso.get().load(article.urlToImage).into(holder.imageView);
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
    public static class CardSwipeViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView titleTextView;
        TextView descriptionTextView;

        public CardSwipeViewHolder(@NonNull View itemView) {
            super(itemView);

            SwipeNewsCardBinding binding = SwipeNewsCardBinding.bind(itemView);
            imageView = binding.swipeCardImageView;
            titleTextView = binding.swipeCardTitle;
            descriptionTextView = binding.swipeCardDescription;
        }
    }

//**********************************************************************************************************************
// * Private attributes
//**********************************************************************************************************************
    private List<Article> articles = new ArrayList<>();

}
