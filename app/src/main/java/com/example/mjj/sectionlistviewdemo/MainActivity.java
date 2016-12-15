package com.example.mjj.sectionlistviewdemo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static List<String> sectionList = new ArrayList<>();
    public static List<String> positionList = new ArrayList<>();

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
        simpleAdapter = new SimpleAdapter(MainActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1);
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

    static class SimpleAdapter extends ArrayAdapter<Item> implements PinnedSectionListView.PinnedSectionListAdapter {

        private static final int[] COLORS = new int[]{R.color.colorA, R.color.colorAccent, R.color.colorB, R.color.colorPrimary};

        public SimpleAdapter(Context context, int resource, int textViewResourceId) {
            super(context, resource, textViewResourceId);
            generateDataset();
        }

        public void generateDataset() {
            int sectionPosition = 0, listPosition = 0;
            // 初始化数据源
            for (char i = 0; i < sectionList.size(); i++) {
                Item section = new Item(Item.SECTION, sectionList.get(i));
                section.sectionPosition = sectionPosition;
                section.listPosition = listPosition++;
                add(section);

                for (int j = 0; j < positionList.size(); j++) {
                    Item item = new Item(Item.ITEM, positionList.get(j));
                    item.sectionPosition = sectionPosition;
                    item.listPosition = listPosition++;
                    add(item);
                }
                sectionPosition++;
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getView(position, convertView, parent);
            view.setTextColor(Color.DKGRAY);
            view.setTag("" + position); // 并没有使用到
            Item item = getItem(position);
            if (item.type == Item.SECTION) {
                view.setBackgroundColor(parent.getResources().getColor(COLORS[item.sectionPosition % COLORS.length]));
            }
            return view;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            return getItem(position).type;
        }

        @Override
        public boolean isItemViewTypePinned(int viewType) {
            return viewType == Item.SECTION;
        }
    }

    // 实体类
    static class Item {

        // 区分项
        public static final int ITEM = 0;
        public static final int SECTION = 1;

        public final int type;
        public final String text;

        public int sectionPosition;
        public int listPosition;

        public Item(int type, String text) {
            this.type = type;
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

}
