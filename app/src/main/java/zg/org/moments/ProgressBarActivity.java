package zg.org.moments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by gzeng on 24/05/2017.
 */

public class ProgressBarActivity extends AppCompatActivity{
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.progressbar);

    LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearLayout);
    TextView textView = (TextView)findViewById(R.id.text);
    linearLayout.setEnabled(false);

    RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.progressbarContainer);
    textView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(ProgressBarActivity.this, "hello", Toast.LENGTH_SHORT).show();
      }
    });
    relativeLayout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(ProgressBarActivity.this, "relative layout", Toast.LENGTH_SHORT).show();
      }
    });
  }
}
