package com.example.blog.app.apis.services.impl;

import com.example.blog.app.apis.entities.User;
import com.example.blog.app.apis.execptions.ResourseNotFoundExecption;
import com.example.blog.app.apis.payloads.UserDto;
import com.example.blog.app.apis.repositories.UserRepo;
import com.example.blog.app.apis.services.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoToUser(userDto);
        User savedUser = userRepo.save(user);
        return userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {

//        video 10 / used my technique
        User user =  userRepo.findById(id)
                .orElseThrow(()-> new ResourseNotFoundExecption("user","id",id));

        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());

        User savedUser = userRepo.save(user);

        return userToDto(savedUser);
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user = userRepo.findById(id)
                .orElseThrow(()-> new ResourseNotFoundExecption("user","id",id));

        return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> allUser = userRepo.findAll();
        List<UserDto> users = new ArrayList<>();
        for(User i : allUser){
            users.add(userToDto(i));
        }
        return users;
    }

    @Override
    public void deleteUser(Integer id) {
        User user = userRepo.findById(id)
                .orElseThrow(()-> new ResourseNotFoundExecption("user","id",id));

        userRepo.delete(user);
    }

    User dtoToUser (UserDto userDto){
        User user = new User();
        user = modelMapper.map(userDto, User.class);
//        user.setName(userDto.getName());
//        user.setPassword(userDto.getPassword());
//        user.setEmail(userDto.getEmail());
//        user.setAbout(userDto.getAbout());
//        user.setId(userDto.getId());
        return user;
    }

    UserDto userToDto(User user){
        UserDto userDto = new UserDto();
        userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }
}
