package com.zheliban.miaosha.redis;

/**
 * @功能:
 * @项目名:miaosha_2
 * @作者:0cm
 * @日期:2020/7/155:31 下午
 */
public class MiaoshaKey extends BasePrefix{

    public MiaoshaKey(String prefix) {
        super(prefix);
    }
    public static MiaoshaKey isGoodsOver = new MiaoshaKey("go");

}
