package zg.org.moments.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

import zg.org.moments.vo.Image;

/**
 * Created by gzeng on 06/05/2017.
 */

public abstract  class GridViewAdpter extends BaseAdapter{
  private Context context = null;
  private List<Image> images = null;

  public GridViewAdpter(Context context, List<Image> images) {
    this.context = context;
    this.images = images;
  }

  @Override
  public int getCount() {
    return this.images.size();
  }

  @Override
  public Object getItem(int position) {
    return images.get(position);
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ImageView imageView;
    if (convertView == null) {
      imageView = new ImageView(context);
      imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
      imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
      imageView.setPadding(8, 8, 8, 8);
    } else {
      imageView = (ImageView) convertView;
    }
    updateImageBitmap(imageView, images.get(position));
    return imageView;
  }



  protected abstract void updateImageBitmap(ImageView imageView, Image image);
}
