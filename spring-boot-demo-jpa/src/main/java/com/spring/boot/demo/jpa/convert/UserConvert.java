package com.spring.boot.demo.jpa.convert;

import com.spring.boot.demo.jpa.entities.User;
import com.spring.boot.demo.jpa.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @Auth wangJun
 * @Version V1.0.1
 * @Description
 * @Date 2020/06/05
 */
@Mapper(componentModel = "spring")
public interface UserConvert {

    @Mappings({
            @Mapping(source = "id", target = "userId"),
            @Mapping(source = "createTime", target = "registerTime")
    })
    UserVO convert(User user);

}
