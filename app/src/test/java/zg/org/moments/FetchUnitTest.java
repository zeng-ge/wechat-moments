package zg.org.moments;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.List;

import zg.org.moments.utils.Constants;
import zg.org.moments.utils.Fetch;
import zg.org.moments.vo.Tween;
import zg.org.moments.vo.User;

import static junit.framework.Assert.assertEquals;

public class FetchUnitTest {

    @Test
    public void loadUser(){
      User user = Fetch.loadUser(Constants.LOAD_USER);
      assertEquals(user.getName(), "jsmith");
    }

  @Test
  public void loadTweens(){
    List<Tween> tweens = Fetch.loadTweens(Constants.LOAD_TWEENS);
    assertEquals(tweens.get(0).getSender().getName(), "jport");
  }

  @Test
  public void testOperator(){
    int i = 100 >> 2;
    System.out.print(i);
  }

  @Test
  public void jsonObject(){
    try {
      JSONObject map = new JSONObject();
      map.put("name", "sky");
      System.out.println(map.toString());
    } catch (JSONException e) {
      e.printStackTrace();
    }

  }

  @Test
  public void stringformat(){
    String raw = "/activities/%s/abc";
    String formated = String.format(raw, 123);
    System.out.println(formated);
  }

}
