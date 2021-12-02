package com.atguigu.gulimall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.product.dao.CategoryDao;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;
//ServiceImpl加入了CategoryDao泛型的实现
//ServiceImpl里面注入了protected M baseMapper;这个对象，即泛型指定的mapper，所以CategoryDAO已经注入了
//操作CategoryEntity实体类对应的表

@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        //1、查出所有分类，通过DAO，查询

        List<CategoryEntity> entities = baseMapper.selectList(null);
        //2、组装成父子的属性结构
        //(1)找到所有一级分类
        List<CategoryEntity> level1Menus = entities.stream().filter((categoryEntity) -> {//categoryEntity 要过滤的元素
            return categoryEntity.getParentCid() == 0;//0即为一级分类
        }).map((menu->{//menu就是个局部变量，跟for循环里的i一样
            menu.setChildren( getChildren(menu,entities));//找到当前菜单的子分类设置/保存进去，这一步改变了菜单
            return menu;//返回当前菜单
        })).sorted((menu1,menu2)->{
            return (menu1.getSort()==null?0:menu1.getSort())- (menu2.getSort()==null?0:menu2.getSort());
        }).collect(Collectors.toList());//把这些1级分类收集成一个集合
        return level1Menus;
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {
        //TODO 1、检查当前删除的菜单，是否被别的地方引用
        //批量删除
        baseMapper.deleteBatchIds(asList);
    }
    //最终效果[2,25,225]
    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();
        List<Long> parentPath = findParentPath(catelogId,paths);
        Collections.reverse(parentPath);
        return parentPath.toArray(new Long[parentPath.size()]);
    }
    private List<Long> findParentPath(Long catelogId,List<Long> paths){
        //收集当前节点id
        paths.add(catelogId);
        CategoryEntity byId = this.getById(catelogId);//查出当前分类的id
        if(byId.getParentCid() != 0){
            findParentPath(byId.getParentCid(),paths);
        }
        return  paths;
    }

    //写一个递归的方法，找到每一个菜单的子菜单,//获取某一个菜单的子菜单，root为当前菜单
    private List<CategoryEntity> getChildren(CategoryEntity root,List<CategoryEntity> all){
        List<CategoryEntity> children = all.stream().filter(categoryEntity -> {
            return categoryEntity.getParentCid() == root.getCatId();//该过滤方法实现，若：当前菜单的父id等于指定的root菜单的id，说明当前菜单是root的子id，
        }).map(categoryEntity -> {
            //1、找到子菜单，递归找
            categoryEntity.setChildren(getChildren(categoryEntity,all));
            return categoryEntity;
        }).sorted((menu1,menu2)->{
            //2、菜单的排序
            return (menu1.getSort()==null?0:menu1.getSort())- (menu2.getSort()==null?0:menu2.getSort());
        }).collect(Collectors.toList());//把过滤得到的菜单收集起来，但是每一个子菜单可能还会有子菜单，这些菜单还需要排序

        return children;

    }

}