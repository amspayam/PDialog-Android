package ir.pkokabi.pdialog;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.TextView;


public class PDialog extends AppCompatDialog implements View.OnClickListener {

    private final int id;

    private final String title;
    private final int titleColor;
    private final int titleSize;
    private final boolean isTitleBold;

    private final String subTitle;
    private final int subTitleColor;
    private final int subTitleSize;
    private final boolean isSubTitleBold;

    private final String positiveTitle;
    private final int positiveColor;
    private final int positiveSize;
    private final boolean isPositiveTitleBold;

    private final String negativeTitle;
    private final int negativeColor;
    private final int negativeSize;
    private final boolean isNegativeTitleBold;

    private final int cornerRadius;
    private final boolean isCancelable;
    private final PositiveListener positiveListener;
    private final NegativeListener negativeListener;

    private PDialog(Context context, Builder builder) {
        super(context);
        this.id = builder.id;
        this.title = builder.title;
        this.titleColor = builder.titleColor;
        this.titleSize = builder.titleSize;
        this.isTitleBold = builder.isTitleBold;

        this.subTitle = builder.subTitle;
        this.subTitleColor = builder.subTitleColor;
        this.subTitleSize = builder.subTitleSize;
        this.isSubTitleBold = builder.isSubTitleBold;

        this.positiveTitle = builder.positiveTitle;
        this.positiveColor = builder.positiveColor;
        this.positiveSize = builder.positiveSize;
        this.isPositiveTitleBold = builder.isPositiveTitleBold;

        this.negativeTitle = builder.negativeTitle;
        this.negativeColor = builder.negativeColor;
        this.negativeSize = builder.negativeSize;
        this.isNegativeTitleBold = builder.isNegativeTitleBold;

        this.cornerRadius = builder.cornerRadius;
        this.isCancelable = builder.isCancelable;
        this.positiveListener = builder.positiveListener;
        this.negativeListener = builder.negativeListener;
        show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.view_dialog);

        setCancelable(isCancelable);
        if (getWindow() != null)
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        CardView rootView = findViewById(R.id.rootView);
        TextView titleTv = findViewById(R.id.titleTv);
        TextView subTitleTv = findViewById(R.id.subTitleTv);
        AppCompatButton positiveBtn = findViewById(R.id.positiveBtn);
        AppCompatButton negativeBtn = findViewById(R.id.negativeBtn);

