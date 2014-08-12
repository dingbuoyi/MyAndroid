package org.dean.myandroid;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
    private static final String TEXT_VIEW_TITLE = "TextView部分文字可点击";
    private static final String EDIT_TEXT_TITLE = "软键盘将整个界面推上去";
    private String[] mTitles = {TEXT_VIEW_TITLE, EDIT_TEXT_TITLE};
    private ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mListView = getListView();
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mTitles));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title = mTitles[position];
                Intent intent = null;
                if (TEXT_VIEW_TITLE.equals(title)) {
                    intent = new Intent(MainActivity.this, MyTextViewActivity.class);
                }
                if (EDIT_TEXT_TITLE.equals(title)) {
                    intent = new Intent(MainActivity.this, MyEditTextActivity.class);
                }
                startActivity(intent);
            }
        });
        super.onCreate(savedInstanceState);
    }
}
