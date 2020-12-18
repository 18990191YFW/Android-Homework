package com.example.myapplication6.bean;

import com.example.myapplication6.R;

import java.util.ArrayList;

public class GoodsInfo {
    public long rowid; // 行号
    public int sn; // 序号
    public String name; // 名称
    public String desc; // 描述
    public float price; // 价格
    public String thumb_path; // 小图的保存路径
    public String pic_path; // 大图的保存路径
    public int thumb; // 小图的资源编号
    public int pic; // 大图的资源编号

    public GoodsInfo() {
        rowid = 0L;
        sn = 0;
        name = "";
        desc = "";
        price = 0;
        thumb_path = "";
        pic_path = "";
        thumb = 0;
        pic = 0;
    }

    // 声明一个手机商品的名称数组
    private static String[] mNameArray = {
            "中长外套", "风衣", "长袖", "羽绒服", "棉衣", "连帽外套","冬装外套","T恤"
    };
    // 声明一个手机商品的描述数组
    private static String[] mDescArray = {
            "花花公子加绒加厚风衣男士中长款外套秋冬季韩版潮流帅气休闲大衣",
            "花花公子外套男秋冬季韩版潮流帅气2020新款青少年中长款加棉风衣",
            "花花公子衬衫男长袖秋冬季韩版潮流加绒保暖格子衬衣男士秋装上衣",
            "花花公子羽绒服男2020年新款韩版潮流轻薄短款潮牌情侣款冬季外套",
            "花花公子棉衣男韩版潮流冬季工装外套连帽加厚带毛领羽绒棉服棉袄",
            "花花公子冬季加厚羽绒服男中长款2020年新款潮牌保暖男士连帽外套",
            "花花公子羽绒服男2020年新款冬季短款韩版轻薄潮牌情侣款冬装外套",
            "花花公子长袖t恤男秋冬款潮流加绒秋衣打底衫卫衣男士秋装上衣服"
    };
    // 声明一个手机商品的价格数组
    private static float[] mPriceArray = {199, 299, 99, 599, 405, 399,150,160};
    // 声明一个手机商品的小图数组
    private static int[] mThumbArray = {
            R.drawable.zc_s, R.drawable.fy_s, R.drawable.gz_s,
            R.drawable.yrf_s, R.drawable.my_s, R.drawable.lmwt_s, R.drawable.wt_s, R.drawable.t_s
    };
    // 声明一个手机商品的大图数组
    private static int[] mPicArray = {
            R.drawable.zc, R.drawable.fy, R.drawable.gz,
            R.drawable.yrf, R.drawable.my_s, R.drawable.lmwt,
            R.drawable.wt, R.drawable.t_s
    };

    // 获取默认的手机信息列表
    public static ArrayList<GoodsInfo> getDefaultList() {
        ArrayList<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();
        for (int i = 0; i < mNameArray.length; i++) {
            GoodsInfo info = new GoodsInfo();
            info.name = mNameArray[i];
            info.desc = mDescArray[i];
            info.price = mPriceArray[i];
            info.thumb = mThumbArray[i];
            info.pic = mPicArray[i];
            goodsList.add(info);
        }
        return goodsList;
    }
}
