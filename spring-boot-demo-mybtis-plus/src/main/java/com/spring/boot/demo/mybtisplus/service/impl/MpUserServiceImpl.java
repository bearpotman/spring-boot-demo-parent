package com.spring.boot.demo.mybtisplus.service.impl;

import com.spring.boot.demo.mybtisplus.entity.MpUser;
import com.spring.boot.demo.mybtisplus.mapper.MpUserMapper;
import com.spring.boot.demo.mybtisplus.service.IMpUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangJun
 * @since 2020-06-06
 */
@Service
public class MpUserServiceImpl extends ServiceImpl<MpUserMapper, MpUser> implements IMpUserService {

}
