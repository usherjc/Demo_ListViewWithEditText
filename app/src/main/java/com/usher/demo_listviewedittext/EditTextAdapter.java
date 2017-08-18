package com.usher.demo_listviewedittext;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by UsherChen on 2017/8/18.
 * Details
 */

public class EditTextAdapter extends BaseAdapter implements
        View.OnClickListener,
        View.OnFocusChangeListener {

    private Context mContext;
    private ArrayList<String> mInfoList;
    private OperateInfoCallback mCallback;
    private int mFocusPosition = -1;//当焦点存在时的位置
    private EditTextWatcher watcher = new EditTextWatcher();

    public EditTextAdapter(Context mContext, ArrayList<String> mInfoList, OperateInfoCallback mCallback) {
        this.mContext = mContext;
        this.mInfoList = mInfoList;
        this.mCallback = mCallback;
    }

    @Override
    public int getCount() {
        try {
            return mInfoList.size();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Object getItem(int i) {
        try {
            return mInfoList.get(i);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_edittext, null);
            holder = new ViewHolder(view);
            holder.mIvDelItem.setOnClickListener(this);
            holder.mEtItem.addTextChangedListener(watcher);
            holder.mEtItem.setOnFocusChangeListener(this);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.mIvDelItem.setTag(i);
        //先移除避免数据错误
        holder.mEtItem.removeTextChangedListener(watcher);
        holder.mEtItem.setText(mInfoList.get(i));
        holder.mEtItem.setSelection(holder.mEtItem.length());
        holder.mEtItem.setTag(i);
        holder.mEtItem.addTextChangedListener(watcher);
        return view;
    }

    //删除操作
    @Override
    public void onClick(View view) {
        int position = (int) view.getTag();
        if (mCallback != null) {
            mCallback.deleteItem(position);
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (b) {
            mFocusPosition = (int) view.getTag();
        }
    }

    class ViewHolder {
        private EditText mEtItem;
        private ImageView mIvDelItem;

        public ViewHolder(View convertView) {
            mEtItem = (EditText) convertView.findViewById(R.id.et_item);
            mIvDelItem = (ImageView) convertView.findViewById(R.id.iv_del_item);
        }
    }

    interface OperateInfoCallback {
        void deleteItem(int position);
    }

    class EditTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            try {
                mInfoList.set(mFocusPosition, editable.toString().trim());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
