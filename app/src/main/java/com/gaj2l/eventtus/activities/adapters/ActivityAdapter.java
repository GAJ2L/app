package com.gaj2l.eventtus.activities.adapters;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gaj2l.eventtus.R;
import com.gaj2l.eventtus.activities.ActivityActivity;
import com.gaj2l.eventtus.models.Activity;

import java.util.List;

/**
 * Created by lucas on 14/04/17.
 */

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder>
{
    private List<Activity> list;
    private android.app.Activity activity;

    public ActivityAdapter( android.app.Activity activity, List<Activity> list )
    {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i)
    {
        viewHolder.setItemTitle(getActivity(i).getName());
        viewHolder.setItemDetail(getActivity(i).getLocalName());
        viewHolder.setActivity(getActivity(i));
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public List<Activity> getEvents()
    {
        return this.list;
    }

    public Activity getActivity(int i)
    {
        return this.list.get(i);
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView itemTitle;
        public TextView itemDetail;
        public TextView btnRemove;
        public Activity activity;

        public ViewHolder(View itemView)
        {
            super(itemView);
            itemTitle  = (TextView)itemView.findViewById(R.id.item_title);
            itemDetail = (TextView)itemView.findViewById(R.id.item_detail);
            btnRemove  = (TextView)itemView.findViewById(R.id.btnRemove);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    onClickCard(v);
                }
            });

            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRemove(v);
                }
            });
        }

        public void setActivity( Activity activity )
        {
            this.activity = activity;
        }

        public void setItemTitle(String title)
        {
            itemTitle.setText(title);
        }

        public void setItemDetail(String detail)
        {
            itemDetail.setText(detail);
        }

        public void onClickCard(View v)
        {
//            Intent intent = new Intent(activity, ActivityActivity.class);
//            intent.putExtra("ref_event",event.getId());
//            activity.startActivity(intent);
        }

        public void onRemove( View v )
        {
            Snackbar.make(v, "Atividade: "+ activity.getName() + " removido!" ,Snackbar.LENGTH_SHORT).show();
        }
    }
}

