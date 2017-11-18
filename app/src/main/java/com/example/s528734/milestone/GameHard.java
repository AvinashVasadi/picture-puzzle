package com.example.s528734.milestone;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;

public class GameHard extends AppCompatActivity {

    Bitmap[] img = new Bitmap[9];
    Bitmap[] refimgs = new Bitmap[9];
    int pos1 = 10;
    int pos2 = 10;
    int pos1ref = 0;
    int pos2ref = 1;
    int numberOfMoves = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_hard);

        Bundle extras = getIntent().getExtras();
        byte[] b = extras.getByteArray("bmp_img");

        Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);

        int xcordindex = (bmp.getWidth()-300)/2;
        int ycordindex = (bmp.getHeight()-300)/2;

        Bitmap bmOverlay = Bitmap.createBitmap(bmp, xcordindex,ycordindex, 300, 300);

        img[0] = Bitmap.createBitmap(bmOverlay, 0, 0, 100,  100);
        img[1] = Bitmap.createBitmap(bmOverlay, 100, 0, 100, 100);
        img[2] = Bitmap.createBitmap(bmOverlay, 200, 0, 100, 100);
        img[3] = Bitmap.createBitmap(bmOverlay, 0, 100, 100, 100);
        img[4] = Bitmap.createBitmap(bmOverlay, 100, 100, 100, 100);
        img[5] = Bitmap.createBitmap(bmOverlay, 200, 100, 100, 100);
        img[6] = Bitmap.createBitmap(bmOverlay, 0, 200, 100, 100);
        img[7] = Bitmap.createBitmap(bmOverlay, 100, 200, 100, 100);
        img[8] = Bitmap.createBitmap(bmOverlay, 200, 200, 100, 100);

        refimgs = img.clone();

        Collections.shuffle((Arrays.asList(img)));

        final GridView gridview = (GridView) findViewById(R.id.gridviewHard);
        gridview.setAdapter(new ImageAdapter(this, img));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                ImageView tile1 = (ImageView) gridview.getChildAt(position);
                ImageView capture1 = (ImageView) tile1;
                capture1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Accessed Tile : "+position, Toast.LENGTH_LONG).show();
                        if(pos1ref == 0){
                            Log.d("entered 1st click", "Click1");
                            pos1 = position;
                            pos1ref = 1;
                        }
                        if(pos2ref == 0){
                            Log.d("entered 2nd click", "Click2");
                            pos2 = position;
                        }
                        if(pos1ref == 1){
                            pos2ref = 0;
                        }
                        if(pos1 != 10 && pos2 != 10){
                            Log.d("entered 2nd click", "swapped");
                            swap(pos1, pos2);
                        }
                    }
                });
            }
        });
    }

    public void swap(int index1, int index2){
        Bitmap temp = img[index1];
        img[index1] = img[index2];
        img[index2] = temp;
        final GridView gridview = (GridView) findViewById(R.id.gridviewHard);
        gridview.setAdapter(new ImageAdapter(this, img));
        isCompleted();
        pos1ref = 0;
        pos2ref = 1;
        pos1 = 10;
        pos2 = 10;
        numberOfMoves++;
    }

    public void isCompleted(){
        int count = 0;
        for(int i=0; i<9; i++){
            if(img[i] == refimgs[i]){
                count++;
            }
        }

        if(count == 9){
            Intent scorepage = new Intent(this, score.class);
            scorepage.putExtra("movescount", numberOfMoves);
            startActivity(scorepage);
        }

    }

    public void OnHomePage(View v){
        startActivity(new Intent(this,MainActivity.class));
    }
    public void undo(View v)
    {
        Toast.makeText(getApplicationContext(), "last action is undone", Toast.LENGTH_LONG).show();
    }
    public void rotate(View v)
    {
        Toast.makeText(getApplicationContext(), "the selected block is rotated", Toast.LENGTH_LONG).show();
    }

}
