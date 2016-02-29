package com.xf.sherlock.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.xf.sherlock.R;
import com.xf.sherlock.bean.Seat;
import com.xf.sherlock.bean.TicketInfo;
import com.xf.sherlock.bean.TicketInfoContainer;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by TC on 2016/1/12.
 */
public class QueryResultAdapter extends RecyclerView.Adapter<QueryResultAdapter.ViewHolder> {

    private Context mContext;
    private List<TicketInfoContainer> mTicketInfoContainers;
    private TextDrawable mPass;//过站
    private TextDrawable mStart;//始发站
    private TextDrawable mEnd;//终点站

    public QueryResultAdapter(@NonNull Context context, @NonNull List<TicketInfoContainer> ticketInfoContainers) {
        mContext = context;
        mTicketInfoContainers = ticketInfoContainers;
        mPass = TextDrawable.builder().buildRound("过", Color.parseColor("#00bcd4"));
        mStart = TextDrawable.builder().buildRound("始", Color.parseColor("#614DB3"));
        mEnd = TextDrawable.builder().buildRound("终", Color.parseColor("#FF4081"));
        mPass.setBounds(0, 0, 50, 50);
        mStart.setBounds(0, 0, 50, 50);
        mEnd.setBounds(0, 0, 50, 50);
    }

    @Override
    public QueryResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.ticket_info_item, parent, false));

    }

    @Override
    public void onBindViewHolder(QueryResultAdapter.ViewHolder holder, final int position) {
        TicketInfo ticketInfo = mTicketInfoContainers.get(position).getTicketInfo();
        holder.mFrom.setText(ticketInfo.getFromStationName());
        holder.mTo.setText(ticketInfo.getToStationName());
        holder.mTrainNum.setText(ticketInfo.getStationTrainCode());
        holder.mDuration.setText(getDuration(ticketInfo.getLishi()));
        if (isStart(ticketInfo)) {
            holder.mFrom.setCompoundDrawables(mStart, null, null, null);
        } else {
            holder.mFrom.setCompoundDrawables(mPass, null, null, null);
        }
        if (isEnd(ticketInfo)) {
            holder.mTo.setCompoundDrawables(mEnd, null, null, null);
        } else {
            holder.mTo.setCompoundDrawables(mPass, null, null, null);

        }
        List<Seat> seats = parseSeatInfo(ticketInfo.getYpInfo());
        holder.mLeftTicketInfo.setText(getLeftTicket(seats));
        if (mOnItemClickLitener != null) {//设置点击事件
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(v, position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickLitener.onItemLongClick(v, position);
                    return false;
                }
            });
        }
    }

    private String getDuration(@NonNull String duration) {
        String[] array = duration.split(":");
        if (array.length == 2) {
            StringBuilder builder = new StringBuilder(array[0]);
            builder.append("小时")
                    .append(array[1])
                    .append("分");
            return builder.toString();
        }
        return "-1";
    }

    //是否始发站
    private boolean isStart(@NonNull TicketInfo ticketInfo) {
        return ticketInfo.getStartStationName().equals(ticketInfo.getFromStationName());
    }

    //是否终点站
    private boolean isEnd(@NonNull TicketInfo ticketInfo) {
        return ticketInfo.getEndStationName().equals(ticketInfo.getToStationName());
    }

    @Override
    public int getItemCount() {
        return mTicketInfoContainers == null ? 0 : mTicketInfoContainers.size();
    }

    private String getLeftTicket(@NonNull List<Seat> seats) {
        StringBuilder builder = new StringBuilder();
        for (Seat seat : seats) {
            builder.append(seat.toString())
                    .append("   ");
        }
        return builder.toString();
    }

    //解析余票信息
    private List<Seat> parseSeatInfo(@NonNull String leftTicketsInfo) {
        int count = leftTicketsInfo.length() / 10;
        List<Seat> seats = new ArrayList<Seat>();
        for (int j = 0; j < count; j++) {
            String info;
            if (j == count - 1) {
                info = leftTicketsInfo.substring(j * 10);
            } else {
                info = leftTicketsInfo.substring(j * 10, (j + 1) * 10);
            }
            int num = Integer.valueOf(info.substring(6));
            if (num >= 3000) {//大于3000表示无座
                continue;
            }
            Seat seat = new Seat();

            float price = Float.valueOf(info.substring(1, 6)) / 10;
            char type = info.charAt(0);
            String seatType;
            switch (type) {
                case '1':
                    seatType = Seat.YING_ZUO_TYPE;
                    break;
                case '2':
                    seatType = Seat.RUAN_ZUO_TYPE;
                    break;
                case '3':
                    seatType = Seat.YING_WO_TYPE;
                    break;

                case '4':
                    seatType = Seat.RUAN_WO_TYPE;
                    break;

                case '6':
                    seatType = Seat.GAO_JI_RUAN_WO_TYPE;
                    break;
                case '7':
                case 'M':
                    seatType = Seat.YI_DENG_ZUO_TYPE;
                    break;
                case 'O':
                case '8':
                    seatType = Seat.ER_DENG_ZUO_TYPE;
                    break;

                case '9':
                    seatType = Seat.SHANG_WU_ZUO_TYPE;
                    break;

                case 'P':
                    seatType = Seat.TE_DENG_ZUO_TYPE;
                    break;
                default:
                    seatType = null;
                    break;
            }
            if (seatType != null) {
                seat.setType(seatType);
                seat.setPrice(price);
                seat.setCount(num);
                seats.add(seat);
            }
        }
        return seats;
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
        @Bind(R.id.duration)
        public TextView mDuration;
        @Bind(R.id.left_ticket_info)
        public TextView mLeftTicketInfo;


        public ViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

}
