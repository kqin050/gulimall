package com.atguigu.gulimall.coupon.dao;

import com.atguigu.gulimall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author kunqin
 * @email messiakun@foxmail.com
 * @date 2021-11-15 19:59:55
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
