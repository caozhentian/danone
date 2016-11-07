package com.danone.comfit.common.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.danone.comfit.net.ProgressListener;
import com.danone.comfit.net.UploadFileRequestBody;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class NetUpLoadUtil {

	public static Map<String, RequestBody> fileToRequestBodyAndProGress(List<File> files) {
		Map<String, RequestBody> requestBodys = new HashMap<String, RequestBody>();
		if (files != null) {
			for (File file : files) {
				UploadFileRequestBody requestBody = new UploadFileRequestBody(file, new ProgressListener() {
					@Override
					public void onProgress(long hasWrittenLen, long totalLen, boolean hasFinish) {
						// TODO Auto-generated method stub
					}
				});
				requestBodys.put("file\"; filename=\"" + file.getName() + "", requestBody);
			}

		}

		return requestBodys;
	}

	public static Map<String, RequestBody> fileToRequestBody(List<File> files) {
		Map<String, RequestBody> requestBodys = new HashMap<String, RequestBody>();
		if (files != null) {
			for (File file : files) {
				RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
				requestBodys.put("file\"; filename=\"" + file.getName() + "", requestBody);
			}

		}

		return requestBodys;
	}

	public static MultipartBody filesToMultipartBody(List<File> files) {
		MultipartBody.Builder builder = new MultipartBody.Builder();

		for (File file : files) {
			RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
			builder.addFormDataPart("file[]", file.getName(), requestBody);
		}
		builder.setType(MultipartBody.FORM);
		MultipartBody multipartBody = builder.build();
		return multipartBody;
	}

	public static MultipartBody.Part fileToMultPartAndListener(File file, ProgressListener progressListener) {
		UploadFileRequestBody requestFile = new UploadFileRequestBody(file, progressListener);
		MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
		return part;
	}

	public static MultipartBody.Part fileToMultPart(File file) {
		RequestBody requestBody = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
		MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
		return part;
	}
}
