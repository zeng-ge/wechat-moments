package zg.org.moments.vo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gzeng on 04/05/2017.
 */

public class Tween {
  @SerializedName("content")
  private String content = null;
  @SerializedName("images")
  private List<Image> images = null;
  @SerializedName("sender")
  private User sender = null;
  @SerializedName("comments")
  private List<Comment> comments = null;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public List<Image> getImages() {
    return images;
  }

  public void setImages(List<Image> images) {
    this.images = images;
  }

  public User getSender() {
    return sender;
  }

  public void setSender(User sender) {
    this.sender = sender;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }
}
