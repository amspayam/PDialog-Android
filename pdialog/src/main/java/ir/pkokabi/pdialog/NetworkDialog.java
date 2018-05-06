package ir.pkokabi.pdialog;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;


public class NetworkDialog extends AppCompatDialog implements View.OnClickListener {

    private final Context context;
    private final boolean isPersian;
    private final int buttonRes;

    private NetworkDialog(Context context, Builder builder) {
        super(context);
        this.context = builder.context;
        this.isPersian = builder.isPersian;
        this.buttonRes = builder.buttonRes;
        show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.view_network);

        setCancelable(true);
        if (getWindow() != null)
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView noInternetTv = findViewById(R.id.noInternetTv);
        TextView noInternetDescTv = findViewById(R.id.noInternetDescTv);
        TextView pleaseTurnOnTv = findViewById(R.id.pleaseTurnOnTv);
        LinearLayout wifiBtn = findViewById(R.id.wifiBtn);
        LinearLayout mobileDataBtn = findViewById(R.id.mobileDataBtn);

        if (isPersian) {
            assert noInternetTv != null;
            noInternetTv.setText("عدم دسترسی به اینترنت");
            assert noInternetDescTv != null;
            noInternetDescTv.setText("شما به اینترنت دسترسی ندارید");
            assert pleaseTurnOnTv != null;
            pleaseTurnOnTv.setText("روشن کنید");
        }

        if (buttonRes != 0) {
            assert wifiBtn != null;
            wifiBtn.setBackgroundResource(buttonRes);
            assert mobileDataBtn != null;
            mobileDataBtn.setBackgroundResource(buttonRes);
        }

        assert wifiBtn != null;
        wifiBtn.setOnClickListener(this);
        assert mobileDataBtn != null;
        mobileDataBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.wifiBtn)
            context.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        else
            context.startActivity(new Intent(Settings.ACTION_SETTINGS));
        dismiss();
    }

    /*Class Builder===============================================================================*/
    public static class Builder {
        private final Context context;
        private boolean isPersian;
        private int buttonRes = 0;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder isPersian(boolean isPersian) {
            this.isPersian = isPersian;
            return this;
        }

        public Builder setButtonBackground(int resId) {
            this.buttonRes = resId;
            return this;
        }

        public NetworkDialog build() {
            return new NetworkDialog(context, this);
        }

    }
}
