package com.instance.working.whours.model;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/12 0012.
 */
public class ProjectIntentJSONSerial {
    private Context _context;
    private String _fileName;
    ProjectIntentJSONSerial(Context c,String name)
    {
        _context = c;
        _fileName = name;
    }
    public void onSave(ArrayList<ProjectInfo> ProjcetList) throws IOException,JSONException
    {
        JSONArray jArray = new JSONArray();
        for(ProjectInfo c:ProjcetList)
        {
            // JSONObject jObject = c.toJSON();
            jArray.put(c.toJSON());
        }

        //讲内容写到硬盘上
        Writer writer = null;
        try{
            OutputStream out = _context.openFileOutput(_fileName,Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(jArray.toString());
        }finally {
            if(writer != null)
            {
                writer.close();
            }
        }
    }

    public ArrayList<ProjectInfo> loadData() throws IOException,JSONException
    {
        ArrayList<ProjectInfo> crims = new ArrayList<ProjectInfo>();
        BufferedReader reader = null;
        try{
            //打开要读取的文件
            InputStream in = _context.openFileInput(_fileName);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while((line=reader.readLine())!= null)
            {
                jsonString.append(line);
            }
            JSONArray jArray = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            for(int i = 0 ;i < jArray.length(); i++)
            {
                crims.add(new ProjectInfo(jArray.getJSONObject(i)));
            }
        }catch (Exception e)
        {
            System.out.println(e.toString());
            System.out.println("--------------------");
            System.out.println(e.getMessage());
            System.out.println("--------------------");
            e.printStackTrace();
            Log.e("working", e.toString());
            Log.e("working", e.getMessage());

        }finally {
            if(reader != null)
            {
                reader.close();
            }
        }

        return crims;
    }
}
