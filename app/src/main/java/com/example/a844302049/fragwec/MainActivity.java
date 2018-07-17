package com.example.a844302049.fragwec;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import crossoverone.statuslib.StatusUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity.TAG";
    TextView titleTextView;
    public LinearLayout firstLinearLayout;
    public LinearLayout secondLinearLayout;
    public LinearLayout threeLinearLayout;
    public LinearLayout fourLinrearLayout;
    ViewPager mViewPager;
    ViewPagerFragmentAdapter mViewPagerFragmentAdapter;
    FragmentManager mFragmentManager;

    String[] titleName = new String[]{"微信","通讯录","发现","我"};
    List<Fragment> mFragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();
        initFragmentList();//初始化碎片集合
        //创建viewpagerfragment适配器，参数一是FragmentManager,参数二是碎片集合
        mViewPagerFragmentAdapter = new ViewPagerFragmentAdapter(mFragmentManager,mFragmentList);
        initView();//初始化界面
        initViewPager();//初始化viewpager界面
        initState();//设置沉浸式状态栏

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.firstLinearLayout:
                mViewPager.setCurrentItem(0);//设置当前项
                updateBottomLinearLayoutSelect(true,false,false,false);
                break;
            case R.id.secondLinearLayout:
                mViewPager.setCurrentItem(1);
                updateBottomLinearLayoutSelect(false,true,false,false);
                break;
            case R.id.threeLinearLayout:
                mViewPager.setCurrentItem(2);
                updateBottomLinearLayoutSelect(false,false,true,false);
                break;
            case R.id.fourLinearLayout:
                mViewPager.setCurrentItem(3);
                updateBottomLinearLayoutSelect(false,false,false,true);
                break;
            default:
                break;
        }
    }


    public void initFragmentList(){
        fragment1 wechat = new fragment1();
        fragment2 tongxun = new fragment2();
        fragment3 faxian = new fragment3();
        fragment4 wo = new fragment4();
        mFragmentList.add(wechat);
        mFragmentList.add(tongxun);
        mFragmentList.add(faxian);
        mFragmentList.add(wo);
    }//初始化碎片
    public void initView(){
        titleTextView = findViewById(R.id.textTop);//头部文字
        mViewPager = findViewById(R.id.ViewPagerLayout);//ViewPager布局
        firstLinearLayout = findViewById(R.id.firstLinearLayout);
        firstLinearLayout.setOnClickListener(this);
        secondLinearLayout = findViewById(R.id.secondLinearLayout);
        secondLinearLayout.setOnClickListener(this);
        threeLinearLayout = findViewById(R.id.threeLinearLayout);
        threeLinearLayout.setOnClickListener(this);
        fourLinrearLayout = findViewById(R.id.fourLinearLayout);
        fourLinrearLayout.setOnClickListener(this);
    }//初始化界面
    private void updateBottomLinearLayoutSelect(boolean f, boolean s, boolean t,boolean u) {
        firstLinearLayout.setSelected(f);
        secondLinearLayout.setSelected(s);
        threeLinearLayout.setSelected(t);
        fourLinrearLayout.setSelected(u);
    }//显示某个碎片的方法
    public void initViewPager(){
        mViewPager.addOnPageChangeListener(new ViewPagerOnPagerChangedListenter());//调用下方内部类viewpager的监听事件
        mViewPager.setAdapter(mViewPagerFragmentAdapter);//设置适配器
        mViewPager.setCurrentItem(0);//设置初始化的当前项，0为微信界面会显示
        titleTextView.setText(titleName[0]);//将标题设置为数组第一个元素，即“微信”
        updateBottomLinearLayoutSelect(true,false,false,false);//将碎片一，也就是微信界面显示，其他隐藏
    }//初始化viewpager的界面
    class ViewPagerOnPagerChangedListenter implements ViewPager.OnPageChangeListener{
        @Override
        public void onPageScrolled(int position,float positionOffset,int positionOffsetPixels){

        }

        @Override
        public void onPageSelected(int position) {
            boolean[] state = new boolean[titleName.length];//创建一个布尔类型的数组，元素数量为标题数组的数量
            state[position] = true;//当前项数组元素设置为true
            titleTextView.setText(titleName[position]);
            updateBottomLinearLayoutSelect(state[0],state[1],state[2],state[3]);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }//viewpager监听事件
    public void initState(){
        //参数二状态栏色值
        StatusUtil.setUseStatusBarColor(this,Color.parseColor("#344447"));
        //参数二是否沉浸，参数三状态栏字体是否为黑色
        StatusUtil.setSystemStatus(this, false, false);
    }
}
