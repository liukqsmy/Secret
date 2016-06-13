package com.example.liuk.secret.net;

import android.os.AsyncTask;

import com.example.liuk.secret.Config;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.MalformedInputException;

/**
 * Created by Administrator on 2016/6/7.
 */
public class NetConnection {
    public NetConnection(final String url, final HttpMethod method, SuccessCallback successCallback, FailCallback failCallback, final String... kvs){


        new AsyncTask<Void, Void, String>(){

            @Override
            protected String doInBackground(Void... arg0) {

                StringBuffer paramStr = new StringBuffer();
                for (int i=0; i<kvs.length; i+=2)
                {
                    paramStr.append(kvs[i]).append("=").append(kvs[i+1]).append("&");
                }
                try {
                    URLConnection uc;

                    switch (method){
                        case POST:
                            uc =  new URL(url).openConnection();
                            uc.setDoOutput(true);
                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(uc.getOutputStream(), Config.CHARSET));
                            bw.write(paramStr.toString());
                            break;
                        default:
                            //uc = new
                            break;

                    }
                } catch (MalformedInputException e){

                } catch (IOException e) {
                    e.printStackTrace();
                }


                return null;
            }
        };
    }

    public static interface SuccessCallback{
        void onSuccess(String result);
    }

    public static interface FailCallback{
        void onFail();
    }
}
