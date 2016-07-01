package com.skystudio.gifdemo;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.addButton)
    AppCompatButton addButton;
    @BindView(R.id.fab)
    FloatingActionButton fab;


    PopupWindow popupWindow=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 显示弹出框
     * */
    private void showPopupWindow() {
        View parentView = LayoutInflater.from(this).inflate(R.layout.content_main, null);
        View view = getLayoutInflater().from(this).inflate(R.layout.popurp_main, null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        //popupWindow.showAtLocation(addButton, Gravity.CENTER_VERTICAL, 0, 0);
        //设置可不可以点击默认可点击
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(),(Bitmap) null));
        popupWindow.showAsDropDown(addButton);
    }
    /**
     * 取消显示弹出框
     * */
    private void cancelPopupWindow(){
        if (popupWindow==null){}
        else {
            if (popupWindow.isShowing()){
                popupWindow.dismiss();
            }
        }
    }

    @OnClick({R.id.toolbar, R.id.addButton, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar:
                cancelPopupWindow();
                break;
            case R.id.addButton:
                Log.e("xv","in click");
                showPopupWindow();
                break;
            case R.id.fab:
                cancelPopupWindow();
                break;
        }
    }

    /**
     * 处理触碰事件
     * */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        cancelPopupWindow();
        return super.onTouchEvent(event);
    }
}
