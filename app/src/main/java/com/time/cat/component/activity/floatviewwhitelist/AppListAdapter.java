package com.time.cat.component.activity.floatviewwhitelist;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.time.cat.R;

import java.util.List;

public class AppListAdapter extends RecyclerView.Adapter {

    private List<ApplicationInfoWrap> mApplicationInfos;

    private Context mContext;
    private OnItemClickListener mListener;
    private boolean isEditMode = true;

    public AppListAdapter(Context context) {
        mContext = context;
    }

    public void setAppList(List<ApplicationInfoWrap> list) {
        mApplicationInfos = list;
    }

    public void setmListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(mContext).inflate(R.layout.item_app_list_float, null);
        AppViewHolder holder = new AppViewHolder(convertView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final AppViewHolder holder = (AppViewHolder) viewHolder;
        Drawable drawable = mApplicationInfos.get(position).applicationInfo.loadIcon(mContext.getPackageManager());
        if (drawable != null) {
            holder.mAppIcon.setImageDrawable(drawable);
        }
        holder.mAppName.setText(mApplicationInfos.get(position).applicationInfo.loadLabel(mContext.getPackageManager()));
        holder.mRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mAppCheck.setChecked(!holder.mAppCheck.isChecked());
            }
        });
        if ((mApplicationInfos.get(position).applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0)//排除系统应用
        {
            holder.mAppDescribe.setText(R.string.user_app);
        } else {
            holder.mAppDescribe.setText(R.string.system_app);
        }
        if (isEditMode) {
            holder.mAppCheck.setVisibility(View.VISIBLE);
        } else {
            holder.mAppCheck.setVisibility(View.GONE);
        }
        holder.mAppCheck.setOnCheckedChangeListener(null);
//		holder.mAppCheck.setChecked(!mApplicationInfos.get(position).isSelected);
//		holder.mTriggerMode.setSelection(mApplicationInfos.get(position).selection>0?0:1);
        holder.mAppCheck.setChecked(mApplicationInfos.get(position).isSelected);
        holder.mAppCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mListener != null) {
                    mListener.onItemClick(position, isChecked);
                }
            }
        });
        holder.mRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEditMode) {
                    holder.mAppCheck.setChecked(!holder.mAppCheck.isChecked());
                }
            }
        });
//		holder.mAppCheck.setChecked(mApplicationInfos.get(position).isSelected);
//		holder.mTriggerMode.setSelection(mApplicationInfos.get(position).selection);
    }

    @Override
    public long getItemId(int position) {
        // TODO 自动生成的方法存根
        return position;
    }

    @Override
    public int getItemCount() {
        return mApplicationInfos.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position, boolean isChecked);
    }

    public static class ApplicationInfoWrap {
        public static final int NON_SELECTION = 3;
        public ApplicationInfo applicationInfo;
        public boolean isSelected = false;
        public int selection = NON_SELECTION;
    }

    private class AppViewHolder extends RecyclerView.ViewHolder {
        public TextView mAppName;
        public ImageView mAppIcon;
        public TextView mAppDescribe;
        public CheckBox mAppCheck;
        public View mRoot;

        public AppViewHolder(View itemView) {
            super(itemView);
            mRoot = itemView;
            mAppName = itemView.findViewById(R.id.app_name);
            mAppIcon = itemView.findViewById(R.id.app_icon);
            mAppDescribe = itemView.findViewById(R.id.app_name_describe);
            mAppCheck = itemView.findViewById(R.id.app_check);
        }
    }


}