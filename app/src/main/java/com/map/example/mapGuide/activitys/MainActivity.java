package com.map.example.mapGuide.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.map.example.R;
import com.map.example.mapGuide.widgets.MapDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapDialog mapDialog = new MapDialog(MainActivity.this, R.style.shareDialog, getString(R.string.addstr));
                Window w = mapDialog.getWindow();
                WindowManager.LayoutParams lp = w.getAttributes();
                lp.x = 0;
                final int cMakeBottom = -1000;
                lp.y = cMakeBottom;
                lp.gravity = Gravity.BOTTOM;
                mapDialog.onWindowAttributesChanged(lp);
                mapDialog.setCanceledOnTouchOutside(true);
                mapDialog.show();
            }
        });
    }
}
