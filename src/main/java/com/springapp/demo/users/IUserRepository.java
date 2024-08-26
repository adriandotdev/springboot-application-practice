package com.springapp.demo.users;

import java.util.Optional;

public interface IUserRepository {

    public Optional<User> findUserByUsername(String username);
}
