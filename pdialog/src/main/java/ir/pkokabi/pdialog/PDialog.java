package ir.pkokabi.pdialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatButton;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.TextView;


public class PDialog extends AppCompatDialog implements View.OnClickListener {

    protected TextView titleTv;
    protected AppCompatButton firstBtn, secondBtn;
    private final String titleText, firstButtonTitle, secondButtonTitle;
    private final int titleColor, firstButtonColor, secondButtonColor;
    private final int titleSize, firstButtonSize, secondButtonSize;
    private final boolean isCancelable;
    private final FirstButtonCallBack firstButtonCallBack;
    private final SecondButtonCallBack secondButtonCallBack;

    private PDialog(Context context, PDialogBuilder builder) {
        super(context);
        this.titleText = builder.titleText;
        this.firstButtonTitle = builder.firstButtonTitle;
        this.secondButtonTitle = builder.secondButtonTitle;
        this.titleColor = builder.titleColor;
        this.firstButtonColor = builder.firstButtonColor;
        this.secondButtonColor = builder.secondButtonColor;
        this.titleSize = builder.titleSize;
        this.firstButtonSize = builder.firstButtonSize;
        this.secondButtonSize = builder.secondButtonSize;
        this.isCancelable = builder.isCancelable;
        this.firstButtonCallBack = builder.firstButtonCallBack;
        this.secondButtonCallBack = builder.secondButtonCallBack;
        show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.p_dialog);

        setCancelable(isCancelable);
        if (getWindow() != null)
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        titleTv = findViewById(R.id.titleTv);
        firstBtn = findViewById(R.id.firstBtn);
        secondBtn = findViewById(R.id.secondBtn);

        titleTv.setText(titleText);
        titleTv.setTextColor(ContextCompat.getColor(getContext(), titleColor));
        titleTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleSize);

        if (firstButtonTitle == null)
            firstBtn.setVisibility(View.GONE);
        else {
            firstBtn.setText(firstButtonTitle);
            firstBtn.setTextColor(ContextCompat.getColor(getContext(), firstButtonColor));
            firstBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, firstButtonSize);
            firstBtn.setOnClickListener(this);
        }

        if (secondButtonTitle == null)
            secondBtn.setVisibility(View.GONE);
        else {
            secondBtn.setText(secondButtonTitle);
            secondBtn.setTextColor(ContextCompat.getColor(getContext(), secondButtonSize));
            secondBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, secondButtonColor);
            secondBtn.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.firstBtn) {
            firstButtonCallBack.onFirstButtonClick();
            dismiss();
        } else {
            secondButtonCallBack.onSecondButtonClick();
            dismiss();
        }
    }

    public String getTitleText() {
        return titleText;
    }

    public String getFirstButtonTitle() {
        return firstButtonTitle;
    }

    public String getSecondButtonTitle() {
        return secondButtonTitle;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public int getFirstButtonColor() {
        return firstButtonColor;
    }

    public int getSecondButtonColor() {
        return secondButtonColor;
    }

    public int getTitleSize() {
        return titleSize;
    }

    public int getFirstButtonSize() {
        return firstButtonSize;
    }

    public int getSecondButtonSize() {
        return secondButtonSize;
    }

    public boolean isCancelable() {
        return isCancelable;
    }

    public FirstButtonCallBack getFirstButtonCallBack() {
        return firstButtonCallBack;
    }

    public SecondButtonCallBack getSecondButtonCallBack() {
        return secondButtonCallBack;
    }

    /*Class Builder===============================================================================*/
    public static class PDialogBuilder {

        private final Context context;
        private final String titleText;
        private String firstButtonTitle, secondButtonTitle;
        private int titleColor = R.color.pDialogBlack, firstButtonColor = R.color.pDialogBlack, secondButtonColor = R.color.pDialogBlack;
        private int titleSize = 16, firstButtonSize = 14, secondButtonSize = 14;
        private boolean isCancelable = true;

        private FirstButtonCallBack firstButtonCallBack;
        private SecondButtonCallBack secondButtonCallBack;

        public PDialogBuilder(Context context, String titleText) {
            this.context = context;
            this.titleText = titleText;
        }

        public PDialogBuilder firstButtonTitle(String firstButtonTitle) {
            this.firstButtonTitle = firstButtonTitle;
            return this;
        }

        public PDialogBuilder secondButtonTitle(String secondButtonTitle) {
            this.secondButtonTitle = secondButtonTitle;
            return this;
        }

        public PDialogBuilder titleColor(int titleColor) {
            this.titleColor = titleColor;
            return this;
        }

        public PDialogBuilder firstButtonColor(int firstButtonColor) {
            this.firstButtonColor = firstButtonColor;
            return this;
        }

        public PDialogBuilder secondButtonColor(int secondButtonColor) {
            this.secondButtonColor = secondButtonColor;
            return this;
        }

        public PDialogBuilder titleSize(int titleSize) {
            this.titleSize = titleSize;
            return this;
        }

        public PDialogBuilder firstButtonSize(int firstButtonSize) {
            this.firstButtonSize = firstButtonSize;
            return this;
        }

        public PDialogBuilder secondButtonSize(int secondButtonSize) {
            this.secondButtonSize = secondButtonSize;
            return this;
        }

        public PDialogBuilder isCancelable(boolean isCancelable) {
            this.isCancelable = isCancelable;
            return this;
        }

        public PDialogBuilder setFirstButtonCallBack(FirstButtonCallBack firstButtonCallBack) {
            this.firstButtonCallBack = firstButtonCallBack;
            return this;
        }

        public PDialogBuilder setSecondButtonCallBack(SecondButtonCallBack secondButtonCallBack) {
            this.secondButtonCallBack = secondButtonCallBack;
            return this;
        }

        public PDialog build() {
            return new PDialog(context, this);
        }

    }

    /*Dialog Interfaces===========================================================================*/
    public interface FirstButtonCallBack {
        void onFirstButtonClick();
    }

    public interface SecondButtonCallBack {
        void onSecondButtonClick();
    }


}
