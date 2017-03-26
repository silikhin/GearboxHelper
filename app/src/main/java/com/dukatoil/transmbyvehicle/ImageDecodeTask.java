package com.dukatoil.transmbyvehicle;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.AsyncTask;

/**
 * Created by B_Silikhin on 024 24.03.17.
 */

public class ImageDecodeTask extends AsyncTask <Integer, Void, Bitmap> {
    Context context;
    Resources res;
    Bitmap decodedImg;

    public ImageDecodeTask(Context context) {
        this.context = context;
        res = context.getResources();
    }

    @Override
    protected Bitmap doInBackground(Integer... integers) {
        decodedImg = ImageDecoding.decodeSampledBitmapFromResource(res, integers[0]);
        return decodedImg;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
    }
}
