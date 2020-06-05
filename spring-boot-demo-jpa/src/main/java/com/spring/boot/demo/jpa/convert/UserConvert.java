package com.spring.boot.demo.jpa.convert;

import com.spring.boot.demo.jpa.entities.User;
import com.spring.boot.demo.jpa.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @Auth wangJun
 * @Version V1.0.1
 * @Description
 * @Date 2020/06/05
 */
@Mapper
public interface UserConvert {

    @Mapping(source = "id", target = "userId")
    UserVO convert(User user);

}
