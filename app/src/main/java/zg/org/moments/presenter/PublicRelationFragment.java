package zg.org.moments.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import zg.org.moments.R;

/**
 * Created by gzeng on 11/05/2017.
 */

public class PublicRelationFragment extends Fragment {

  public static String TAG = "public_relation_fragment";
  private ViewPager viewPager = null;
  private TabLayout tabLayout = null;

  public PublicRelationFragment(){
  }


  public static Fragment getInstance(){
    return new PublicRelationFragment();
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.public_relation_fragment, container, false);

    tabLayout = (TabLayout) view.findViewById(R.id.public_relation_activity_tab_header);
    viewPager = (ViewPager) view.findViewById(R.id.public_relation_activity_tab_body);


    initEvents();

    tabLayout.setupWithViewPager(viewPager);
    tabLayout.getTabAt(0).setText(R.string.public_relation_activity_info);
    tabLayout.getTabAt(1).setText(R.string.public_relation_activity_docs);

    setHasOptionsMenu(true);

    return view;
  }

  public void initEvents(){

    FragmentStatePagerAdapter pagerAdapter = new FragmentStatePagerAdapter(getActivity().getSupportFragmentManager()) {
      @Override
      public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
          case 0:
            fragment = new ActivityInformationFragment();
            break;
          case 1:
            fragment = new DocumentsFragment();
            break;
        }
        return fragment;
      }

      @Override
      public int getCount() {
        return 2;
      }
    };
    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    viewPager.setAdapter(pagerAdapter);

    tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
      @Override
      public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override
      public void onTabReselected(TabLayout.Tab tab) {

      }
    });
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.search, menu);
  }
}
