package zg.org.moments.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import zg.org.moments.R;

/**
 * Created by gzeng on 12/05/2017.
 */

public class ActivityInformationFragment extends Fragment{

  private LinearLayout teamMemberLinearLayout = null;

  public static Fragment getInstance(){
    return new ActivityInformationFragment();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.public_relation_activity_informations, container, false);

    return view;
  }


}
