package com.example.s528734.milestone;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class PregameSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregame_settings);
    }

    public void OnBack(View v){
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }

    public void OnLetsGo(View v){
        Intent letsgo = new Intent(this, Game.class);
        startActivity(letsgo);
    }

    private static int RESULT_LOAD_IMAGE = 1;

    public byte[] byteArray;

    public void onBrowse(View v) {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMAGE);
    }

    Bitmap bmp = null;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            String imagename = picturePath.substring(picturePath.lastIndexOf('/')+1, picturePath.length());

            TextView tv = (TextView)findViewById(R.id.imageName);
            tv.setText(imagename);

            try {
                bmp = getBitmapFromUri(selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();

            Intent mIntent = new Intent(this, Game.class);
            mIntent.putExtra("bmp_img", b);

            Intent gameMedium = new Intent(this, GameMedium.class);
            gameMedium.putExtra("bmp_img",b);

            Intent gameHard = new Intent(this, GameHard.class);
            gameHard.putExtra("bmp_img",b);

            if(modeFlag == 0 || levelFlag == 0){
                Toast.makeText(getApplicationContext(), "Select the both MODE & LEVEL of the game", Toast.LENGTH_LONG).show();
            } else if(easy == 1){
                startActivity(mIntent);
            } else if(medium == 1){
                startActivity(gameMedium);
            } else if(hard == 1){
                startActivity(gameHard);
            }

        }
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    public int modeFlag = 0;

    public void modeOnRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        modeFlag = 0;
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.TimeMode:
                if (checked)
                    modeFlag = 1;
                    break;
            case R.id.NormalMode:
                if (checked)
                    modeFlag = 1;
                    break;
        }
    }

    public int levelFlag = 0;
    public int easy = 0;
    public int medium = 0;
    public int hard = 0;

    public void levelOnRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        levelFlag = 0;
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.Easy:
                if (checked)
                    levelFlag = 1;
                    easy = 1;
                break;
            case R.id.Medium:
                if (checked)
                    levelFlag = 1;
                    medium = 1;
                break;
            case R.id.Hard:
                if (checked)
                    levelFlag = 1;
                    hard = 1;
                break;
        }
    }
}
