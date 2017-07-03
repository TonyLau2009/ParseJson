package jocelyn_test03.com.json_test.m_MySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


import jocelyn_test03.com.json_test.M_UI.ListViewAdapter;
import jocelyn_test03.com.json_test.PersonModle.Persons;

/**
 * Created by Jocelyn on 27/10/2016.
 */

public class DataParser extends AsyncTask<Void,Void,Integer> {

    Context c;
    ListView lv;
    String jsonData;

    ProgressDialog pd;

    ArrayList<Persons> personsList = new ArrayList<>();

    public DataParser(Context c, ListView lv, String jsonData) {
        this.c = c;
        this.lv = lv;
        this.jsonData = jsonData;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("Parse");
        pd.setMessage("Parsing....Please wait");
        pd.show();
    }


    @Override
    protected Integer doInBackground(Void... params) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        pd.dismiss();

        if(result == 0){
            Toast.makeText(c,"Unable to parse",Toast.LENGTH_SHORT).show();
        }else{
            //TODO call Adapter to bing data;
            ListViewAdapter adapter = new ListViewAdapter(c,personsList);
            lv.setAdapter(adapter);
        }
    }

    private int parseData(){
        try {
            JSONObject jsonObj = new JSONObject(jsonData);
            JSONArray jsonArr = jsonObj.getJSONArray("person");
            JSONObject finalObj = null;
            personsList.clear();
            Persons p = null;
            for(int i = 0; i<jsonArr.length(); i++){
                finalObj = jsonArr.getJSONObject(i);
                int id = finalObj.getInt("id");
                String name = finalObj.getString("name");
                int age = finalObj.getInt("age");
                String address = finalObj.getString("address");

                p = new Persons();
                p.setId(id);
                p.setName(name);
                p.setAge(age);
                p.setAddress(address);

                personsList.add(p);
            }
            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
