package com.example.mjj.sectionlistviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：分级列表,也可适用于普通列表数据显示
 * <p>
 * Created by Mjj on 2016/12/15.
 */

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<String> sectionList = new ArrayList<>();
    private List<String> positionList = new ArrayList<>();

    private ListView listView;
    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 11; i++) {
            sectionList.add("section " + i);
            positionList.add("position - " + i);
        }

        listView = (ListView) findViewById(R.id.lv_main);
        listView.setOnItemClickListener(this);
        simpleAdapter = new SimpleAdapter(MainActivity.this, android.R.layout.simple_list_item_1,
                android.R.id.text1, sectionList, positionList);
        listView.setAdapter(simpleAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Item item = simpleAdapter.getItem(i);
        if (item != null) {
            if (item.type == Item.ITEM) {
                Toast.makeText(this, "Item 索引" + i + ": " + item.text, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Item " + i, Toast.LENGTH_SHORT).show();
        }
    }

}
