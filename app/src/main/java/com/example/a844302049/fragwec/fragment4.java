package com.example.a844302049.fragwec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by 844302049 on 2018/7/17.
 */

public class fragment4 extends Fragment {
    View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.wecat_layout,null);
        }
        ((TextView)mView.findViewById(R.id.TextWeCat)).setText("我的界面");
        return mView;
    }
}
