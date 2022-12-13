//**********************************************************************************************************************
// * Documentation
// * Author: zilin.li
// * Date: 12/22
// * Definition: Implementation of MainActivity class.
// * Note: main class for main activity
//**********************************************************************************************************************
package com.tzllzt.tinnews;

//**********************************************************************************************************************
// * Includes
//**********************************************************************************************************************

// Framework includes
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;


//**********************************************************************************************************************
// * Class definition
//**********************************************************************************************************************
public class MainActivity extends AppCompatActivity {

//**********************************************************************************************************************
// * Public methods
//**********************************************************************************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bottom navigation view
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Fragment view
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        // Nav controller to manage fragments
        navController = navHostFragment.getNavController();

        // Enable navigation through clicking on tab on BottomNavView
        NavigationUI.setupWithNavController(navView, navController);

        // Display label on action bar
        NavigationUI.setupActionBarWithNavController(this, navController);

        // Display a welcome toast
        Toast.makeText(this, WELCOME_MSG, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        // can click back
        return navController.navigateUp();
    }

//**********************************************************************************************************************
// * Private attributes
//**********************************************************************************************************************
    private NavController navController;
    private final CharSequence WELCOME_MSG = "Welcome to Tinnews";
}
