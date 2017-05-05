package zg.org.moments.vo;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gzeng on 04/05/2017.
 */

public class Image {
  @SerializedName("url")
  private String url = null;

  private Bitmap bitmap = null;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Bitmap getBitmap() {
    return bitmap;
  }

  public void setBitmap(Bitmap bitmap) {
    this.bitmap = bitmap;
  }
}
