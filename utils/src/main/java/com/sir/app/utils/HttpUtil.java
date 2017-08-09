package com.sir.app.utils;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import cz.msebera.android.httpclient.Header;

/**
 * 网络请求工具类
 * Created by zhuyinan on 2016/12/22.
 * Contact by 445181052@qq.com
 */

public class HttpUtil {

    private HttpUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    //封装的方法建议都加上Context参数，以在Activity pause或stop时取消掉没用的请求。
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(context, url, params, responseHandler);
    }

    public static void get(Context context, String url, AsyncHttpResponseHandler responseHandler) {
        client.get(context, url, responseHandler);
    }


    public static void get(Context context, String url, BinaryHttpResponseHandler handler) {
        client.get(context, url, handler);
    }


    public static void post(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(context, url, params, responseHandler);
    }

    public static void download(final String downloadUrl, final String savePath, final String fileName, final OnDownloadListener listener) {
        // 指定文件类型
        //String[] allowedContentTypes = new String[] { "image/png", "image/jpeg" };
        String[] allowedContentTypes = new String[]{".*"};
        client.get(downloadUrl, new BinaryHttpResponseHandler(allowedContentTypes) {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] binaryData) {
                // TODO Auto-generated method stub
                // 下载成功后需要做的工作
                InputStream inputstream = new ByteArrayInputStream(binaryData);
                if (inputstream != null) {
                    OutputStream output = null;
                    try {
                        File file = new File(isExistDir(savePath), fileName);
                        file.createNewFile();
                        output = new FileOutputStream(file);
                        // 4k为单位，每4K写一次
                        byte buffer[] = new byte[4 * 1024];
                        int temp;
                        while ((temp = inputstream.read(buffer)) != -1) {
                            // 获取指定信,防止写入没用的信息
                            output.write(buffer, 0, temp);
                        }
                        output.flush();
                        listener.onDownloadSuccess(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                        listener.onDownloadFailed(e.getMessage());
                    } finally {
                        try {
                            output.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] binaryData, Throwable error) {
                Log.e("TAG", "==" + statusCode);
                listener.onDownloadFailed(error.getMessage());
            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                super.onProgress(bytesWritten, totalSize);
                int count = (int) ((bytesWritten * 1.0 / totalSize) * 100);
                listener.onDownloading(count);
            }
        });
    }

    private static String isExistDir(String saveDir) throws IOException {
        File downloadFile = new File(saveDir);
        if (!downloadFile.mkdirs()) {
            downloadFile.createNewFile();
        }
        return downloadFile.getAbsolutePath();
    }

    public interface OnDownloadListener {
        /**
         * 下载成功
         */
        void onDownloadSuccess(File file);

        /**
         * @param progress 下载进度
         */
        void onDownloading(int progress);

        /**
         * 下载失败
         */
        void onDownloadFailed(String e);
    }


    public static void cancel(Context context) {
        client.cancelRequests(context, true);
    }
}
