package com.zxtitle.bar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author zhangxin
 * @date 2020/4/14
 * @desc 自定义标题栏
 **/
public class ZXTitleBar extends FrameLayout implements View.OnClickListener {

    private TextView tvLeft, tvTitle, tvRight;
    private ImageView ivLeft, ivRight;
    private FrameLayout flLeft, flRight;
    private LinearLayout llRoot;

    private String leftText = "";
    private String titleText = "";
    private String rightText = "";
    private int leftTextColor = Color.parseColor("#000000");
    private int titleTextColor = Color.parseColor("#000000");
    private int rightTextColor = Color.parseColor("#000000");
    private int backgroundColor = Color.parseColor("#ffffff");
    private boolean showLeftImage = true;
    private boolean showRightImage = false;
    private int rightImageRes = 0;
    private int leftImageRes = 0;
    private int backgroundImageRes = 0;
    private OnZXTitleBarClickListener clickListener;


    public ZXTitleBar(Context context) {
        this(context, null);
    }

    public ZXTitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZXTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = inflate(context, R.layout.view_zx_titlebar, this);
        findViewById(view);
        initTypedArray(context, attrs);
        init();
    }

    private void initTypedArray(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ZXTitleBar);
        leftText = a.getString(R.styleable.ZXTitleBar_leftText);
        titleText = a.getString(R.styleable.ZXTitleBar_titleText);
        rightText = a.getString(R.styleable.ZXTitleBar_rightText);
        leftTextColor = a.getColor(R.styleable.ZXTitleBar_leftTextColor, leftTextColor);
        titleTextColor = a.getColor(R.styleable.ZXTitleBar_titleTextColor, titleTextColor);
        rightTextColor = a.getColor(R.styleable.ZXTitleBar_rightTextColor, rightTextColor);
        backgroundColor = a.getColor(R.styleable.ZXTitleBar_backgroundColor, backgroundColor);
        showLeftImage = a.getBoolean(R.styleable.ZXTitleBar_showLeftImage, showLeftImage);
        showRightImage = a.getBoolean(R.styleable.ZXTitleBar_showRightImage, showRightImage);
        leftImageRes = a.getResourceId(R.styleable.ZXTitleBar_leftImageSrc, leftImageRes);
        backgroundImageRes = a.getResourceId(R.styleable.ZXTitleBar_backgroundImageSrc, backgroundImageRes);
        rightImageRes = a.getResourceId(R.styleable.ZXTitleBar_rightImageSrc, rightImageRes);
        a.recycle();
    }

    private void findViewById(View view) {
        tvLeft = view.findViewById(R.id.tvLeft);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvRight = view.findViewById(R.id.tvRight);
        ivLeft = view.findViewById(R.id.ivLeft);
        ivRight = view.findViewById(R.id.ivRight);
        flLeft = view.findViewById(R.id.flLeft);
        flRight = view.findViewById(R.id.flRight);
        llRoot = view.findViewById(R.id.llRoot);

        flLeft.setOnClickListener(this);
        tvTitle.setOnClickListener(this);
        flRight.setOnClickListener(this);
    }

    private void init() {

        if (!TextUtils.isEmpty(leftText)) {
            tvLeft.setVisibility(VISIBLE);
            tvLeft.setText(leftText);
            tvLeft.setTextColor(leftTextColor);
        }

        if (!TextUtils.isEmpty(titleText)) {
            tvTitle.setText(titleText);
            tvTitle.setTextColor(titleTextColor);
        }

        if (!TextUtils.isEmpty(rightText)) {
            tvRight.setVisibility(VISIBLE);
            tvRight.setText(rightText);
            tvRight.setTextColor(rightTextColor);
        }

        llRoot.setBackgroundColor(backgroundColor);

        if (showLeftImage) {
            ivLeft.setVisibility(VISIBLE);
        }

        if (showRightImage) {
            ivRight.setVisibility(VISIBLE);
        }

        if (leftImageRes > 0) {
            ivLeft.setImageResource(leftImageRes);
        }

        if (rightImageRes > 0) {
            ivRight.setImageResource(rightImageRes);
        }

        if (backgroundImageRes > 0) {
            llRoot.setBackgroundResource(backgroundImageRes);
        }
    }

    public void setOnZXTitleBarClickListener(OnZXTitleBarClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setLeftText(@Nullable String leftText) {
        this.leftText = leftText;
        if (!TextUtils.isEmpty(leftText)) {
            tvLeft.setText(leftText);
        }
    }


    public void setTitleText(@Nullable String title) {
        this.titleText = title;
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
    }


    public void setRightText(@Nullable String rightText) {
        this.rightText = rightText;
        if (!TextUtils.isEmpty(rightText)) {
            tvRight.setText(rightText);
        }
    }

    public void setLeftTextColor(@ColorRes int leftTextColor) {
        this.leftTextColor = leftTextColor;
        tvLeft.setTextColor(getResources().getColor(leftTextColor));
    }

    public void setRightTextColor(@ColorRes int rightTextColor) {
        this.rightTextColor = rightTextColor;
        tvRight.setTextColor(getResources().getColor(rightTextColor));
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        llRoot.setBackgroundColor(backgroundColor);
    }

    public void setRightImageRes(@DrawableRes int rightImageRes) {
        this.rightImageRes = rightImageRes;
        ivRight.setImageResource(rightImageRes);
    }

    public void setLeftImageRes(@DrawableRes int leftImageRes) {
        this.leftImageRes = leftImageRes;
        ivLeft.setImageResource(leftImageRes);
    }

    public void setBackgroundImageRes(@DrawableRes int backgroundImageRes) {
        this.backgroundImageRes = backgroundImageRes;
        llRoot.setBackgroundResource(backgroundImageRes);
    }

    public void setTitleTextColor(@ColorRes int titleTextColor) {
        this.titleTextColor = titleTextColor;
        tvTitle.setTextColor(getResources().getColor(titleTextColor));
    }

    @Override
    public void onClick(View v) {
        if (null == clickListener) {
            return;
        }

        int i = v.getId();
        if (i == R.id.flLeft) {
            if (tvLeft.getVisibility() == VISIBLE || ivLeft.getVisibility() == VISIBLE) {
                clickListener.onLeftClick();
            }
        }

        if (i == R.id.tvTitle) {
            clickListener.onTitleClick();
        }

        if (i == R.id.flRight) {
            if (tvRight.getVisibility() == VISIBLE || ivRight.getVisibility() == VISIBLE) {
                clickListener.onRightClick();
            }
        }

    }

    public interface OnZXTitleBarClickListener {

        void onLeftClick();

        void onTitleClick();

        void onRightClick();
    }
}
