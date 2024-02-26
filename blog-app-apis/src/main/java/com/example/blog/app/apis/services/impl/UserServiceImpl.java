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
    public UserDto updateUser(UserDto userDto, Integer id) throws ResourseNotFoundExecption {

//        video 10 / used my technique
        User user = null;
        try {
            user = userRepo.getReferenceById(id);
        } catch (ResourseNotFoundExecption e) {
            throw new ResourseNotFoundExecption("user", "Id", id);
        }

        user.setName(user.getName());
        user.setPassword(user.getPassword());
        user.setEmail(user.getEmail());
        user.setAbout(user.getAbout());

        User savedUser = userRepo.save(user);

        return userToDto(savedUser);
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user = null;
        try {
            user = userRepo.getReferenceById(id);
        } catch (ResourseNotFoundExecption e) {
            throw new ResourseNotFoundExecption("user", "Id", id);
        }

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
        User user = null;
        try {
            user = userRepo.getReferenceById(id);
        } catch (ResourseNotFoundExecption e) {
            throw new ResourseNotFoundExecption("user", "Id", id);
        }

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
