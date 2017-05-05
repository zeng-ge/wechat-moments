package zg.org.moments.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import java.util.List;

import zg.org.moments.vo.Tween;
import zg.org.moments.vo.User;

/**
 * Created by gzeng on 04/05/2017.
 */

public class FetchHandlerThread extends HandlerThread {
  private static final int LOAD_USER = 1;
  private static final int LOAD_TWEENS = 2;
  private static String TAG = "fetchHandlerThread";

  private Handler responseHandler = null;
  private Handler loadHandler = null;
  private FetchCallback fetchCallback = null;

  public interface FetchCallback {
    void onFetchUser(User user);

    void onFetchTweens(List<Tween> tweens);
  }

  public FetchHandlerThread(Handler responseHandler, FetchCallback fetchCallback) {
    super(TAG);
    this.responseHandler = responseHandler;
    this.fetchCallback = fetchCallback;
  }

  @Override
  protected void onLooperPrepared() {
    this.loadHandler = new Handler(this.getLooper()) {
      @Override
      public void handleMessage(Message msg) {
        switch (msg.what) {
          case LOAD_USER:
            User user = Fetch.loadUser((String) msg.obj);
            onFetchUser(user);
            break;
          case LOAD_TWEENS:
            List<Tween> tweens = Fetch.loadTweens((String) msg.obj);
            onFetchTweens(tweens);
            break;
        }
      }
    };
    Log.i(TAG, "init fetch handler thread's handler");
    responseHandler.obtainMessage(Constants.UI_THREAD).sendToTarget();
  }

  public void fetchUser(String url) {
    Log.i(TAG, "fetch user:" + this.loadHandler.toString());
    this.loadHandler.obtainMessage(LOAD_USER, url).sendToTarget();
  }

  public void fetchTweens(String url) {
    Log.i(TAG, "fetch tweens:" + this.loadHandler.toString());
    this.loadHandler.obtainMessage(LOAD_TWEENS, url).sendToTarget();
  }

  public void onFetchUser(final User user) {
    responseHandler.post(new Runnable() {
      @Override
      public void run() {
        fetchCallback.onFetchUser(user);
      }
    });
  }

  public void onFetchTweens(final List<Tween> tweens) {
    responseHandler.post(new Runnable() {
      @Override
      public void run() {
        fetchCallback.onFetchTweens(tweens);
      }
    });
  }
}
