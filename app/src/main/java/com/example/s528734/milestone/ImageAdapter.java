package com.example.s528734.milestone;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.s528734.milestone.R;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private Bitmap[] mThumbIds;
    public ImageAdapter(Context c,Bitmap[] m) {
        mContext = c;
        mThumbIds = m;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(315, 315));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }


        imageView.setImageBitmap(mThumbIds[position]);

        return imageView;
    }
    public Bitmap[] AddItems(Bitmap[] a){
        final Bitmap[] aThumbIds;
        final int i;
        aThumbIds = a;
        Bitmap[] a2 = new Bitmap[mThumbIds.length + aThumbIds.length];
        System.arraycopy(mThumbIds, 0, a2, 0, mThumbIds.length);
        System.arraycopy(aThumbIds, 0, a2, mThumbIds.length, aThumbIds.length);
        return mThumbIds;
    }
}
