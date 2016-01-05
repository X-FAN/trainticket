package com.xf.sherlock.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.xf.sherlock.R;
import com.xf.sherlock.bean.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CityAdapter extends BaseAdapter implements SectionIndexer,
        Filterable {

    private Context context;
    private List<City> list;
    private List<City> filterList;

    public CityAdapter(Context context, List<City> list) {
        this.context = context;
        this.list = list;
        filterList = new ArrayList<City>();
        filterList.addAll(list);
    }

    @Override
    public int getCount() {
        return filterList == null ? 0 : filterList.size();
    }

    @Override
    public Object getItem(int position) {
        return filterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        final City city = filterList.get(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.city_item, parent, false);
            viewHolder.letterTv = (TextView) convertView.findViewById(R.id.id_section);
            viewHolder.nameTv = (TextView) convertView.findViewById(R.id.id_city_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // 根据position获取分类的首字母的Char ascii值
        int section = getSectionForPosition(position);

        // 如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (position == getPositionForSection(section)) {
            viewHolder.letterTv.setVisibility(View.VISIBLE);
            viewHolder.letterTv.setText(city.getSection());
        } else {
            viewHolder.letterTv.setVisibility(View.GONE);
        }

        viewHolder.nameTv.setText(city.getName());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filterList = (ArrayList<City>) results.values;
                if (results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }

            @Override
            protected FilterResults performFiltering(CharSequence s) {

                FilterResults results = new FilterResults();
                ArrayList<City> queryResultCities = new ArrayList<City>();
                results.values = queryResultCities;
                results.count = queryResultCities.size();

                if (TextUtils.isEmpty(s)) {
                    results.values = list;
                    results.count = list.size();
                    return results;
                }

                String str = s.toString();
                for (City city : list) {
                    if ((str.matches("^[a-zA-Z]*") && city.getKey().startsWith(str.toLowerCase(Locale.getDefault()))) || city.getName().startsWith(str) || city.getPy().startsWith(str)) {
                        queryResultCities.add(city);
                    }
                }
                results.values = queryResultCities;
                results.count = queryResultCities.size();
                return results;
            }
        };
        return filter;
    }

    @Override
    public Object[] getSections() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    @Override
    public int getPositionForSection(int sectionIndex) {
        // TODO Auto-generated method stub
        for (int i = 0; i < getCount(); i++) {
            char firstChar = filterList.get(i).getAlpha().charAt(0);
            if (firstChar == sectionIndex) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    @Override
    public int getSectionForPosition(int position) {
        return filterList.get(position).getAlpha().charAt(0);
    }

    final static class ViewHolder {
        TextView letterTv;
        TextView nameTv;
    }
}
