package zg.org.moments.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import java.util.List;

import zg.org.moments.vo.Tween;
import zg.org.moments.vo.User;

/**
 * Created by gzeng on 04/05/2017.
 */

public class FetchHandlerThread extends HandlerThread{
  private static final int LOAD_USER = 1;
  private static final int LOAD_TWEENS = 2;
  private static String TAG = "fetchHandlerThread";

  private Handler responseHandler = null;
  private Handler loadHandler = null;
  private FetchCallback fetchCallback = null;

  public interface FetchCallback{
    void onFetch(Object obj);
  }

  public FetchHandlerThread(Handler responseHandler, FetchCallback fetchCallback) {
    super(TAG);
    this.responseHandler = responseHandler;
    this.fetchCallback = fetchCallback;
  }

  @Override
  protected void onLooperPrepared() {
    loadHandler = new Handler(){
      @Override
      public void handleMessage(Message msg) {
        switch (msg.what){
          case LOAD_USER:
            User user = Fetch.loadUser((String)msg.obj);
            handleFetch(user);
          case LOAD_TWEENS:
            List<Tween> tweens = Fetch.loadTweens((String)msg.obj);
            handleFetch(tweens);
        }
      }
    };
  }

  public void fetchUser(String url){
    loadHandler.obtainMessage(LOAD_USER, url).sendToTarget();
  }

  public void fetchTweens(String url){
    loadHandler.obtainMessage(LOAD_TWEENS, url).sendToTarget();
  }

  private void handleFetch(final Object obj){
    responseHandler.post(new Runnable() {
      @Override
      public void run() {
        fetchCallback.onFetch(obj);
      }
    });
  }

}
