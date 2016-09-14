package ggikko.me.destroyboilerplateapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ggikko.me.destroyboilerplate.SayHello;

@SayHello
public class MainActivity extends AppCompatActivity {

  @BindView(R.id.hello) TextView hello;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
  }
}
