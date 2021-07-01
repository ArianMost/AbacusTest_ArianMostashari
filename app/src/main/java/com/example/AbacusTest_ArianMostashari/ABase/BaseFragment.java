package com.example.AbacusTest_ArianMostashari.ABase;


import com.example.AbacusTest_ArianMostashari.R;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class BaseFragment extends Fragment {


  public void changeFragmentTo(Fragment selectedFragment) {
    FragmentManager manager = getActivity().getSupportFragmentManager();
    manager.beginTransaction().replace(R.id.fragment_container, selectedFragment).addToBackStack(null).commit();
  }

}
