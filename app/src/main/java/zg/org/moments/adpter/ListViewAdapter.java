package zg.org.moments.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import zg.org.moments.R;
import zg.org.moments.vo.Comment;

/**
 * Created by gzeng on 06/05/2017.
 */

public abstract class ListViewAdapter extends BaseAdapter{
  private Context context = null;
  private List<Comment> comments = null;

  public ListViewAdapter(Context context, List<Comment> comments) {
    this.context = context;
    this.comments = comments;
  }

  @Override
  public int getCount() {
    return comments.size();
  }

  @Override
  public Object getItem(int position) {
    return comments.get(position);
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    TextView textView = null;
    if(convertView != null){
      textView = (TextView) convertView;
    }else{
      textView = (TextView) LayoutInflater.from(context).inflate(R.layout.tween_comment, null);
    }
    updateText(textView, comments.get(position));
    return textView;
  }

  protected abstract void updateText(TextView textView, Comment comment);

}
