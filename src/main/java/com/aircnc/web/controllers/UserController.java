package com.aircnc.web.controllers;

import com.aircnc.web.enums.UserLoginResult;
import com.aircnc.web.enums.UserRegisterResult;
import com.aircnc.web.services.UserService;
import com.aircnc.web.vos.LoginVo;
import com.aircnc.web.vos.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping(value = "/user", method = RequestMethod.POST)  //POST로 보내도록 막아줌
public class UserController {
    private final UserService userService; // 'userService 는 객체화 되었다' 로 간주

    @Autowired //전달인자 (userService 를 스프링이 자동으로 객체화)
    public UserController(UserService userService, DataSource dataSource) {
        this.userService = userService;
    }

    @RequestMapping(value = "login")
    public void login(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam(name = "email", defaultValue = "") String email,
                      @RequestParam(name = "password", defaultValue = "") String password) throws IOException, SQLException {
        //response.getWriter().print("Email : " + email);
        //response.getWriter().print("Password : " + password);

        //Controller -> loginVo (email, password) 를 가지고 정규화 - > (끝난 후) Service
        LoginVo loginVo = new LoginVo(email, password);
        if (!loginVo.isNormalized()) {
            // 정규화 실패 : 이메일 혹은 비밀번호가 요구하는 형식이 아님. JavaScript 를 고의로 우회한 것으로 봐도 됨으로 ban 때려도 ok
            response.getWriter().print("NORMALIZATION_FAILURE");
        } else {
            // 정규화 성공
            UserLoginResult userLoginResult = this.userService.login(loginVo, request.getSession());
            if (userLoginResult == userLoginResult.SUCCESS) {
                response.getWriter().print("LOGIN_SUCCESS");
            } else {
                response.getWriter().print("LOGIN_FAILURE");
            }
        }
    }

    @RequestMapping(value = "/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("UserVo", null);
    }

    @RequestMapping(value = "/register")
    public void register(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam(name = "email", defaultValue = "") String email,
                         @RequestParam(name = "password", defaultValue = "") String password,
                         @RequestParam(name = "name", defaultValue = "") String name,
                         @RequestParam(name = "nickname", defaultValue = "") String nickname,
                         @RequestParam(name = "contact", defaultValue = "") String contact,
                         @RequestParam(name = "address", defaultValue = "") String address,
                         @RequestParam(name = "birth", defaultValue = "") String birth) throws IOException, SQLException {

        RegisterVo registerVo = new RegisterVo(email,password,name,nickname,contact,address,birth);
        if (!registerVo.isNormalized()) {
            response.getWriter().print("NORMALIZATION_FAILURE");
        } else {
            UserRegisterResult userRegisterResult = this.userService.register(registerVo);
            response.getWriter().print(userRegisterResult.name());
        }
    }
}
