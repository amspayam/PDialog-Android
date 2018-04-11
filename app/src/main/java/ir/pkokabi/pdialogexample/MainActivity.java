package ir.pkokabi.pdialogexample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;


public class MainActivity extends AppCompatActivity  {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        AppCompatButton btn = findViewById(R.id.dialogBtn);

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new PDialog
//                        .Builder(context)
//                        .title("سلام چه خبر ، چطوری؟")
//                        .positiveTitle("خوبم تو چطوری؟")
//                        .negativeTitle("به تو چه؟")
//                        .cornerRadius(2)
//                        .setPositiveListener(MainActivity.this)
//                        .build();
//            }
//        });

    }

//    @Override
//    public void onPositiveClick() {
//        Toast.makeText(this, "OK Clicked", Toast.LENGTH_SHORT).show();
//    }
}
