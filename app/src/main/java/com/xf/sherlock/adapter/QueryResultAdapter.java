package com.xf.sherlock.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xf.sherlock.R;
import com.xf.sherlock.bean.TicketInfo;
import com.xf.sherlock.bean.TicketInfoContainer;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by TC on 2016/1/12.
 */
public class QueryResultAdapter extends RecyclerView.Adapter<QueryResultAdapter.ViewHolder> {

    private Context mContext;
    private List<TicketInfoContainer> mTicketInfoContainers;

    public QueryResultAdapter(@NonNull Context context, @NonNull List<TicketInfoContainer> ticketInfoContainers) {
        mContext = context;
        mTicketInfoContainers = ticketInfoContainers;
    }

    @Override
    public QueryResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.ticket_info_item, parent, false));

    }

    @Override
    public void onBindViewHolder(QueryResultAdapter.ViewHolder holder, int position) {
        TicketInfo ticketInfo = mTicketInfoContainers.get(position).getTicketInfo();
        holder.mFrom.setText(ticketInfo.getFromStationName());
        holder.mTo.setText(ticketInfo.getToStationName());
        holder.mTrainNum.setText(ticketInfo.getStationTrainCode());
    }

    @Override
    public int getItemCount() {
        return mTicketInfoContainers == null ? 0 : mTicketInfoContainers.size();
    }

    public void setData(List<TicketInfoContainer> data) {
        mTicketInfoContainers = data;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.train_num)
        public TextView mTrainNum;
        @Bind(R.id.from)
        public TextView mFrom;
        @Bind(R.id.to)
        public TextView mTo;


        public ViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
