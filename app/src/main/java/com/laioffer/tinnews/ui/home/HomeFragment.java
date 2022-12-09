//**********************************************************************************************************************
// * Documentation
// * Author: zilin.li
// * Date: 12/22
// * Definition: Implementation of HomeFragment class.
// * Note: fragment class for home tab
//**********************************************************************************************************************
package com.laioffer.tinnews.ui.home;

//**********************************************************************************************************************
// * Includes
//**********************************************************************************************************************

// Project includes
import com.laioffer.tinnews.databinding.FragmentHomeBinding;
import com.laioffer.tinnews.repository.NewsRepository;
import com.laioffer.tinnews.repository.NewsViewModelFactory;

// Framework includes
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// Library includes
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.Duration;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting;

//**********************************************************************************************************************
// * Class definition
//**********************************************************************************************************************
public class HomeFragment extends Fragment implements CardStackListener {


//**********************************************************************************************************************
// * Public methods
//**********************************************************************************************************************


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment and create Java view binding object
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup card stack view
        swipeAdapter = new CardSwipeAdapter();
        layoutManager = new CardStackLayoutManager(requireContext(), this); // HomeFragment listens to swipe event
        layoutManager.setStackFrom(StackFrom.Top);
        binding.homeCardStackView.setLayoutManager(layoutManager);
        binding.homeCardStackView.setAdapter(swipeAdapter);

        // Setup listener to swipe cards for handling like/unlike button clicks
        binding.homeUnlikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swipeCard(Direction.Left);
            }
        });
        binding.homeLikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swipeCard(Direction.Right);
            }
        });

        // Create view model to fetch news data and sync it with view layer through adapter
        NewsRepository repository = new NewsRepository();
        // Data inside viewModel can survive after configuration change (rotate screen etc...)
        // Factory design pattern simplifies the object creation here
        viewModel = new ViewModelProvider(this, new NewsViewModelFactory(repository)).get(HomeViewModel.class);
        viewModel.setCountryInput("us");
        viewModel
                .getTopHeadlines()
                .observe(
                        getViewLifecycleOwner(),
                        // viewModel is observed by view, observer observes the LiveData<T>,
                        // observer will execute this function as long as LiveData<T> updates
                        newsResponse -> {
                            if (newsResponse != null) {
                                Log.d("HomeFragment", newsResponse.toString());
                                swipeAdapter.setArticles(newsResponse.articles);
                            }
                        });
    }

    @Override
    public void onCardDragging(Direction direction, float v) {

    }

    // HomeFragment listens to card swipe event
    @Override
    public void onCardSwiped(Direction direction) {
        if (direction == Direction.Left) {
            Log.d("CardStackView", "Unliked " + layoutManager.getTopPosition());
        } else if (direction == Direction.Right) {
            Log.d("CardStackView", "Liked " + layoutManager.getTopPosition());
        }
    }

    @Override
    public void onCardRewound() {

    }

    @Override
    public void onCardCanceled() {

    }

    @Override
    public void onCardAppeared(View view, int i) {

    }

    @Override
    public void onCardDisappeared(View view, int i) {

    }

//**********************************************************************************************************************
// * Private methods
//**********************************************************************************************************************

    // Swipe card event helper for like/unlike buttons
    private void swipeCard(Direction direction) {
        SwipeAnimationSetting setting = new SwipeAnimationSetting.Builder()
                .setDirection(direction)
                .setDuration(Duration.Normal.duration)
                .build();
        layoutManager.setSwipeAnimationSetting(setting);
        binding.homeCardStackView.swipe();
    }

//**********************************************************************************************************************
// * Private attributes
//**********************************************************************************************************************

    private HomeViewModel viewModel;
    private FragmentHomeBinding binding;
    private CardStackLayoutManager layoutManager;
    private CardSwipeAdapter swipeAdapter;
}


