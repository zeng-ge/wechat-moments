package zg.org.moments.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import zg.org.moments.vo.Tween;
import zg.org.moments.vo.User;

/**
 * Created by gzeng on 04/05/2017.
 */

public class Fetch {

  public static String load(String url){
    URL uri = null;
    HttpURLConnection connection = null;
    try {
      uri = new URL(url);
      connection = (HttpURLConnection) uri.openConnection();
      InputStream in = connection.getInputStream();
      if(connection.getResponseCode() != HttpURLConnection.HTTP_OK){
        throw new RuntimeException("connection failed");
      }
      return parseInputStream(in);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      if(connection != null){
        connection.disconnect();
      }
    }
  }

  public static String parseInputStream(InputStream input) throws IOException{
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    int bytesRead = 0;
    byte[] buffer = new byte[1024];
    while((bytesRead = input.read(buffer)) > 0){
      out.write(buffer, 0, bytesRead);
    }
    out.close();
    return new String(out.toByteArray());
  }



  public static User loadUser(String url){
    String userInfo = load(url);
    Gson gson = new Gson();
    return gson.fromJson(userInfo, User.class);
  }

  public static List<Tween> loadTweens(String url){
    List<Tween> tweens = new ArrayList<Tween>();
    String tweenInfos = load(url);
    Gson gson = new Gson();

    Type tweensType = new TypeToken<List<Tween>>(){}.getType();
    return gson.fromJson(tweenInfos, tweensType);
  }

}
