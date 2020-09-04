package com.spring.boot.demo.webflux.controller;

import com.spring.boot.demo.webflux.convert.UserConvert;
import com.spring.boot.demo.webflux.dto.UserAddDTO;
import com.spring.boot.demo.webflux.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * </p>
 *
 * @author wangJun
 * @version V1.0.0
 * @since 2020/09/04
 */
@RestController
@RequestMapping("/user")
public class DemoController {

    private static Map<Long, UserVO> userVOMap = new HashMap<>();

    static {
        userVOMap.put(1001L, new UserVO("1001", "bear", "wangJun"));
        userVOMap.put(1002L, new UserVO("1002", "bearPot", "wangJun"));
        userVOMap.put(1003L, new UserVO("1003", "bearPotMan", "wangJun"));
    }

    @Autowired
    private UserConvert userConvert;

    /**
     * 查询所有用户
     *
     * @return
     */
    @GetMapping("/list")
    public Flux<UserVO> list() {
        return Flux.fromIterable(userVOMap.values());
    }

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public Mono<UserVO> get(@PathVariable("userId") String userId) {
        return Mono.just(userVOMap.get(Long.parseLong(userId)));
    }

    /**
     * 添加用户
     *
     * @param addDTO
     * @return
     */
    @PostMapping("/add")
    public Mono<UserVO> add(@RequestBody UserAddDTO addDTO) {
        userVOMap.put(Long.parseLong(addDTO.getId()), userConvert.convert(addDTO));
        return Mono.just(userVOMap.get(Long.parseLong(addDTO.getId())));
    }

}
