package zg.org.moments;

import org.junit.Test;

import java.util.List;

import zg.org.moments.utils.Constants;
import zg.org.moments.utils.Fetch;
import zg.org.moments.vo.Tween;
import zg.org.moments.vo.User;

import static org.junit.Assert.assertEquals;

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
}
