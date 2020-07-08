package com.zheliban.miaosha.domain;

/**
 * @功能:
 * @项目名:miaosha_2
 * @作者:0cm
 * @日期:2020/7/87:24 下午
 */
public class MiaoshaOrder {
    private Long id;
    private Long userld;
    private Long orderld;
    private Long goodsld;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserld() {
        return userld;
    }

    public void setUserld(Long userld) {
        this.userld = userld;
    }

    public Long getOrderld() {
        return orderld;
    }

    public void setOrderld(Long orderld) {
        this.orderld = orderld;
    }

    public Long getGoodsld() {
        return goodsld;
    }

    public void setGoodsld(Long goodsld) {
        this.goodsld = goodsld;
    }
}
