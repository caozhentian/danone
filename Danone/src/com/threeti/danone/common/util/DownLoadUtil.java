package com.threeti.danone.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.util.Log;
import okhttp3.ResponseBody;

public class DownLoadUtil {

	private static final String TAG = DownLoadUtil.class.getSimpleName();

	public static boolean writeResponseBodyToDisk(ResponseBody body, Context context) {
		try {
			// todo change the file location/name according to your needs
			File futureStudioIconFile = new File(context.getExternalFilesDir(null) + File.separator + "icon.png");

			InputStream inputStream = null;
			OutputStream outputStream = null;

			try {
				byte[] fileReader = new byte[4096];

				long fileSize = body.contentLength();
				long fileSizeDownloaded = 0;

				inputStream = body.byteStream();
				outputStream = new FileOutputStream(futureStudioIconFile);

				while (true) {
					int read = inputStream.read(fileReader);

					if (read == -1) {
						break;
					}

					outputStream.write(fileReader, 0, read);

					fileSizeDownloaded += read;

					Log.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
				}

				outputStream.flush();

				return true;
			} catch (IOException e) {
				return false;
			} finally {
				if (inputStream != null) {
					inputStream.close();
				}

				if (outputStream != null) {
					outputStream.close();
				}
			}
		} catch (IOException e) {
			return false;
		}
	}
}
