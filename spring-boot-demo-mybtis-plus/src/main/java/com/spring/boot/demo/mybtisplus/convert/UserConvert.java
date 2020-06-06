package com.spring.boot.demo.mybtisplus.convert;

import com.spring.boot.demo.mybtisplus.dto.UserDTO;
import com.spring.boot.demo.mybtisplus.entity.MpUser;
import com.spring.boot.demo.mybtisplus.vo.UserVO;
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
    UserVO convert(MpUser mpUser);

    MpUser convert(UserDTO userDTO);

}
