package com.aircnc.web.daos;

import com.aircnc.web.vos.LoginVo;
import com.aircnc.web.vos.RegisterVo;
import com.aircnc.web.vos.UserVo;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {
    // 전달된 LoginVo 의 정보와 일치하는 회원의 정보를 넘겨줌
    // UserService 에서 DataSource 를 이용해서 Connection 전달
    public UserVo selectUser(Connection connection, LoginVo loginVo) throws SQLException {
        UserVo userVo = null;  //로그인 실패시 null 반환
        // try-With-Resource 라는 구문을 이용해서 자동으로 close
        // 'preparedStatement'(connection 에 종속, new 못 만듬) 는 try-catch 구문이 끝나면 자동으로 close (메모리 절약)

        String query = " " +
                "SELECT  `user_name`  AS `userName`,\n" +
                "\t\t`user_nickname`  AS `userNickname`,\n" +
                "\t\t`user_contact`   AS `userContact`,\n" +
                "\t\t`user_address`   AS `userAddress`,\n" +
                "\t\t`user_birth`     AS `userBirth`\n" +
                "FROM `member`.`users` \n" +
                "WHERE `user_email`=? AND `user_password`=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // email, password 가 일치하지 않는다면 결과적으로 null 이 반환
            preparedStatement.setString(1, loginVo.getEmail());  // 첫번째 ? 에 email 넣음
            preparedStatement.setString(2, loginVo.getHashedPassword());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {  // preparedStatement 에 지정된 query 를 실행
                // 2중 try : 오류 발생시 상위 catch(아래 구문) 에서 예외 처리(catch 지움)  ->  가장 상위 메서드에서 예외 전담 (throws SQLException)
                // java 는 몇 행이 select 되었는지 알 수 없다 - > while 사용
                while (resultSet.next()) {  // next() 가 false 일 때 while 문을 빠져나감
                    // userVo 객체화
                    userVo = new UserVo(
                            loginVo.getEmail(),
                            resultSet.getNString("userName"),
                            resultSet.getNString("userNickname"),
                            resultSet.getNString("userContact"),
                            resultSet.getNString("userAddress"),
                            resultSet.getNString("userBirth")
                    );
                }
            }
        }
        return userVo;
    }


    public boolean isUserEmailDefined(Connection connection, String email) throws SQLException {
        boolean exist = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT COUNT(`user_index`) AS `count` FROM `member`.`users` WHERE `user_email`=?")) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();
                    exist = resultSet.getInt("count") > 0;
            }
            return exist;
        }
    }

    public boolean isUserNicknameDefined(Connection connection, String nickname) throws SQLException {
        boolean exist = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT COUNT(`user_index`) AS `count` FROM `member`.`users` WHERE `user_nickname`=?")) {
            preparedStatement.setString(1, nickname);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                exist = resultSet.getInt("count") > 0;
            }
            return exist;
        }
    }

    public boolean insertUser(Connection connection, RegisterVo registerVo) throws SQLException {

        String query = "" +
                "INSERT INTO `member`.`users` (" +
                "`user_email`," +
                "`user_password`," +
                "`user_name`, " +
                "`user_nickname`," +
                "`user_contact`, " +
                "`user_address`, " +
                "`user_birth`) " +
                "VALUES(?,?,?,?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, registerVo.getEmail());
            preparedStatement.setString(2, registerVo.getHashedPassword());
            preparedStatement.setString(3, registerVo.getName());
            preparedStatement.setString(4, registerVo.getNickname());
            preparedStatement.setString(5, registerVo.getContact());
            preparedStatement.setString(6, registerVo.getAddress());
            preparedStatement.setString(7, registerVo.getBirth());
            preparedStatement.execute();  //select 에만 ResultSet 이 들어감
        }
        return this.isUserEmailDefined(connection, registerVo.getEmail());
    }
}
