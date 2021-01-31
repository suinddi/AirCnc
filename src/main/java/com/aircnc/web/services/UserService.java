package com.aircnc.web.services;

import com.aircnc.web.daos.UserDao;
import com.aircnc.web.enums.UserLoginResult;
import com.aircnc.web.enums.UserRegisterResult;
import com.aircnc.web.vos.LoginVo;
import com.aircnc.web.vos.RegisterVo;
import com.aircnc.web.vos.UserVo;
import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class UserService {

    private final DataSource dataSource;
    private final UserDao userDao;

    @Autowired
    public UserService(DataSource dataSource, UserDao userDao) {
        this.dataSource = dataSource;
        this.userDao = userDao;
    }

    // 회원의 로그인에 관련된 서비스 로직 구현
    // Session 은 request 에서 얻는다
    public UserLoginResult login(LoginVo loginVo, HttpSession session)
            throws SQLException {  // VO 에서 간결하게 정리된 loginVo 를 가져옴. 반환타입은 UserLoginResult
        try (Connection connection = this.dataSource.getConnection()) {
            UserVo userVo = this.userDao.selectUser(connection, loginVo);
            if (userVo == null) {
                return UserLoginResult.FAILURE;
            } else {
                session.setAttribute("UserVo", userVo);
                return UserLoginResult.SUCCESS;
            }
        }
    }

    // Connection 을 Service 에서 사용한 이유 : sql 문을 여러번 사용하지 않기 위해
    public UserRegisterResult register(RegisterVo registerVo) throws SQLException {
        try (Connection connection = this.dataSource.getConnection()) {
            if (this.userDao.isUserEmailDefined(connection, registerVo.getEmail())) {
                return UserRegisterResult.EMAIL_DUPLICATE;
            } else if (this.userDao.isUserNicknameDefined(connection, registerVo.getNickname())) {
                return UserRegisterResult.NICKNAME_DUPLICATE;
            } else {
                boolean success = this.userDao.insertUser(connection, registerVo);
                return success ?
                        UserRegisterResult.SUCCESS :
                        UserRegisterResult.FAILURE;

            }
        }
    }
}
