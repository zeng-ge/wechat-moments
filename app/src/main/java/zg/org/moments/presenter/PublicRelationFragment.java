package zg.org.moments.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zg.org.moments.R;

/**
 * Created by gzeng on 11/05/2017.
 */

public class PublicRelationFragment extends Fragment {

  public static String TAG = "public_relation_fragment";
  public ViewPager viewPager = null;
  public TabLayout tabLayout = null;

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

    tabLayout.addTab(tabLayout.newTab().setText(R.string.public_relation_activity_info));
    tabLayout.addTab(tabLayout.newTab().setText(R.string.public_relation_activity_docs));

    tabLayout.setupWithViewPager(viewPager);

    return view;
  }

  public void initEvents(){
    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    viewPager.setAdapter(new PagerAdapter() {
      @Override
      public int getCount() {
        return 0;
      }

      @Override
      public boolean isViewFromObject(View view, Object object) {
        return false;
      }
    });

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


}
