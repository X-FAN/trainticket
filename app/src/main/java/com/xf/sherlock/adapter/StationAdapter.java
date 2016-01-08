package com.xf.sherlock.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.xf.sherlock.R;
import com.xf.sherlock.bean.Station;

import java.util.ArrayList;
import java.util.List;

public class StationAdapter extends BaseAdapter implements Filterable {

    private Context context;
    private List<Station> list;
    private List<Station> filterList;

    public StationAdapter(Context context, List<Station> list) {
        this.context = context;
        this.list = list;
        filterList = new ArrayList<Station>();
        filterList.addAll(list);
    }

    @Override
    public int getCount() {
        return filterList == null ? 0 : filterList.size();
    }

    @Override
    public Station getItem(int position) {
        return filterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        final Station station = filterList.get(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.city_item, parent, false);
            viewHolder.letterTv = (TextView) convertView.findViewById(R.id.id_section);
            viewHolder.nameTv = (TextView) convertView.findViewById(R.id.id_city_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (station.isShow()) {
            viewHolder.letterTv.setVisibility(View.VISIBLE);
            viewHolder.letterTv.setText(station.getSection());
        } else {
            viewHolder.letterTv.setVisibility(View.GONE);
        }

        viewHolder.nameTv.setText(station.getStationName());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filterList = (ArrayList<Station>) results.values;
                if (results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }

            @Override
            protected FilterResults performFiltering(CharSequence s) {

                FilterResults results = new FilterResults();
                ArrayList<Station> queryResultCities = new ArrayList<>();
                results.values = queryResultCities;
                results.count = queryResultCities.size();

                if (TextUtils.isEmpty(s)) {
                    results.values = list;
                    results.count = list.size();
                    return results;
                }

                String str = s.toString();
                for (Station station : list) {
                    if ((station.getStationName().startsWith(str) || station.getFullPY().startsWith(str) || station.getShortPY().startsWith(str))) {
                        queryResultCities.add(station);
                    }
                }
                results.values = queryResultCities;
                results.count = queryResultCities.size();
                return results;
            }
        };
        return filter;
    }


    public int getPositionForSection(String sectionIndex) {
        // TODO Auto-generated method stub
        for (int i = 0; i < getCount(); i++) {
            if (sectionIndex.equals(filterList.get(i).getSection())) {
                return i;
            }
        }

        return -1;
    }

    final static class ViewHolder {
        TextView letterTv;
        TextView nameTv;
    }
}
