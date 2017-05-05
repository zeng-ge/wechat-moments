package zg.org.moments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zg.org.moments.utils.Constants;
import zg.org.moments.utils.FetchHandlerThread;
import zg.org.moments.vo.Tween;
import zg.org.moments.vo.User;

public class MomentsActivity extends AppCompatActivity {

  private Handler handler = null;
  private FetchHandlerThread fetchHandlerThread = null;
  private FetchHandlerThread.FetchCallback fetchCallback = null;
  private TextView textView = null;
  private RecyclerView recycleView = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    hideTitle();
    setContentView(R.layout.activity_moments);
    init();
  }

  private void init(){
    initRecycleView();
    handler = new MomentsHandler();
    fetchCallback = new FetchHandlerCallback();
    fetchHandlerThread = new FetchHandlerThread(handler, fetchCallback);
    fetchHandlerThread.start();
  }

  private void hideTitle(){
//    requestWindowFeature(Window.FEATURE_NO_TITLE);
//    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    getSupportActionBar().hide();
  }

  public void initRecycleView(){
    recycleView = (RecyclerView)findViewById(R.id.momentRecyclerView);
    recycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    recycleView.setAdapter(new TweenAdaper(new ArrayList<Tween>()));
  }

  public void doFetch(){
    fetchHandlerThread.fetchUser(Constants.LOAD_USER);
    fetchHandlerThread.fetchTweens(Constants.LOAD_TWEENS);
  }

  private class MomentsHandler extends Handler{
    @Override
    public void handleMessage(Message msg) {
      switch (msg.what){
        case Constants.UI_THREAD:
          doFetch();
          break;
      }
    }
  }

  private class TweenViewHoder extends RecyclerView.ViewHolder{
    private View tweenView = null;
    private TextView senderView = null;
    private TextView contentView = null;
    private ImageView imageView = null;
    public TweenViewHoder(View itemView) {
      super(itemView);
      tweenView = itemView;
      senderView = (TextView)itemView.findViewById(R.id.senderName);
      contentView = (TextView)itemView.findViewById(R.id.tweenContent);
//      imageView = (ImageView) itemView.findViewById(R.id.senderPicture);
    }
    public void updateViewHoder(Tween tween){
      String sender = "";
      String content = "";
      if(tween.getSender() != null && tween.getSender() !=null && tween.getSender().getName() !=null){
        sender = tween.getSender().getName();
      }
      if(tween.getContent() != null ){
        content = tween.getContent();
      }
      senderView.setText(sender);
      contentView.setText(content);
//      imageView.setImageURI(Uri.parse(tween.getSender().getProfileImage()));
    }
  }

  private class TweenAdaper extends RecyclerView.Adapter<TweenViewHoder>{

    private List<Tween> tweens = null;

    public TweenAdaper(List<Tween> tweens) {
      this.tweens = tweens;
    }

    @Override
    public TweenViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
      LayoutInflater inflater = LayoutInflater.from(MomentsActivity.this);
      View view = inflater.inflate(R.layout.tween, parent, false);
      TweenViewHoder viewHoder = new TweenViewHoder(view);
      return viewHoder;
    }

    @Override
    public void onBindViewHolder(TweenViewHoder viewHoder, int position) {
      viewHoder.updateViewHoder(tweens.get(position));
    }

    @Override
    public int getItemCount() {
      return tweens.size();
    }
  }

  private class FetchHandlerCallback implements FetchHandlerThread.FetchCallback{
    @Override
    public void onFetchUser(User user) {

    }

    @Override
    public void onFetchTweens(List<Tween> tweens) {
      recycleView.setAdapter(new TweenAdaper(tweens));
    }
  }
}
