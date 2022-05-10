package com.userservice.service;

import com.userservice.entity.Login;
import com.userservice.entity.User;
import com.userservice.repository.LoginRepository;
import com.userservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginRepository loginRepository;


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User getUserByName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User saveUser(User user) {
        user.setActive(true);
        return userRepository.save(user);
    }

    @Override
    public String doLogin(Login login) {

        //checking if user has been registered and active.
        String userName = login.getUserName();
        String password = login.getPassword();
        User result = userRepository.findByUserName(userName);
        if (result == null) {
            logger.info("User is not registered " + this.getClass().getName());
            return "User is not registered";
        }
        if (result.getActive() == false) {
            logger.info("User is not active " + this.getClass().getName());
            return "User is not active";
        }
        if (!result.getPassword().equals(password)) {
            logger.info("Password Mismatch " + this.getClass().getName());
            return "Password Mismatch";
        }
        //checking if user has already logged in, change the login time then.
        Login _login = loginRepository.findByUserName(userName);

        if (_login != null && _login.isLogin() == true) {
            //Setting the re-logging time and making logout as null
            _login.setLoginTime(Instant.now().toString());
            _login.setLogoutTime("");
            loginRepository.save(_login);
            logger.info("User is already logged In" + this.getClass().getName());
            return "User is already logged In";
        }

        //user never logged in, first time logging in, creating a fresh record.
        login.setLogin(true);
        login.setLoginTime(Instant.now().toString());
        login.setLogoutTime("");
        loginRepository.save(login);
        logger.info("Login Successful" + this.getClass().getName());
        return "Login Successful";
    }

    @Override
    public String doLogout(Login login) {
        String userName = login.getUserName();
        String password = login.getPassword();
        Login _login = loginRepository.findByUserName(userName);
        if (_login == null) {
            return "User has not logged in!";
        } else {
            _login.setLogin(false);
            loginRepository.save(_login);
            return "Logout Successfully";
        }
    }

}
