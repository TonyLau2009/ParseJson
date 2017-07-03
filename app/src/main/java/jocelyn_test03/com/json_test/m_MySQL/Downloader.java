package jocelyn_test03.com.json_test.m_MySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by Jocelyn on 27/10/2016.
 */

public class Downloader extends AsyncTask<Void, Void ,String>{

    Context c;
    String urlAddress;
    ListView lv;

    ProgressDialog pg;

    public Downloader(Context c, String urlAddress, ListView lv) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.lv = lv;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pg = new ProgressDialog(c);
        pg.setTitle("Fetch");
        pg.setMessage("Fetching....please wait!");
        pg.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        return this.downloadData();
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        pg.dismiss();

        if(s == null){
            Toast.makeText(c,"Unsuccessfull,Null returned",Toast.LENGTH_SHORT).show();
        }else{
            //TODO call DataParser;
            DataParser parser = new DataParser(c,lv,s);
            parser.execute();
        }
    }

    private String downloadData(){

        HttpURLConnection con = Connector.connect(urlAddress);

        if(con == null){
            return null;
        }

        InputStream is = null;
        try {
            is = new BufferedInputStream(con.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            StringBuffer response = new StringBuffer();
            if(br != null) {
                while ((line = br.readLine()) != null) {
                    response.append(line + "\n");
                }
                br.close();
            }else {
                return null;
            }
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
