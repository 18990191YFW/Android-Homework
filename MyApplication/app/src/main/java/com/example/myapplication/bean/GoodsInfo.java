package com.example.myapplication.bean;

import com.example.myapplication.R;

import java.util.ArrayList;

public class GoodsInfo {
    public long rowid; // 行号
    public int sn; // 序号
    public String name; // 名称
    public String desc1; // 描述
    public float price; // 价格
    public String thumb_path; // 小图的保存路径
    public String pic_path; // 大图的保存路径
    public int thumb; // 小图的资源编号
    public int pic; // 大图的资源编号

    public int pic_id;
    public String title;
    public boolean bPressed;
    public int id;
    private static int seq = 0;
    public String desc;
    public GoodsInfo(int pic_id, String title, String desc) {
        rowid = 0L;
        sn = 0;
        name = "";
        desc1 = "";
        price = 0;
        thumb_path = "";
        pic_path = "";
        thumb = 0;
        pic = 0;
        this.pic_id = pic_id;
        this.title = title;
        this.desc = desc;
        this.bPressed = false;
        this.id = this.seq;
        this.seq++;
    }

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

    public GoodsInfo() {
        rowid = 0L;
        sn = 0;
        name = "";
        desc1 = "";
        price = 0;
        thumb_path = "";
        pic_path = "";
        thumb = 0;
        pic = 0;
    }

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
    private static int[] gridImageArray = {R.drawable.pic_01, R.drawable.pic_02, R.drawable.pic_03
            , R.drawable.pic_04, R.drawable.pic_05, R.drawable.pic_06, R.drawable.pic_07
            , R.drawable.pic_08, R.drawable.pic_09, R.drawable.pic_10};
    private static String[] gridTitleArray = {"商场", "超市", "百货", "便利店",
            "地摊", "食杂店", "饭店", "餐厅", "会所", "菜市场"};

    public static ArrayList<GoodsInfo> getDefaultGrid() {
        ArrayList<GoodsInfo> gridArray = new ArrayList<GoodsInfo>();
        for (int i = 0; i < gridImageArray.length; i++) {
            gridArray.add(new GoodsInfo(gridImageArray[i], gridTitleArray[i], null));
        }
        return gridArray;
    }
    private static int[] stagImageArray = {R.drawable.skirt01, R.drawable.skirt02, R.drawable.skirt03
            , R.drawable.skirt04, R.drawable.skirt05, R.drawable.skirt06, R.drawable.skirt07
            , R.drawable.skirt08, R.drawable.skirt09, R.drawable.skirt10, R.drawable.skirt11
            , R.drawable.skirt12, R.drawable.skirt13, R.drawable.skirt14, R.drawable.skirt15
            , R.drawable.skirt16, R.drawable.skirt17, R.drawable.skirt18, R.drawable.skirt19
            , R.drawable.skirt20, R.drawable.skirt21, R.drawable.skirt22, R.drawable.skirt23};
    private static String[] stagTitleArray = {"促销价", "惊爆价", "跳楼价", "白菜价", "清仓价", "割肉价",
            "实惠价", "一口价", "满意价", "打折价", "腰斩价", "无人问津", "算了吧", "大声点",
            "嘘嘘", "嗯嗯", "呼呼", "呵呵", "哈哈", "嘿嘿", "嘻嘻", "嗷嗷", "喔喔"};

    public static ArrayList<GoodsInfo> getDefaultStag() {

        ArrayList<GoodsInfo> stagArray = new ArrayList<GoodsInfo>();
        for (int i = 0; i < stagImageArray.length; i++) {
            stagArray.add(new GoodsInfo(stagImageArray[i], stagTitleArray[i], null));
        }
        return stagArray;
    }
}
