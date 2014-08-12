package org.dean.myandroid;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ScrollView;


/**
 * Created by dinghuiyuan01 on 2014/8/8.
 */
public class MyEditTextActivity extends BaseActivity implements View.OnClickListener {
    private EditText mEdit1, mEdit2;
    private ScrollView mScrollView;
    private View editTextHackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_edittext);
        mScrollView = (ScrollView) findViewById(R.id.edit_scroll_view);
        editTextHackLayout = findViewById(R.id.edit_text_hack_layout);
        mEdit1 = (EditText) findViewById(R.id.edit1);
        mEdit2 = (EditText) findViewById(R.id.edit2);
        mEdit2.setOnClickListener(this);// 用户如果点击了返回按钮收起键盘然后再点输入框，因为焦点没改变，只能靠这个事件
        mEdit2.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    scrollToViewBottom();
                }
            }
        });
    }

    // 点击非屏幕其他地方，软键盘消失并且edittext失去焦点，这个事件要起作用必须和自定义的scrollview配合，让scrollview不消费点击事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        hideSoftInputAndMakeEditTextLoseFocus();
        return super.onTouchEvent(event);
    }

    private void hideSoftInputAndMakeEditTextLoseFocus() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEdit2.getWindowToken(), 0);
        makeEditTextLoseFocus();
    }

    private void makeEditTextLoseFocus() {
        editTextHackLayout.setFocusable(true);
        editTextHackLayout.setFocusableInTouchMode(true);
        editTextHackLayout.requestFocus();
    }

    @Override
    public void onClick(View v) {
        scrollToViewBottom();
    }

    private void scrollToViewBottom() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // 将ScrollView滚动到底
                mScrollView.fullScroll(View.FOCUS_DOWN);
            }
        }, 100);
    }
}
