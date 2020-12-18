package com.example.myapplication;


import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myapplication.adapter.RecyclerGridAdapter;
import com.example.myapplication.adapter.RecyclerStaggeredAdapter;
import com.example.myapplication.bean.GoodsInfo;
import com.example.myapplication.constant.ImageList;
import com.example.myapplication.util.MenuUtil;
import com.example.myapplication.util.Utils;
import com.example.myapplication.widget.BannerPager;
import com.example.myapplication.widget.SpacesItemDecoration;

public class FirstActivity extends AppCompatActivity implements BannerPager.BannerClickListener, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "BannerPagerActivity";
    private TextView tv_pager;
    private TextView tv_desc;
    private SearchView.SearchAutoComplete sac_key; // 声明一个搜索自动完成的编辑框对象
    private TextView tv_simple;
    private SwipeRefreshLayout srl_simple;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Bundle bundle = new Bundle(); // 创建一个包裹对象
        bundle.putString("tag", TAG); // 往包裹中存入名叫tag的标记

//        tv_pager = findViewById(R.id.tv_pager);
        // 从布局文件中获取名叫banner_pager的横幅轮播条
        BannerPager banner = findViewById(R.id.banner_pager);
        // 获取横幅轮播条的布局参数
        LayoutParams params = (LayoutParams) banner.getLayoutParams();
        params.height = (int) (Utils.getScreenWidth(this) * 250f / 640f);
        // 设置横幅轮播条的布局参数
        banner.setLayoutParams(params);
        // 设置横幅轮播条的广告图片队列
        banner.setImage(ImageList.getDefault());
        // 设置横幅轮播条的广告点击监听器
        banner.setOnBannerListener(this);
        // 开始广告图片的轮播滚动
        banner.start();
        // 从布局文件中获取名叫tl_head的工具栏
        Toolbar tl_head = findViewById(R.id.tl_head);
        // 设置工具栏的标题文字
        tl_head.setTitle("商城");
        // 使用tl_head替换系统自带的ActionBar
        setSupportActionBar(tl_head);
        tv_desc = findViewById(R.id.tv_desc);
        initRecyclerGrid(); // 初始化网格布局的循环视图
        initRecyclerStaggered(); // 初始化瀑布流布局的循环视图
//        tv_simple = findViewById(R.id.tv_simple);
//        // 从布局文件中获取名叫srl_simple的下拉刷新布局
//        srl_simple = findViewById(R.id.srl_simple);
        // 给srl_simple设置下拉刷新监听器
//        srl_simple.setOnRefreshListener(this);
        // 设置下拉刷新布局的进度圆圈颜色
//        srl_simple.setColorSchemeResources(
//                R.color.red, R.color.orange, R.color.green, R.color.blue);
    }

    private void initRecyclerStaggered() {
        // 从布局文件中获取名叫rv_staggered的循环视图
        RecyclerView rv_staggered = findViewById(R.id.rv_staggered);
        // 创建一个垂直方向的瀑布流布局管理器
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(
                3, RecyclerView.VERTICAL);
        // 设置循环视图的布局管理器
        rv_staggered.setLayoutManager(manager);
        // 构建一个服装列表的瀑布流适配器
        RecyclerStaggeredAdapter adapter = new RecyclerStaggeredAdapter(this, GoodsInfo.getDefaultStag());
        // 设置瀑布流列表的点击监听器
        adapter.setOnItemClickListener(adapter);
        // 设置瀑布流列表的长按监听器
        adapter.setOnItemLongClickListener(adapter);
        // 给rv_staggered设置服装瀑布流适配器
        rv_staggered.setAdapter(adapter);
        // 设置rv_staggered的默认动画效果
        rv_staggered.setItemAnimator(new DefaultItemAnimator());
        // 给rv_staggered添加列表项之间的空白装饰
        rv_staggered.addItemDecoration(new SpacesItemDecoration(3));

    }
    // 一旦在下拉刷新布局内部往下拉动页面，就触发下拉监听器的onRefresh方法
