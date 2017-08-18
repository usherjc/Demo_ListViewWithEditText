package com.usher.demo_listviewedittext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, EditTextAdapter.OperateInfoCallback {

    private ListView mLvMain;
    private Button mBtnNewAdd;
    private ArrayList<String> mInfoList;
    private EditTextAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initAction();
        initData();
    }

    private void initData() {
        if (mInfoList == null) {
            mInfoList = new ArrayList<>();
            mInfoList.add("1");
            mInfoList.add("2");
            mInfoList.add("3");
        }
        mAdapter = new EditTextAdapter(this, mInfoList, this);
        mLvMain.setAdapter(mAdapter);
    }

    private void initAction() {
        mBtnNewAdd.setOnClickListener(this);
        mLvMain.setDivider(null);
    }

    private void initView() {
        mLvMain = (ListView) findViewById(R.id.lv_main);
        mBtnNewAdd = (Button) findViewById(R.id.btn_new_add);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == mBtnNewAdd.getId()) {
            mInfoList.add("");
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void deleteItem(int position) {
        mInfoList.remove(position);
        mAdapter.notifyDataSetChanged();
    }
}