        assert titleTv != null;
        if (title != null) {
            titleTv.setText(title);
            assert rootView != null;
            if (isPersian(title))
                rootView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            else
                rootView.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }
        titleTv.setTextColor(ContextCompat.getColor(getContext(), titleColor));
        titleTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleSize);
        if (isTitleBold)
            titleTv.setTypeface(titleTv.getTypeface(), Typeface.BOLD);

        if (subTitle == null) {
            assert subTitleTv != null;
            subTitleTv.setVisibility(View.GONE);
        } else {
            assert subTitleTv != null;
            subTitleTv.setText(subTitle);
            subTitleTv.setTextColor(ContextCompat.getColor(getContext(), subTitleColor));
            subTitleTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, subTitleSize);
            if (isSubTitleBold)
                subTitleTv.setTypeface(subTitleTv.getTypeface(), Typeface.BOLD);
        }

        if (positiveTitle == null) {
            assert positiveBtn != null;
            positiveBtn.setVisibility(View.GONE);
        } else {
            assert positiveBtn != null;
            positiveBtn.setText(positiveTitle);
            positiveBtn.setTextColor(ContextCompat.getColor(getContext(), positiveColor));
            positiveBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, positiveSize);
            positiveBtn.setOnClickListener(this);
            if (isPositiveTitleBold)
                positiveBtn.setTypeface(positiveBtn.getTypeface(), Typeface.BOLD);
        }

        if (negativeTitle == null) {
            assert negativeBtn != null;
            negativeBtn.setVisibility(View.GONE);
        } else {
            assert negativeBtn != null;
            negativeBtn.setText(negativeTitle);
            negativeBtn.setTextColor(ContextCompat.getColor(getContext(), negativeColor));
            negativeBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, negativeSize);
            negativeBtn.setOnClickListener(this);
            if (isNegativeTitleBold)
                negativeBtn.setTypeface(negativeBtn.getTypeface(), Typeface.BOLD);
        }

        if (cornerRadius != dpToPx(8)) {
            assert rootView != null;
            rootView.setRadius(cornerRadius);
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.positiveBtn) {
            if (positiveListener != null)
                positiveListener.onPositiveClick(id);
            dismiss();
        } else {
            if (negativeListener != null)
                negativeListener.onNegativeClick(id);
            dismiss();
        }
    }

    public String getTitle() {
        return title;
    }

    public PositiveListener getPositiveListener() {
        return positiveListener;
    }

    public NegativeListener getNegativeListener() {
        return negativeListener;
    }

    /*Class Builder===============================================================================*/
    public static class Builder {

        private final Context context;
        private int id;
        private String title;
        private int titleColor = R.color.pDialogBlack;
        private int titleSize = 16;
        private boolean isTitleBold = false;

        private String subTitle;
        private int subTitleColor = R.color.pDialogBlack;
        private int subTitleSize = 15;
        private boolean isSubTitleBold = false;

        private String positiveTitle;
        private int positiveColor = R.color.pDialogBlack;
        private int positiveSize = 14;
        private boolean isPositiveTitleBold = false;

        private String negativeTitle;
        private int negativeColor = R.color.pDialogBlack;
        private int negativeSize = 14;
        private boolean isNegativeTitleBold = false;

        private int cornerRadius = dpToPx(8);
        private boolean isCancelable = true;

        private PositiveListener positiveListener;
        private NegativeListener negativeListener;

        public Builder(Context context, int id) {
            this.context = context;
            this.id = id;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder titleColor(int titleColor) {
            this.titleColor = titleColor;
            return this;
        }

        public Builder titleSize(int titleSize) {
            this.titleSize = titleSize;
            return this;
        }

        public Builder isTitleBold(boolean isTitleBold) {
            this.isTitleBold = isTitleBold;
            return this;
        }

        public Builder subTitle(String subTitle) {
            this.subTitle = subTitle;
            return this;
        }

        public Builder subTitleColor(int subTitleColor) {
            this.subTitleColor = subTitleColor;
            return this;
        }

        public Builder subTitleSize(int subTitleSize) {
            this.subTitleSize = subTitleSize;
            return this;
        }

        public Builder isSubTitleBold(boolean isSubTitleBold) {
            this.isSubTitleBold = isSubTitleBold;
            return this;
        }

        public Builder positiveTitle(String positiveTitle) {
            this.positiveTitle = positiveTitle;
            return this;
        }

        public Builder positiveColor(int positiveColor) {
            this.positiveColor = positiveColor;
            return this;
        }

        public Builder positiveSize(int positiveSize) {
            this.positiveSize = positiveSize;
            return this;
        }

        public Builder isPositiveTitleBold(boolean isPositiveTitleBold) {
            this.isPositiveTitleBold = isPositiveTitleBold;
            return this;
        }

        public Builder negativeTitle(String negativeTitle) {
            this.negativeTitle = negativeTitle;
            return this;
        }

        public Builder negativeColor(int negativeColor) {
            this.negativeColor = negativeColor;
            return this;
        }

        public Builder negativeSize(int negativeSize) {
            this.negativeSize = negativeSize;
            return this;
        }

        public Builder isNegativeTitleBold(boolean isNegativeTitleBold) {
            this.isNegativeTitleBold = isNegativeTitleBold;
            return this;
        }

        public Builder cornerRadius(int cornerRadius) {
            this.cornerRadius = cornerRadius;
            return this;
        }

        public Builder isCancelable(boolean isCancelable) {
            this.isCancelable = isCancelable;
            return this;
        }

        public Builder setPositiveListener(PositiveListener positiveListener) {
            this.positiveListener = positiveListener;
            return this;
        }

        public Builder setNegativeListener(NegativeListener negativeListener) {
            this.negativeListener = negativeListener;
            return this;
        }

        public PDialog build() {
            return new PDialog(context, this);
        }

    }

    /*Dialog Interfaces===========================================================================*/
    public interface PositiveListener {
        void onPositiveClick(int id);
    }

    public interface NegativeListener {
        void onNegativeClick(int id);
    }

    /*Methods=====================================================================================*/
    private boolean isPersian(String string) {
        for (int i = 0; i < Character.codePointCount(string, 0, string.length()); i++) {
            int c = string.codePointAt(i);
            if (c >= 0x0600 && c <= 0x06FF || c == 0xFB8A)
                return true;
        }
        return false;
    }

    private static int dpToPx(float dp) {
        return (int) ((dp) * Resources.getSystem().getDisplayMetrics().density);
    }


}
