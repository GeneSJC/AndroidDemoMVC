package com.inventorymgr.activity.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.Toast;

import com.inventorymgr.activity.R;

public class DrawerDemoActivity extends Activity implements OnClickListener {
 
    Button slideButton,b1, b2,b3,b4;
    SlidingDrawer slidingDrawer;
 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        setContentView(R.layout.drawer_layout2);
        slideButton = (Button) findViewById(R.id.slideButton);
        slidingDrawer = (SlidingDrawer) findViewById(R.id.SlidingDrawer);
        b1 = (Button) findViewById(R.id.Button01);
        b2 = (Button) findViewById(R.id.Button02);
        b3 = (Button) findViewById(R.id.Button03);
        b4 = (Button) findViewById(R.id.Button04);
 
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
 
        slidingDrawer.setOnDrawerOpenListener(new OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened() {
                slideButton.setBackgroundResource(R.drawable.addbutton);
            }
        });
 
        slidingDrawer.setOnDrawerCloseListener(new OnDrawerCloseListener() {
            @Override
            public void onDrawerClosed() {
                slideButton.setBackgroundResource(R.drawable.ic_drawer);
            }
        });
    }
 
    @Override
    public void onClick(View v) {
        Button b = (Button)v;
        Toast.makeText(DrawerDemoActivity.this, b.getText() + " Clicked", Toast.LENGTH_SHORT).show();
    }
}