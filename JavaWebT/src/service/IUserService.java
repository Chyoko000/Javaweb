package service;

import com.situ.web.pojo.User;

public interface IUserService {
    User login(String name, String password);
}