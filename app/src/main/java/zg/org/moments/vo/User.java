package zg.org.moments.vo;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gzeng on 04/05/2017.
 */

public class User {
//  "profile-image": "http://img2.findthebest.com/sites/default/files/688/media/images/Mingle_159902_i0.png",
//    "avatar": "http://info.thoughtworks.com/rs/thoughtworks2/images/glyph_badge.png",
//    "nick": "John Smith",
//    "username": "jsmith"
  @SerializedName("username")
  private String name = null;
  @SerializedName("nick")
  private String nickName = null;
  @SerializedName("avatar")
  private String avatar = null;
  @SerializedName("profile-image")
  private String profileImage = null;

  private Bitmap avatarBitmap = null;
  private Bitmap profileBitmap = null;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getProfileImage() {
    return profileImage;
  }

  public void setProfileImage(String profileImage) {
    this.profileImage = profileImage;
  }

  public Bitmap getAvatarBitmap() {
    return avatarBitmap;
  }

  public void setAvatarBitmap(Bitmap avatarBitmap) {
    this.avatarBitmap = avatarBitmap;
  }

  public Bitmap getProfileBitmap() {
    return profileBitmap;
  }

  public void setProfileBitmap(Bitmap profileBitmap) {
    this.profileBitmap = profileBitmap;
  }
}
