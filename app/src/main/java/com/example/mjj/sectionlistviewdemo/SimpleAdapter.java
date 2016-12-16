package com.example.mjj.sectionlistviewdemo;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Description：适配器
 * <p>
 * Created by Mjj on 2016/12/15.
 */

class SimpleAdapter extends ArrayAdapter<Item> implements PinnedSectionListView.PinnedSectionListAdapter {

    private List<String> secList;
    private List<String> posiList;

    public SimpleAdapter(Context context, int resource, int textViewResourceId, List<String> secList, List<String> posiList) {
        super(context, resource, textViewResourceId);
        this.secList = secList;
        this.posiList = posiList;
        generateDataset(secList, posiList);
    }

    public void generateDataset(List<String> sectionList, List<String> positionList) {
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
        view.setTag("" + position); // 并没有使用到
        Item item = getItem(position);
        if (item.type == Item.SECTION) {
            view.setBackgroundColor(Color.GRAY);
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