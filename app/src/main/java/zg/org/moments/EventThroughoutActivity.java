package zg.org.moments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by gzeng on 24/05/2017.
 */

public class EventThroughoutActivity extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.event_throughout);
    TextView below = (TextView) findViewById(R.id.below);
    TextView above = (TextView) findViewById(R.id.above);
    ImageView imageView = (ImageView) findViewById(R.id.image_view);
    Picasso.with(this).load("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png").into(imageView);

    Button button = (Button) findViewById(R.id.transitionButton);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    below.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(EventThroughoutActivity.this, "below", Toast.LENGTH_SHORT).show();
      }
    });
    above.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(EventThroughoutActivity.this, "above", Toast.LENGTH_SHORT).show();
      }
    });
  }
}
