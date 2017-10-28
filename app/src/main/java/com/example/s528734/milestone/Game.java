package com.example.s528734.milestone;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle extras = getIntent().getExtras();
        byte[] b = extras.getByteArray("bmp_img");
//
      Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);

        Bitmap bmOverlay = Bitmap.createBitmap(315, 315, Bitmap.Config.ARGB_8888);

        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp, 0, 0, null);
        //canvas.drawRect(0, 0, 100, 100, paint);

//        Bitmap[] imgs = new Bitmap[9];
//        imgs[0] = Bitmap.createBitmap(bmOverlay, 0, 0, 80 , 80);
//        imgs[1] = Bitmap.createBitmap(bmOverlay, 80, 0, 80, 80);
//        imgs[2] = Bitmap.createBitmap(bmOverlay,160, 0, 80,80);
//        imgs[3] = Bitmap.createBitmap(bmOverlay, 0, 80, 80, 80);
//        imgs[4] = Bitmap.createBitmap(bmOverlay, 80, 80, 80,80);
//        imgs[5] = Bitmap.createBitmap(bmOverlay, 160, 80,80,80);
//        imgs[6] = Bitmap.createBitmap(bmOverlay, 0, 160, 80,80);
//        imgs[7] = Bitmap.createBitmap(bmOverlay, 80, 160,80,80);
//        imgs[8] = Bitmap.createBitmap(bmOverlay, 160,160,80,80);
//

        ImageView iv = (ImageView)findViewById(R.id.imageView);
        Bitmap result = splitImage(bmOverlay, 50);
        iv.setImageBitmap(result);

        //Bitmap mBitmap = (Bitmap) getIntent().getParcelableExtra("bmp_img");


//        Bundle extras = getIntent().getExtras();
//        byte[] byteArray = extras.getByteArray("picture");
//
//        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//        ImageView image = (ImageView) findViewById(R.id.imageView);
//
//        image.setImageBitmap(bmp);
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
