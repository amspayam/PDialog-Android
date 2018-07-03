package ir.pkokabi.pdialogexample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import ir.pkokabi.pdialog.NetworkDialog;
import ir.pkokabi.pdialog.PDialog;


public class MainActivity extends AppCompatActivity implements PDialog.PositiveListener {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        AppCompatButton btn = findViewById(R.id.dialogBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PDialog
                        .Builder(context, 1)
                        .title("سلام چه خبر ، چطوری؟")
                        .positiveTitle("خوبم تو چطوری؟")
                        .negativeTitle("به تو چه؟")
                        .cornerRadius(8)
                        .setPositiveListener(MainActivity.this)
                        .build();
            }
        });
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new NetworkDialog
//                        .Builder(context)
//                        .isPersian(true)
//                        .setButtonBackground(R.drawable.shape_internet)
//                        .build();
//            }
//        });

    }

    @Override
    public void onPositiveClick(int id) {

    }

//    @Override
//    public void onPositiveClick() {
//        Toast.makeText(this, "OK Clicked", Toast.LENGTH_SHORT).show();
//    }
}
