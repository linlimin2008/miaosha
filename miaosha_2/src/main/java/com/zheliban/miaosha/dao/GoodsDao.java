package com.zheliban.miaosha.dao;

import com.zheliban.miaosha.domain.Goods;
import com.zheliban.miaosha.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @功能:
 * @项目名:miaosha_2
 * @作者:0cm
 * @日期:2020/7/87:34 下午
 */
@Mapper
public interface GoodsDao {
    @Select("select g.*,mg.stock_count,mg.start_date,mg.end_date,mg.miaosha_price,g.goods_img from miaosha_goods mg left join goods g on mg.goods_id =g.id")
    public List<GoodsVo> listGoodsVo();

    @Select("select g.*,mg.stock_count,mg.start_date,mg.end_date,mg.miaosha_price,g.goods_img from miaosha_goods mg left join goods g on mg.goods_id =g.id where g.id = #{goodsId}")
    public GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);
}
