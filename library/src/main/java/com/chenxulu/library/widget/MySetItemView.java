package com.chenxulu.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by xulu on 16/6/14.
 */
public class MySetItemView extends FrameLayout {
    private View mLayout;

    private ImageView leftImage;
    private TextView leftTxt;
    private ImageView rightImage;
    private TextView rightTxt;
    private CheckBox checkBox;

    public MySetItemView(Context context) {
        this(context, null);
    }

    public MySetItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySetItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MySetItemView);
        String leftStr = ta.getString(R.styleable.MySetItemView_left_txt);
        String rightStr = ta.getString(R.styleable.MySetItemView_right_txt);
        int leftResId = ta.getResourceId(R.styleable.MySetItemView_left_image, 0);
        boolean rightImageVisible = ta.getBoolean(R.styleable.MySetItemView_right_image_visible, true);
        boolean checkBoxVisible = ta.getBoolean(R.styleable.MySetItemView_checkbox_visible, false);
        int itemType = ta.getInt(R.styleable.MySetItemView_item_type, ItemType.SINGLE.type);
        ta.recycle();

        mLayout = LayoutInflater.from(context).inflate(R.layout.my_set_item_view_layout, null);
        addView(mLayout);
        leftImage = (ImageView) mLayout.findViewById(R.id.left_image);
        leftTxt = (TextView) mLayout.findViewById(R.id.left_txt);
        rightImage = (ImageView) mLayout.findViewById(R.id.right_image);
        rightTxt = (TextView) mLayout.findViewById(R.id.right_txt);
        checkBox = (CheckBox) mLayout.findViewById(R.id.checkbox);

        setLeftImage(leftResId);
        setLeftTxt(leftStr);
        setRightTxt(rightStr);
        rightImage.setVisibility(rightImageVisible ? VISIBLE : GONE);
        checkBox.setVisibility(checkBoxVisible ? VISIBLE : GONE);

        if (itemType == ItemType.TOP.type) {
            setBackgroundResource(R.drawable.my_set_item_top_selector);
        } else if (itemType == ItemType.MIDDLE.type) {
            setBackgroundResource(R.drawable.my_set_item_middle_selector);
        } else if (itemType == ItemType.BOTTOM.type) {
            setBackgroundResource(R.drawable.my_set_item_bottom_selector);
        } else if (itemType == ItemType.SINGLE.type) {
            setBackgroundResource(R.drawable.my_set_item_single_selector);
        }

        setClickable(true);
    }

    /**
     * 设置左边图标
     *
     * @param leftResId
     */
    public void setLeftImage(int leftResId) {
        if (leftResId != 0) {
            leftImage.setVisibility(VISIBLE);
            leftImage.setImageResource(leftResId);
        } else {
            leftImage.setVisibility(GONE);
        }
    }

    /**
     * 设置左边文字
     *
     * @param leftStr
     */
    public void setLeftTxt(CharSequence leftStr) {
        if (!TextUtils.isEmpty(leftStr)) {
            leftTxt.setVisibility(VISIBLE);
            leftTxt.setText(leftStr);
        } else {
            leftTxt.setText("");
            leftTxt.setVisibility(GONE);
        }
    }

    /**
     * 设置右边文字
     *
     * @param rightStr
     */
    public void setRightTxt(CharSequence rightStr) {
        if (!TextUtils.isEmpty(rightStr)) {
            rightTxt.setText(rightStr);
            rightTxt.setVisibility(VISIBLE);
        } else {
            rightTxt.setText("");
            rightTxt.setVisibility(GONE);
        }
    }

    public TextView getLeftTextView() {
        return leftTxt;
    }

    public ImageView getLeftImage() {
        return leftImage;
    }

    public TextView getRightTextView() {
        return rightTxt;
    }

    public ImageView getRightImage() {
        return rightImage;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public enum ItemType {
        SINGLE(0), TOP(1), MIDDLE(2), BOTTOM(3);

        int type;

        ItemType(int type) {
            this.type = type;
        }

    }

}
