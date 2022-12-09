//**********************************************************************************************************************
// * Documentation
// * Author: zilin.li
// * Date: 12/22
// * Definition: Implementation of SaveFragment class.
// * Note: fragment class for search tab
//**********************************************************************************************************************
package com.laioffer.tinnews.ui.save;

//**********************************************************************************************************************
// * Includes
//**********************************************************************************************************************
// Project includes
import com.laioffer.tinnews.R;

// Framework includes
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//**********************************************************************************************************************
// * Class definition
//**********************************************************************************************************************
public class SaveFragment extends Fragment {

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_save, container, false);
    }

//**********************************************************************************************************************
// * Private attributes
//**********************************************************************************************************************
}