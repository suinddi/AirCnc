package com.aircnc.web.vos;

// 정규화 할 필요가 없다.
public class UserVo {
    private final String email;
    private final String name;
    private final String nickName;
    private final String contact;
    private final String address;
    private final String birth;

    public UserVo(String email, String name, String nickName, String contact, String address, String birth) {
        this.email = email;
        this.name = name;
        this.nickName = nickName;
        this.contact = contact;
        this.address = address;
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getNickName() {
        return nickName;
    }

    public String getContact() {
        return contact;
    }

    public String getAddress() {
        return address;
    }

    public String getBirth() {
        return birth;
    }
}
