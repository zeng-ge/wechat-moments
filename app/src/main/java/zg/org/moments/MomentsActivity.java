package zg.org.moments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zg.org.moments.adpter.GridViewAdpter;
import zg.org.moments.adpter.ListViewAdapter;
import zg.org.moments.utils.Constants;
import zg.org.moments.utils.FetchHandlerThread;
import zg.org.moments.vo.Comment;
import zg.org.moments.vo.Image;
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
    private GridView gridView = null;
    private ListView listView = null;

    public TweenViewHoder(View itemView) {
      super(itemView);
      tweenView = itemView;
      senderView = (TextView)itemView.findViewById(R.id.senderName);
      contentView = (TextView)itemView.findViewById(R.id.tweenContent);
      imageView = (ImageView)itemView.findViewById(R.id.senderPicture);
      gridView = (GridView)itemView.findViewById(R.id.gridView);
      listView = (ListView)itemView.findViewById(R.id.tween_comments);
    }

    public void updateViewHoder(Tween tween, List<Tween> tweens, int position){
      String content = tween.getContent();
      List<Image> images = tween.getImages();
      List<Comment> comments = tween.getComments();
      String name = null;
      String avator = null;
      Bitmap avatarBitmap = null;

      final User sender = tween.getSender();
      if(sender != null){
        name = sender.getName();
        avator = sender.getAvatar();
        avatarBitmap = sender.getAvatarBitmap();
      }
      if(name == null){
        name = "";

      }
      if(content == null){
        content = "";
      }

      senderView.setText(name);
      contentView.setText(content);

      if(avatarBitmap != null){
        imageView.setImageBitmap(sender.getAvatarBitmap());
      }else if(avator != null){
        fetchHandlerThread.fetchImage(avator, new FetchImageCallback(sender, imageView));
      }
      if(images != null && images.size() > 0){
        updateImageGrid(images);
      }

      if(comments != null && comments.size() > 0){
        updateTweenComments(comments);
      }

    }

    public void updateTweenComments(List<Comment> comments){
      listView.setAdapter(new ListViewAdapter(MomentsActivity.this, comments) {
        @Override
        protected void updateText(TextView textView, Comment comment) {
          textView.setText(comment.getContent());
        }
      });
    }

    public void updateImageGrid(List<Image> images){
      gridView.setVisibility(View.VISIBLE);
      gridView.setAdapter(new GridViewAdpter(MomentsActivity.this, images) {
        @Override
        protected void updateImageBitmap(final ImageView imageView, final Image image) {
          if(image.getBitmap() != null){
            imageView.setImageBitmap(image.getBitmap());
          }else{
            fetchHandlerThread.fetchImage(image.getUrl(), new FetchHandlerThread.InvokeCallback(){
              @Override
              public void callback(Object obj) {
                Bitmap bitmap = (Bitmap)obj;
                image.setBitmap(bitmap);
                imageView.setImageBitmap(bitmap);
              }
            });
          }
        }
      });
    }
  }

  private class FetchImageCallback implements FetchHandlerThread.InvokeCallback{
    private User user = null;
    private ImageView imageView = null;

    public FetchImageCallback(User user, ImageView imageView) {
      this.user = user;
      this.imageView = imageView;
    }

    @Override
    public void callback(Object obj) {
      Bitmap bitmap = (Bitmap) obj;
      user.setAvatarBitmap(bitmap);
      imageView.setImageBitmap(bitmap);
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
      viewHoder.updateViewHoder(tweens.get(position), tweens, position);
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
      List<Tween> tweenList = new ArrayList<Tween>();
      for(Tween tween : tweens){
        String content = tween.getContent();
        User sender = tween.getSender();
        List<Image> images = tween.getImages();
        if(content != null || sender != null || (images != null && images.size() > 0)){
          tweenList.add(tween);
        }
      }
      recycleView.setAdapter(new TweenAdaper(tweenList));
    }

    @Override
    public void onFetchImage(Bitmap bitmap, String url) {

    }
  }
}
