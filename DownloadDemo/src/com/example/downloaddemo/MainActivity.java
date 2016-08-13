package com.example.downloaddemo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	File file;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Picture picture = new Picture(this);
		setContentView(R.layout.activity_main);
	}

	public void onClick(View view) {

		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				file = downLoadFile("http://7xtjjt.com2.z0.glb.qiniucdn.com/53017_00509.apk");
				openFile(file);
			}
		}).start();
		
	}
	
	public void onSmartInstall(View view) {
		if (TextUtils.isEmpty("/sdcard/update/updas.apk")) {
            Toast.makeText(this, "请选择安装包！", Toast.LENGTH_SHORT).show();
            return;
        }
        Uri uri = Uri.fromFile(new File("/sdcard/update/updas.apk"));
        Intent localIntent = new Intent(Intent.ACTION_VIEW);
        localIntent.setDataAndType(uri, "application/vnd.android.package-archive");
        startActivity(localIntent);
	}

	protected File downLoadFile(String httpUrl) {
		// TODO Auto-generated method stub
		final String fileName = "updas.apk";
		File tmpFile = new File("/sdcard/update");
		if (!tmpFile.exists()) {
			tmpFile.mkdir();
		}
		final File file = new File("/sdcard/update"+ fileName);
//		final File file = new File("\\"+ fileName);
		try {
			URL url = new URL(httpUrl);
			try {
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				InputStream is = conn.getInputStream();
				FileOutputStream fos = new FileOutputStream(file);
				byte[] buf = new byte[1024];
				conn.connect();
				double count = 0;
				if (conn.getResponseCode() >= 400) {
					Toast.makeText(MainActivity.this, "连接超时", Toast.LENGTH_SHORT).show();
				} else {
					while (count <= 100) {
						if (is != null) {
							int numRead = is.read(buf);
							if (numRead <= 0) {
								break;
							} else {
								fos.write(buf, 0, numRead);
							}

						} else {
							break;
						}

					}
				}

				conn.disconnect();
				fos.close();
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}

		return file;
	}
	
	// 打开APK程序代码
	private void openFile(File file) {
		// TODO Auto-generated method stub
		Log.e("OpenFile", file.getName());
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
		startActivity(intent);
	}

}
