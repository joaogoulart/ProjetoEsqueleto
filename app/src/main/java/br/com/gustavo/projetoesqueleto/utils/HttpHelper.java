package br.com.gustavo.projetoesqueleto.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;


import livroandroid.lib.utils.IOUtils;

public class HttpHelper
{
    private final String TAG = "Http";
    public final int TIMEOUT_MILLIS = 15000;
    public boolean LOG_ON = true;
    private String contentType;
    private String charsetToEncode;

    public HttpHelper() {
    }

    public String doGet(String url) throws IOException
        {
        return this.doGet(url, (Map) null, "UTF-8");
    }

    public String doGet(String url, Map<String, String> params, String charset) throws IOException
        {
        String queryString = this.getQueryString(params);
        if (queryString != null && queryString.trim().length() > 0) {
            url = url + "?" + queryString;
        }

        if (this.LOG_ON) {
            Log.d("Http", ">> Http.doGet: " + url);
        }

        URL u = new URL(url);
        HttpURLConnection conn = null;
        String s = null;

        try {
            conn = (HttpURLConnection) u.openConnection();
            if (this.contentType != null) {
                conn.setRequestProperty("Content-Type", this.contentType);

            }

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(15000);
            conn.setRequestProperty("accept", "application/json");
            conn.connect();
            InputStream e = null;
            int status = conn.getResponseCode();
            if (status >= 400) {
                Log.d("Http", "Error code: " + status);
                e = conn.getErrorStream();
            } else {
                e = conn.getInputStream();
            }

            s = IOUtils.toString(e, charset);
            if (this.LOG_ON) {
                Log.d("Http", "<< Http.doGet: " + s);
            }

            e.close();
        } catch (IOException var13) {
            throw var13;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }

        }

        return s;
    }

    public String doDelete(String url) throws IOException
        {
        return this.doDelete(url, (Map) null, "UTF-8");
    }

    public String doDelete(String url, Map<String, String> params, String charset) throws IOException
        {
        String queryString = this.getQueryString(params);
        if (queryString != null && queryString.trim().length() > 0) {
            url = url + "?" + queryString;
        }

        if (this.LOG_ON) {
            Log.d("Http", ">> Http.doDelete: " + url);
        }

        URL u = new URL(url);
        HttpURLConnection conn = null;
        String s = null;

        try {
            conn = (HttpURLConnection) u.openConnection();
            if (this.contentType != null) {
                conn.setRequestProperty("Content-Type", this.contentType);
            }

            conn.setRequestMethod("DELETE");
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(15000);
            conn.setDoOutput(true);
            conn.connect();
            InputStream e = conn.getInputStream();
            s = IOUtils.toString(e, charset);
            if (this.LOG_ON) {
                Log.d("Http", "<< Http.doGet: " + s);
            }

            e.close();
        } catch (IOException var12) {
            throw var12;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }

        }

        return s;
    }

    public String doPostJson(String url, Map<String, String> params, String charset) throws IOException
        {
        String queryString = this.getQueryString(params);
        byte[] bytes = params != null ? queryString.getBytes(charset) : null;
        if (this.LOG_ON) {
            Log.d("Http", "Http.doPost: " + url + "?" + params);
        }

        return this.doPostJson(url, bytes, charset);
    }

    public String doPostJson(String url, byte[] params, String charset) throws IOException
        {
        if (this.LOG_ON) {
            Log.d("Http", ">> Http.doPost: " + url);
        }

        URL u = new URL(url);
        HttpURLConnection conn = null;
        String s = null;

        try {

            conn = (HttpURLConnection) u.openConnection();
            if (this.contentType != null) {
                conn.setRequestProperty("Content-Type", this.contentType);
            }

            conn.setRequestMethod("POST");
            conn.setConnectTimeout(120000);
            conn.setReadTimeout(120000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("accept", "application/json");
            conn.connect();
            OutputStream e;
            if (params != null) {
                e = conn.getOutputStream();
                e.write(params);
                e.flush();
                e.close();
            }

            e = null;
            int status = conn.getResponseCode();
            InputStream e1;
            if (status >= 400) {
                Log.d("Http", "Error code: " + status);
                e1 = conn.getErrorStream();
            } else {
                e1 = conn.getInputStream();
            }

            s = IOUtils.toString(e1, charset);
            if (this.LOG_ON) {
                Log.d("Http", "<< Http.doPost: " + s);
            }

            e1.close();
        } catch (IOException var12) {
            throw var12;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return s;
    }

    public Bitmap doGetBitmap(String url) throws IOException
        {
        if (this.LOG_ON) {
            Log.d("Http", ">> Http.doGet: " + url);
        }

        URL u = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(15000);
        conn.setReadTimeout(15000);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.connect();
        InputStream in = conn.getInputStream();
        byte[] bytes = IOUtils.toBytes(in);
        if (this.LOG_ON) {
            Log.d("Http", "<< Http.doGet: " + bytes);
        }

        in.close();
        conn.disconnect();
        Bitmap bitmap = null;
        if (bytes != null) {
            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }

        return bitmap;
    }

    public String getQueryString(Map<String, String> params) throws IOException
        {
        if (params != null && params.size() != 0) {
            String urlParams = null;
            Iterator var3 = params.keySet().iterator();

            while (var3.hasNext()) {
                String chave = (String) var3.next();
                Object objValor = params.get(chave);
                if (objValor != null) {
                    String valor = objValor.toString();
                    if (this.charsetToEncode != null) {
                        valor = URLEncoder.encode(valor, this.charsetToEncode);
                    }

                    urlParams = urlParams == null ? "" : urlParams + "&";
                    urlParams = urlParams + chave + "=" + valor;
                }
            }

            return urlParams;
        } else {
            return null;
        }
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setCharsetToEncode(String encode) {
        this.charsetToEncode = encode;
    }

    public String doPost(String url, Map<String, Object> params) throws IOException
        {

        if (this.LOG_ON) {
            Log.d("Http", ">> Http.doPost: " + url);
        }

        URL u = new URL(url);
        HttpURLConnection conn = null;
        String s = null;

        try {

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, Object> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0; )
                sb.append((char) c);

            s = sb.toString();

        } catch (IOException var12) {
            throw var12;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }

        }

        return s;
    }


    private static String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }
}


