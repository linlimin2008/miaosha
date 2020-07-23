package com.zheliban.miaosha.redis;

/**
 * @功能:
 * @项目名:miaosha_2
 * @作者:0cm
 * @日期:2020/7/155:31 下午
 */
public class GoodsKey extends BasePrefix{

    public GoodsKey(int exporeSeconds,String prefix) {
        super(exporeSeconds,prefix);
    }
    public static GoodsKey getGoodsList = new GoodsKey(60,"gl");
    public static GoodsKey getGoodsDetail = new GoodsKey(60,"gd");
    public static GoodsKey getMiaoshaGoodsStock = new GoodsKey(0,"gs");
}
