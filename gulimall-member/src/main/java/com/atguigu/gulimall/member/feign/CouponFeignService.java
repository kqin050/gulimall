package com.atguigu.gulimall.member.feign;

import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kqin050_uottawa
 * @create 2021-11-16 21:17
 */
@FeignClient("gulimall-coupon")//告诉cloud这是个远程服务接口
public interface CouponFeignService {
    @RequestMapping("coupon/coupon/member/list")//指明调用哪个请求
    public R membercoupons();
}
