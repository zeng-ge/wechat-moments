package zg.org.moments;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by gzeng on 08/06/2017.
 */

public class ViewPagerActivity extends AppCompatActivity {
  private float MIN_SCALE = 0.9f;
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.viewpager);
    ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);

    viewPager.setOffscreenPageLimit(4);
    viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
      @Override
      public void transformPage(View page, float position) {
        if(position < -1){
          page.setScaleY(MIN_SCALE);
        }else if(position<= 1){
          //
          float scale = Math.max(MIN_SCALE,1 - Math.abs(position));
          page.setScaleY(scale);
            /*page.setScaleX(scale);
            if(position<0){
                page.setTranslationX(width * (1 - scale) /2);
            }else{
                page.setTranslationX(-width * (1 - scale) /2);
            }*/

        }else{
          page.setScaleY(MIN_SCALE);
        }
      }
    });

    viewPager.setAdapter(new PagerAdapter() {
      @Override
      public int getCount() {
        return 10;
      }

      @Override
      public boolean isViewFromObject(View view, Object object) {
        return view == object;
      }


      @Override
      public Object instantiateItem(ViewGroup container, int position) {
        View view = getLayoutInflater().inflate(R.layout.viewpager_item, container, false);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(position + "");
        view.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            showPopup();
          }
        });
        container.addView(view);
        return view;
      }

      @Override
      public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View)object;
        container.removeView(view);
      }
    });

  }

  public void showPopup(){
    WindowManager windowManager = getWindowManager();
    TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.popup, null);

    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
    layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
    layoutParams.format = PixelFormat.TRANSLUCENT;
    layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_PANEL;
    layoutParams.gravity = Gravity.CENTER;
    layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
      | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
    windowManager.addView(textView, layoutParams);
  }

}
