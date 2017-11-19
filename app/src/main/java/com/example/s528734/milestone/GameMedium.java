package com.example.s528734.milestone;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;

public class GameMedium extends AppCompatActivity {

    Bitmap[] imgs = new Bitmap[16];
    Bitmap[] errimg = new Bitmap[16];
    Bitmap[] refimgs = new Bitmap[16];
    int position1 = 20;
    int position2 = 20;
    int position1ref = 0;
    int position2ref = 1;
    int numberOfMoves = 0;
    long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_medium);

        Bundle extras = getIntent().getExtras();
        byte[] b = extras.getByteArray("bmp_img");

        Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);

        int length = bmp.getWidth();
        int width = bmp.getHeight();

        if(length>300 && width>300){

            int xcordinateindex = (bmp.getWidth()-300)/2;
            int ycordinateindex = (bmp.getHeight()-300)/2;

            Bitmap bmOverlay = Bitmap.createBitmap(bmp, xcordinateindex,ycordinateindex, 300, 300);

            imgs[0] = Bitmap.createBitmap(bmOverlay, 0, 0, 75,  75);
            imgs[1] = Bitmap.createBitmap(bmOverlay, 75, 0, 75, 75);
            imgs[2] = Bitmap.createBitmap(bmOverlay, 150, 0, 75, 75);
            imgs[3] = Bitmap.createBitmap(bmOverlay, 225, 0, 75, 75);
            imgs[4] = Bitmap.createBitmap(bmOverlay, 0, 75, 75, 75);
            imgs[5] = Bitmap.createBitmap(bmOverlay, 75, 75, 75, 75);
            imgs[6] = Bitmap.createBitmap(bmOverlay, 150, 75, 75, 75);
            imgs[7] = Bitmap.createBitmap(bmOverlay, 225, 75, 75, 75);
            imgs[8] = Bitmap.createBitmap(bmOverlay, 0, 150, 75, 75);
            imgs[9] = Bitmap.createBitmap(bmOverlay, 75, 150, 75, 75);
            imgs[10] = Bitmap.createBitmap(bmOverlay, 150, 150, 75, 75);
            imgs[11] = Bitmap.createBitmap(bmOverlay, 225, 150, 75, 75);
            imgs[12] = Bitmap.createBitmap(bmOverlay, 0, 225, 75, 75);
            imgs[13] = Bitmap.createBitmap(bmOverlay, 75, 225, 75, 75);
            imgs[14] = Bitmap.createBitmap(bmOverlay, 150, 225, 75, 75);
            imgs[15] = Bitmap.createBitmap(bmOverlay, 225, 225, 75, 75);

            refimgs = imgs.clone();

            Collections.shuffle((Arrays.asList(imgs)));

            startTime = System.currentTimeMillis();

            final GridView gridview = (GridView) findViewById(R.id.gridviewMedium);
            gridview.setAdapter(new ImageAdapter(this, imgs));

            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                    ImageView tile1 = (ImageView) gridview.getChildAt(position);
                    ImageView capture1 = (ImageView) tile1;
                    capture1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getApplicationContext(), "Accessed Tile : "+position, Toast.LENGTH_LONG).show();
                            if(position1ref == 0){
                                Log.d("entered 1st click", "Click1");
                                position1 = position;
                                position1ref = 1;
                            }
                            if(position2ref == 0){
                                Log.d("entered 2nd click", "Click2");
                                position2 = position;
                            }
                            if(position1ref == 1){
                                position2ref = 0;
                            }
                            if(position1 != 20 && position2 != 20){
                                Log.d("entered 2nd click", "swapped");
                                swap(position1, position2);
                            }
                        }
                    });
                }
            });
        } else {
            Resources resources = getResources();
            Drawable d = resources.getDrawable(R.drawable.error);
            Bitmap errorimgy = ((BitmapDrawable) d).getBitmap();

            int xcordinateindexy = (errorimgy.getWidth()-300)/2;
            int ycordinateindexy = (errorimgy.getHeight()-300)/2;

            Bitmap errorimg = Bitmap.createBitmap(errorimgy, xcordinateindexy,ycordinateindexy, 300, 300);
            errimg[0] = Bitmap.createBitmap(errorimg, 0, 0, 75,  75);
            errimg[1] = Bitmap.createBitmap(errorimg, 75, 0, 75, 75);
            errimg[2] = Bitmap.createBitmap(errorimg, 150, 0, 75, 75);
            errimg[3] = Bitmap.createBitmap(errorimg, 225, 0, 75, 75);
            errimg[4] = Bitmap.createBitmap(errorimg, 0, 75, 75, 75);
            errimg[5] = Bitmap.createBitmap(errorimg, 75, 75, 75, 75);
            errimg[6] = Bitmap.createBitmap(errorimg, 150, 75, 75, 75);
            errimg[7] = Bitmap.createBitmap(errorimg, 225, 75, 75, 75);
            errimg[8] = Bitmap.createBitmap(errorimg, 0, 150, 75, 75);
            errimg[9] = Bitmap.createBitmap(errorimg, 75, 150, 75, 75);
            errimg[10] = Bitmap.createBitmap(errorimg, 150, 150, 75, 75);
            errimg[11] = Bitmap.createBitmap(errorimg, 225, 150, 75, 75);
            errimg[12] = Bitmap.createBitmap(errorimg, 0, 225, 75, 75);
            errimg[13] = Bitmap.createBitmap(errorimg, 75, 225, 75, 75);
            errimg[14] = Bitmap.createBitmap(errorimg, 150, 225, 75, 75);
            errimg[15] = Bitmap.createBitmap(errorimg, 225, 225, 75, 75);
            final GridView gridview = (GridView) findViewById(R.id.gridviewMedium);
            gridview.setAdapter(new ImageAdapter(this, errimg));
            Toast.makeText(getApplicationContext(), "IMAGE SIZE ERROR, SIZE SHOULD BE GREATER THAN 300X300", Toast.LENGTH_LONG).show();

        }

    }

    public void swap(int index1, int index2){
        Bitmap temp = imgs[index1];
        imgs[index1] = imgs[index2];
        imgs[index2] = temp;
        final GridView gridview = (GridView) findViewById(R.id.gridviewMedium);
        gridview.setAdapter(new ImageAdapter(this, imgs));
        isCompleted();
        position1ref = 0;
        position2ref = 1;
        position1 = 20;
        position2 = 20;
        numberOfMoves++;
    }

    public void isCompleted(){
        int count = 0;
        for(int i=0; i<16; i++){
            if(imgs[i] == refimgs[i]){
                count++;
            }
        }

        int mode = getIntent().getIntExtra("timemode", 3);

        if(count == 16) {
            if (mode == 1) {
                Intent scorepage = new Intent(this, score.class);
                scorepage.putExtra("scoremode", mode);
                scorepage.putExtra("mediummovescount", numberOfMoves);
                startActivity(scorepage);
            }
            if (mode == 2) {
                long difference = System.currentTimeMillis() - startTime;
                //Log.d("time taken", String.valueOf(difference));
                //System.out.println(difference);
                Intent scorepage = new Intent(this, score.class);
                scorepage.putExtra("scoremode", mode);
                scorepage.putExtra("mediumtimecount", difference);
                startActivity(scorepage);
            }
            if (mode == 3) {

            }
        }
    }
    public void rotate(View v)
    {
        Toast.makeText(getApplicationContext(), "Chosen block is rotated", Toast.LENGTH_LONG).show();
    }

    public void undo(View v)
    {
        Toast.makeText(getApplicationContext(), "Previous action is reversed", Toast.LENGTH_LONG).show();
    }

    public void OnHomePage(View v){
       startActivity(new Intent(this,MainActivity.class));
    }



}