//    public void onRefresh() {
//        tv_simple.setText("正在刷新");
//        // 延迟若干秒后启动刷新任务
//        mHandler.postDelayed(mRefresh, 2000);
//    }

    private Handler mHandler = new Handler(); // 声明一个处理器对象
    // 定义一个刷新任务
    private Runnable mRefresh = new Runnable() {
        @Override
        public void run() {
            tv_simple.setText("");
            // 结束下拉刷新布局的刷新动作
            srl_simple.setRefreshing(false);
        }
    };
    private void initRecyclerGrid() {
        // 从布局文件中获取名叫rv_grid的循环视图
        RecyclerView rv_grid = findViewById(R.id.rv_grid);
        // 创建一个垂直方向的网格布局管理器
        GridLayoutManager manager = new GridLayoutManager(this, 5);
        // 设置循环视图的布局管理器
        rv_grid.setLayoutManager(manager);
        // 构建一个市场列表的网格适配器
        RecyclerGridAdapter adapter = new RecyclerGridAdapter(this, GoodsInfo.getDefaultGrid());
        // 设置网格列表的点击监听器
        adapter.setOnItemClickListener(adapter);
        // 设置网格列表的长按监听器
        adapter.setOnItemLongClickListener(adapter);
        // 给rv_grid设置市场网格适配器
        rv_grid.setAdapter(adapter);
        // 设置rv_grid的默认动画效果
        rv_grid.setItemAnimator(new DefaultItemAnimator());
        // 给rv_grid添加列表项之间的空白装饰
        rv_grid.addItemDecoration(new SpacesItemDecoration(1));

    }

    // 一旦点击了广告图，就回调监听器的onBannerClick方法
    public void onBannerClick(int position) {
//        String desc = String.format("您点击了第%d张图片", position + 1);
//        tv_pager.setText(desc);
    }


    // 根据菜单项初始化搜索框
    @SuppressLint("RestrictedApi")
    private void initSearchView(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        // 从菜单项中获取搜索框对象
        SearchView searchView = (SearchView) menuItem.getActionView();
        // 设置搜索框默认自动缩小为图标
        searchView.setIconifiedByDefault(getIntent().getBooleanExtra("collapse", true));
        // 设置是否显示搜索按钮。搜索按钮只显示一个箭头图标，Android暂不支持显示文本。
        // 查看Android源码，搜索按钮用的控件是ImageView，所以只能显示图标不能显示文字。
        searchView.setSubmitButtonEnabled(true);
        // 从系统服务中获取搜索管理器
        SearchManager sm = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        // 创建搜索结果页面的组件名称对象
        ComponentName cn = new ComponentName(this, SearchResultActivity.class);
        // 从结果页面注册的activity节点获取相关搜索信息，即searchable.xml定义的搜索控件
        SearchableInfo info = sm.getSearchableInfo(cn);
        if (info == null) {
            Log.d(TAG, "Fail to get SearchResultActivity.");
            return;
        }
        // 设置搜索框的可搜索信息
        searchView.setSearchableInfo(info);
        // 从搜索框中获取名叫search_src_text的自动完成编辑框
        sac_key = searchView.findViewById(R.id.search_src_text);
        // 设置自动完成编辑框的文本颜色
        sac_key.setTextColor(Color.BLACK);
        // 设置自动完成编辑框的提示文本颜色
        sac_key.setHintTextColor(Color.BLACK);
        // 给搜索框设置文本变化监听器
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            // 搜索关键词完成输入
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            // 搜索关键词发生变化
//            public boolean onQueryTextChange(String newText) {
//                doSearch(newText);
//                return true;
//            }
//        });
        Bundle bundle = new Bundle(); // 创建一个新包裹
        bundle.putString("hi", "hello"); // 往包裹中存放名叫hi的字符串
        // 设置搜索框的额外搜索数据
        searchView.setAppSearchData(bundle);
    }

//    // 自动匹配相关的关键词列表
//    private void doSearch(String text) {
//        if (text.indexOf("i") == 0) {
//            // 根据提示词数组构建一个数组适配器
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                    R.layout.search_list_auto, hintArray);
//            // 设置自动完成编辑框的数组适配器
//            sac_key.setAdapter(adapter);
//            // 给自动完成编辑框设置列表项的点击监听器
//            sac_key.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                // 一旦点击关键词匹配列表中的某一项，就触发点击监听器的onItemClick方法
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    sac_key.setText(((TextView) view).getText());
//                }
//            });
//        }
//    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        // 显示菜单项左侧的图标
        MenuUtil.setOverflowIconVisible(featureId, menu);
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 从menu_search.xml中构建菜单界面布局
        getMenuInflater().inflate(R.menu.menu_search, menu);
        // 初始化搜索框
        initSearchView(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) { // 点击了工具栏左边的返回箭头
            finish();
        } else if (id == R.id.menu_refresh) { // 点击了刷新图标
            return true;
        } else if (id == R.id.menu_about) { // 点击了关于菜单项
            Toast.makeText(this, "这个是工具栏的演示demo", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.menu_quit) { // 点击了退出菜单项
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {

    }
}
