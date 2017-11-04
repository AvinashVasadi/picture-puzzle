package com.example.s528734.milestone;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle extras = getIntent().getExtras();
        byte[] b = extras.getByteArray("bmp_img");

      Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);

        int xcordinateindex = (bmp.getWidth()-300)/2;
        int ycordinateindex = (bmp.getHeight()-300)/2;

        Bitmap bmOverlay = Bitmap.createBitmap(bmp, xcordinateindex,ycordinateindex, 300, 300);

        Log.d(String.valueOf("width value : " + bmOverlay.getWidth() + "height value : " + bmOverlay.getHeight()), "Width before creating bmp");
//        Bitmap bmOverlay = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
//
//        Paint paint = new Paint();
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
//
//
//        Canvas canvas = new Canvas(bmOverlay);
//        canvas.drawBitmap(bmp, 0, 0, null);
//        //canvas.drawRect(0, 0, 100, 100, paint);
//        Log.d("Width value : "+String.valueOf(bmOverlay.getWidth()), "width");

        Bitmap[] imgs = new Bitmap[9];
//        imgs[0] = Bitmap.createBitmap(bmOverlay, 0, 0, bmOverlay.getWidth()/2 , bmOverlay.getHeight()/2);
//        imgs[1] = Bitmap.createBitmap(bmOverlay, bmOverlay.getWidth()/2, 0, bmOverlay.getWidth()/2, bmOverlay.getHeight()/2);
//        imgs[2] = Bitmap.createBitmap(bmOverlay,0, bmOverlay.getHeight()/2, bmOverlay.getWidth()/2,bmOverlay.getHeight()/2);
//        imgs[3] = Bitmap.createBitmap(bmOverlay, bmOverlay.getWidth()/2, bmOverlay.getHeight()/2, bmOverlay.getWidth()/2, bmOverlay.getHeight()/2);
        imgs[0] = Bitmap.createBitmap(bmOverlay, 0, 0, 100,  100);
        imgs[1] = Bitmap.createBitmap(bmOverlay, 100, 0, 100, 100);
        imgs[2] = Bitmap.createBitmap(bmOverlay, 200, 0, 100, 100);
        imgs[3] = Bitmap.createBitmap(bmOverlay, 0, 100, 100, 100);
        imgs[4] = Bitmap.createBitmap(bmOverlay, 100, 100, 100, 100);
        imgs[5] = Bitmap.createBitmap(bmOverlay, 200, 100, 100, 100);
        imgs[6] = Bitmap.createBitmap(bmOverlay, 0, 200, 100, 100);
        imgs[7] = Bitmap.createBitmap(bmOverlay, 100, 200, 100, 100);
        imgs[8] = Bitmap.createBitmap(bmOverlay, 200, 200, 100, 100);


        Collections.shuffle((Arrays.asList(imgs)));

        final GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this, imgs));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView tile1 = (ImageView) gridview.getChildAt(0);
                ImageView capture1 = (ImageView) tile1;
                capture1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Accessed Tile-1", Toast.LENGTH_LONG).show();
                    }
                });

                ImageView tile2 = (ImageView) gridview.getChildAt(1);
                ImageView capture2 = (ImageView) tile2;
                capture2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Accessed Tile-2", Toast.LENGTH_LONG).show();
                    }
                });

                ImageView tile3 = (ImageView) gridview.getChildAt(2);
                ImageView capture3 = (ImageView) tile3;
                capture3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Accessed Tile-3", Toast.LENGTH_LONG).show();
                    }
                });

                ImageView tile4 = (ImageView) gridview.getChildAt(3);
                ImageView capture4 = (ImageView) tile4;
                capture4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Accessed Tile-4", Toast.LENGTH_LONG).show();
                    }
                });

                ImageView tile5 = (ImageView) gridview.getChildAt(4);
                ImageView capture5 = (ImageView) tile5;
                capture5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Accessed Tile-5", Toast.LENGTH_LONG).show();
                    }
                });

                ImageView tile6 = (ImageView) gridview.getChildAt(5);
                ImageView capture6 = (ImageView) tile6;
                capture6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Accessed Tile-6", Toast.LENGTH_LONG).show();
                    }
                });

                ImageView tile7 = (ImageView) gridview.getChildAt(6);
                ImageView capture7 = (ImageView) tile7;
                capture7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Accessed Tile-7", Toast.LENGTH_LONG).show();
                    }
                });

                ImageView tile8 = (ImageView) gridview.getChildAt(7);
                ImageView capture8 = (ImageView) tile8;
                capture8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Accessed Tile-8", Toast.LENGTH_LONG).show();
                    }
                });

                ImageView tile9 = (ImageView) gridview.getChildAt(8);
                ImageView capture9 = (ImageView) tile9;
                capture9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Accessed Tile-9", Toast.LENGTH_LONG).show();
                    }
                });


            }
        });
    }
    public void click(View v){
        Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();
    }

    public void OnHomePage(View v){
        Intent homepage = new Intent(this, MainActivity.class);
        startActivity(homepage);
    }
    public void undo(View v)
    {
        Toast.makeText(getApplicationContext(), "last action is undone", Toast.LENGTH_LONG).show();
    }
    public void rotate(View v)
    {
        Toast.makeText(getApplicationContext(), "the selected block is rotated", Toast.LENGTH_LONG).show();
    }

    ArrayList<Bitmap> chunkedImages;

    private Bitmap splitImage(Bitmap image, int chunkNumbers) {
        Bitmap bitmap = image;
        //For the number of rows and columns of the grid to be displayed
        int rows,cols;

        //For height and width of the small image chunks
        int chunkHeight,chunkWidth;

        //To store all the small image chunks in bitmap format in this list
        chunkedImages = new ArrayList<Bitmap>(chunkNumbers);

        //Getting the scaled bitmap of the source image

    /*ByteArrayOutputStream stream = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);*/
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);

        rows = cols = (int) Math.sqrt(chunkNumbers);
        chunkHeight = bitmap.getHeight()/rows;
        chunkWidth = bitmap.getWidth()/cols;
    /*chunkHeight = 300/rows;
    chunkWidth = 300/cols;*/

        //xCoord and yCoord are the pixel positions of the image chunks
        int yCoord = 0;
        for(int x=0; x<rows; x++){
            int xCoord = 0;
            for(int y=0; y<cols; y++){
                chunkedImages.add(Bitmap.createBitmap(scaledBitmap, xCoord, yCoord, chunkWidth, chunkHeight));
                xCoord += chunkWidth;
            }
            yCoord += chunkHeight;
        }

        // Function of merge the chunks images(after image divided in pieces then i can call this function to combine and merge the image as one)
        Bitmap x = mergeImage(chunkedImages);

        return x;
    }

    Bitmap mergeImage(ArrayList<Bitmap> imageChunks) {

        Collections.shuffle(imageChunks);

        //Get the width and height of the smaller chunks
        int chunkWidth = imageChunks.get(0).getWidth();
        int chunkHeight = imageChunks.get(0).getHeight();

        //create a bitmap of a size which can hold the complete image after merging
        Bitmap bitmap = Bitmap.createBitmap(chunkWidth * 5, chunkHeight * 5, Bitmap.Config.ARGB_4444);

        //create a canvas for drawing all those small images
        Canvas canvas = new Canvas(bitmap);
        int count = 0;
        for (int rows = 0; rows < 5; rows++) {
            for (int cols = 0; cols < 5; cols++) {
                canvas.drawBitmap(imageChunks.get(count), chunkWidth * cols, chunkHeight * rows, null);
                count++;
            }
        }
        return bitmap;
    }

    }
