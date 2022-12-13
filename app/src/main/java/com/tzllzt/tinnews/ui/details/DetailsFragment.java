//**********************************************************************************************************************
// * Documentation
// * Author: zilin.li
// * Date: 12/22
// * Definition: Implementation of DetailsFragment class.
// * Note: detail fragment to display article content
//**********************************************************************************************************************

package com.tzllzt.tinnews.ui.details;
//**********************************************************************************************************************
// * Includes
//**********************************************************************************************************************

// Project includes
import com.tzllzt.tinnews.model.Article;
import com.tzllzt.tinnews.databinding.FragmentDetailsBinding;

// Framework includes
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.squareup.picasso.Picasso;

//**********************************************************************************************************************
// * Class definition
//**********************************************************************************************************************
public class DetailsFragment extends Fragment {

//**********************************************************************************************************************
// * Public methods
//**********************************************************************************************************************
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Article article = DetailsFragmentArgs.fromBundle(getArguments()).getArticle();
        binding.detailsTitleTextView.setText(article.title);
        binding.detailsAuthorTextView.setText(article.author);
        binding.detailsDateTextView.setText(article.publishedAt);
        binding.detailsDescriptionTextView.setText(article.description);
        binding.detailsContentTextView.setText(article.content);
        Picasso.get().load(article.urlToImage).into(binding.detailsImageView);
    }

//**********************************************************************************************************************
// * Private attributes
//**********************************************************************************************************************

    private FragmentDetailsBinding binding;
}
