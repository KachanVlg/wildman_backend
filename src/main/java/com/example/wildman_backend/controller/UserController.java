package com.example.wildman_backend.controller;


import com.example.wildman_backend.domain.dto.UserDto;
import com.example.wildman_backend.domain.model.user.User;
import com.example.wildman_backend.service.UserService;
import com.example.wildman_backend.util.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final DtoMapper dtoMapper;

    @PostMapping("/api/user/create")
    public UserDto register(@RequestBody UserDto userDto) {
        return dtoMapper.toDto(userService.create(dtoMapper.toModel(userDto,User.class)), UserDto.class);
    }
}
