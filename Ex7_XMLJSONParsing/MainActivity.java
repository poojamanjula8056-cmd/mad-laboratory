package com.example.xml_json_parsing;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    Button btnXML, btnJSON;
    TextView txtXML, txtJSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnXML = findViewById(R.id.btnXML);
        btnJSON = findViewById(R.id.btnJSON);
        txtXML = findViewById(R.id.txtXML);
        txtJSON = findViewById(R.id.txtJSON);

        btnXML.setOnClickListener(v -> parseXML());
        btnJSON.setOnClickListener(v -> parseJSON());
    }

    private void parseXML() {
        StringBuilder builder = new StringBuilder();
        try {
            XmlPullParser parser = getResources().getXml(R.xml.student);
            int eventType = parser.getEventType();
            String tag = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        tag = parser.getName();
                        break;
                    case XmlPullParser.TEXT:
                        String text = parser.getText().trim();
                        if (!text.isEmpty()) {
                            if (tag.equals("name"))
                                builder.append("Name : ").append(text).append("\n");
                            else if (tag.equals("age"))
                                builder.append("Age : ").append(text).append("\n");
                            else if (tag.equals("department"))
                                builder.append("Department : ").append(text).append("\n\n");
                        }
                        break;
                }
                eventType = parser.next();
            }
            txtXML.setText(builder.toString());
        } catch (Exception e) {
            txtXML.setText(e.toString());
        }
    }

    private void parseJSON() {
        try {
            InputStream is = getResources().openRawResource(R.raw.student);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            JSONObject object = new JSONObject(builder.toString());
            JSONArray array = object.getJSONArray("students");
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < array.length(); i++) {
                JSONObject student = array.getJSONObject(i);
                output.append("Name : ")
                        .append(student.getString("name"))
                        .append("\n");
                output.append("Age : ")
                        .append(student.getInt("age"))
                        .append("\n");
                output.append("Department : ")
                        .append(student.getString("department"))
                        .append("\n\n");
            }
            txtJSON.setText(output.toString());
        }
        catch (Exception e) {
            txtJSON.setText(e.toString());
        }
    }
}
