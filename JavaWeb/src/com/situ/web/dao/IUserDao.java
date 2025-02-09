package com.situ.web.dao;

import com.situ.web.pojo.User;

public interface IUserDao {
    User login(String name, String password);
}
