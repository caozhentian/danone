package com.threeti.danone.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageUtils {

	public static boolean saveImage(int resId, Context context, String fileName) throws IOException {

		Bitmap mBitmap = BitmapFactory.decodeResource(context.getResources(), resId);
		File fileDir = new File("/sdcard/temp");
		if(!fileDir.exists()){
			fileDir.mkdir();
		}
		
		File file = new File("/sdcard/temp/" + fileName + ".jpg");
		if (!file.exists()) {
			file.createNewFile();
			return true;
		} else {
			FileOutputStream fOut = new FileOutputStream(file);
			return mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
		}

	}

}
