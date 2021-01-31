package com.aircnc.web.vos;

import com.aircnc.utility.Sha512;

public class LoginVo {
    private static final String EMAIL_REGEX = "^(?=.{8,100}$)(?!.*[\\-]{2,}.*$)(?!.*[_]{2,}.*$)(?!.*[.]{2,}.*$)([0-9a-zA-Z][0-9a-zA-Z\\-_.]*[0-9a-zA-Z])@([a-z][a-z\\-]*[a-z])\\.([a-z]{2,15})(\\.[a-z]{2})?$";
    private static final String PASSWORD_REGEX = "^([0-9a-zA-Z~!@#$%^&*()\\-_=+\\[{\\]}\\\\|;:'\",<.>/?]{4,100})$";

    private final String email;
    private final String password;
    private final String hashedPassword;

    private boolean isNormalized = false;

    public LoginVo(String email, String password) {
            if (email.matches(LoginVo.EMAIL_REGEX) && password.matches(LoginVo.PASSWORD_REGEX)) {
                // email 과 password 가 정규식 통과를 하면 (둘다 true)
                this.isNormalized = true; // (정규화가 되었고)
                this.email = email;
                this.password = password;
                this.hashedPassword = Sha512.hash(this.password); // password 를 해싱
            } else {  //email 과 password 가 정규식 통과를 못하면 this.isNormalized = false;
            this.email = null;
            this.password = null;
            this.hashedPassword = null;
        }
        // 정규화 실패시 예외 던지는 것 대신 private boolean isNormalized = false;
    }

    public String getEmail() {
        return this.email;
        // 정적변수(static) 에는 this 쓰지 않는다.
    }

    public String getPassword() {
        return this.password;
    }

    public boolean isNormalized() {
        return isNormalized;
        // is로 시작하는 전역변수는 Getter 가 get- 으로 시작하지 않는다.
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
}
