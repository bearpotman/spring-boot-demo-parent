package com.spring.boot.demo.webflux.convert;

import com.spring.boot.demo.webflux.dto.UserAddDTO;
import com.spring.boot.demo.webflux.vo.UserVO;
import org.mapstruct.Mapper;

/**
 * <p>
 * </p>
 *
 * @author wangJun
 * @version V1.0.0
 * @since 2020/09/04
 */
@Mapper(componentModel = "spring")
public interface UserConvert {

    UserVO convert(UserAddDTO addDTO);

}
