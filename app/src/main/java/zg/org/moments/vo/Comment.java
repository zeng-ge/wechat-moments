package zg.org.moments.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gzeng on 04/05/2017.
 */

public class Comment {
//  "content": "Good.",
//    "sender": {
//    "username": "outman",
//      "nick": "Super hero",
//      "avatar": "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRJm8UXZ0mYtjv1a48RKkFkdyd4kOWLJB0o_l7GuTS8-q8VF64w"
//  }
  @SerializedName("content")
  private String content = null;
  @SerializedName("sender")
  private User sender = null;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public User getSender() {
    return sender;
  }

  public void setSender(User sender) {
    this.sender = sender;
  }
}
