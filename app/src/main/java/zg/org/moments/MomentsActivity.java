package zg.org.moments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import zg.org.moments.utils.FetchHandlerThread;

public class MomentsActivity extends AppCompatActivity {

  private Handler handler = null;
  private FetchHandlerThread fetchHandlerThread = null;
  private FetchHandlerThread.FetchCallback fetchCallback = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_moments);
    init();
  }

  private void init(){
    handler = new Handler();
    fetchCallback = new FetchHandlerThread.FetchCallback() {
      @Override
      public void onFetch(Object obj) {

      }
    };
    fetchHandlerThread = new FetchHandlerThread(handler, fetchCallback);
    initData();
  }

  private void initData(){

  }
}
