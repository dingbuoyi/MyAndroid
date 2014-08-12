package org.dean.myandroid;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dinghuiyuan01 on 2014/8/8.
 */
public class MyTextViewActivity extends BaseActivity {
    private TextView mTextView1, mTextView2;
    private String phoneNumber = "13761827068";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_text);
        mTextView1 = (TextView) findViewById(R.id.verification_code_phone_number);
        String phoneVerificationCode = String.format(getString(R.string.please_input_phone_verification_code),
                phoneNumber);
        SpannableStringBuilder sb = createPhoneNumberBoldSpannable(phoneVerificationCode);// 把中间的电话号码用黑体显示
        mTextView1.setText(sb);
        mTextView2 = (TextView) findViewById(R.id.get_verification_text);
        setGetVerificationCodeText();// 重新获取验证码可以点击
    }

    private SpannableStringBuilder createPhoneNumberBoldSpannable(String phoneVerificationCode) {
        SpannableStringBuilder sb = new SpannableStringBuilder(phoneVerificationCode);
        final StyleSpan boldStyleSpan = new StyleSpan(android.graphics.Typeface.BOLD);// 黑体
        // final StyleSpan iss = new
        // StyleSpan(android.graphics.Typeface.ITALIC);//斜体
        int boldStart = phoneVerificationCode.indexOf("+86");
        int boldEnd = phoneVerificationCode.indexOf("收到的");
        sb.setSpan(boldStyleSpan, boldStart, boldEnd, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return sb;
    }

    private void setGetVerificationCodeText() {
        mTextView2.setMovementMethod(LinkMovementMethod.getInstance());// 设置TextView可点击
        CharSequence text = mTextView2.getText();
        if (text instanceof Spannable) {
            int end = text.length();
            Spannable sp = (Spannable) mTextView2.getText();
            URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);
            SpannableStringBuilder style = new SpannableStringBuilder(text);
            style.clearSpans(); // should clear old spans
            for (URLSpan url : urls) {
                MyURLSpan myURLSpan = new MyURLSpan(url.getURL());
                style.setSpan(myURLSpan, sp.getSpanStart(url), sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            mTextView2.setText(style);
            disableClickHightLight(); // 去电点击的高亮效果
        }
    }

    private void disableClickHightLight() {
        mTextView2.setFocusable(false);
        mTextView2.setClickable(false);
        mTextView2.setLongClickable(false);
    }

    private class MyURLSpan extends ClickableSpan {
        private String mUrl;

        MyURLSpan(String url) {
            mUrl = url;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(ds.linkColor);
            ds.setUnderlineText(false);
            ds.bgColor = Color.TRANSPARENT;
        }

        @Override
        public void onClick(View widget) {
            Toast.makeText(MyTextViewActivity.this, "选中了", Toast.LENGTH_SHORT).show();
        }
    }
}
