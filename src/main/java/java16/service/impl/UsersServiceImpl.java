package java16.service.impl;

import java16.entitys.User;
import java16.repo.UserRepo;
import java16.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UsersServiceImpl implements UserService {

    private final UserRepo userRepo;

}
