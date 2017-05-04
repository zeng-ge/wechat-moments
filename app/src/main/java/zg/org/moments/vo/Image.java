package zg.org.moments.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gzeng on 04/05/2017.
 */

public class Image {
  @SerializedName("url")
  private String url = null;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
