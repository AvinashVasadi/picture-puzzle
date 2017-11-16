package com.example.s528734.milestone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LeaderBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
    }
    public void LeaderBoardOnBack(View v){
        Intent bcak = new Intent(this, MainActivity.class);
        startActivity(bcak);
    }

    //score for time mode
    public void timeScorel(View v)
    {
        TextView tve1= (TextView) findViewById(R.id.textView12);
        TextView tvm1= (TextView) findViewById(R.id.textView13);
        TextView tvh1= (TextView) findViewById(R.id.textView14);
        TextView tve2= (TextView) findViewById(R.id.textView15);
        TextView tvm2= (TextView) findViewById(R.id.textView18);
        TextView tvh2= (TextView) findViewById(R.id.textView19);
        tve1.setText("Bhavishya - moves(600)");
        tvm1.setText("sahu - moves(400)");
        tvh1.setText("avinash - moves(150)");
        tve2.setText("aswini - moves(550)");
        tvm2.setText("sandeep - moves(300)");
        tvh2.setText("haritha - moves(98)");
    }

    //score for normal mode

    public void normalScorel(View v)
    {
        TextView tve1= (TextView) findViewById(R.id.textView12);
        TextView tvm1= (TextView) findViewById(R.id.textView13);
        TextView tvh1= (TextView) findViewById(R.id.textView14);
        TextView tve2= (TextView) findViewById(R.id.textView15);
        TextView tvm2= (TextView) findViewById(R.id.textView18);
        TextView tvh2= (TextView) findViewById(R.id.textView19);
        tve1.setText("Bhavishya - moves(700)");
        tvm1.setText("sahu - moves(690)");
        tvh1.setText("avinash - moves(623)");
        tve2.setText("aswini - moves(550)");
        tvm2.setText("sandeep - moves(500)");
        tvh2.setText("haritha - moves(108)");
    }

}
