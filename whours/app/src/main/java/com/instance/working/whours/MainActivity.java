package com.instance.working.whours;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.instance.working.whours.view.ProjectListActivity;


public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button)findViewById(R.id.btn_start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ProjectListActivity.class);
                startActivity(i);
            }
        });

        new Thread(new Runnable() {
            public void run() {
                int i = 3;
                while(i >= 0) {
                    i--;
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e)
                    {

                    }
                }
                Intent itent = new Intent(MainActivity.this,ProjectListActivity.class);
                startActivity(itent);
            }
        }).start();
    }
}
