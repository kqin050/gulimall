package com.atguigu.gulimall.product.dao;

import com.atguigu.gulimall.product.entity.AttrEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品属性
 * 
 * @author kunqin
 * @email messiakun@foxmail.com
 * @date 2021-11-15 16:05:05
 */
@Mapper
public interface AttrDao extends BaseMapper<AttrEntity> {
	
}
