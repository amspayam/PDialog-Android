package ir.pkokabi.pdialogexample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements PDialog.FirstButtonCallBack {

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
                        .PDialogBuilder(context, "hi")
                        .titleColor(R.color.colorPrimary)
                        .isCancelable(false)
                        .firstButtonTitle("Ok")
                        .secondButtonTitle("No")
                        .setFirstButtonCallBack(MainActivity.this)
                        .build();
            }
        });

    }

    @Override
    public void onFirstButtonClick() {
        Toast.makeText(this, "OK Clicked", Toast.LENGTH_SHORT).show();
    }
}
