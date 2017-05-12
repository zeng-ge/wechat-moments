package zg.org.moments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import zg.org.moments.presenter.PublicRelationFragment;

/**
 * Created by gzeng on 11/05/2017.
 */

public class PublicRelationActivity extends AppCompatActivity{
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.public_relation_activity);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setDisplayShowTitleEnabled(false);
//    actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);


    FragmentManager fragmentManager = getSupportFragmentManager();
    Fragment fragment = (Fragment) fragmentManager.findFragmentById(R.id.public_relation_fragment);
    if(fragment == null){
      fragment = PublicRelationFragment.getInstance();
      fragmentManager.beginTransaction()
        .add(R.id.public_relation_fragment, fragment)
        .commit();
    }
  }
}
