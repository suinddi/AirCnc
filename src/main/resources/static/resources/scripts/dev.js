function domContentLoaded(){
    setTimeout(function(){
        window.document.body.classList.remove("preload");
        let loading = window.document.body.querySelector("#loading");
        loading.classList.add("visible");

        attachEvents();
    }, 100);
}

function onLoad(){
    setTimeout(function(){
        let loading = window.document.body.querySelector("#loading");
        let cover = window.document.body.querySelector("#cover");
        loading.classList.remove("visible");
        cover.classList.add("hidden");

        window.document.body.classList.remove("loading");
    }, 1000);
}

function attachEvents() {
    let headerTopUserMenu = window.document.body.querySelector("#header-top-user-menu");
    headerTopUserMenu.addEventListener("click", function(){
        let userMenu = window.document.body.querySelector("#user-menu");
        let headerTopUserMenuRect = headerTopUserMenu.getBoundingClientRect();
        userMenu.style.top = `${headerTopUserMenuRect.top + headerTopUserMenuRect.height + 16}px`;
        userMenu.style.left = `${headerTopUserMenuRect.left + headerTopUserMenuRect.width - userMenu.getBoundingClientRect().width}px`;
        if (userMenu.classList.contains("visible")) {
            userMenu.classList.remove("visible");
        } else {
            userMenu.classList.add("visible");
        }
    });

    let userMenuLogin = window.document.body.querySelector("#user-menu-login");
    if (userMenuLogin!== null) {
        userMenuLogin.addEventListener("click", function(){
            let userMenu = window.document.body.querySelector("#user-menu");
            let login = window.document.body.querySelector("#login");
            let cover = window.document.body.querySelector("#cover");
            userMenu.classList.remove("visible");
            login.classList.add("visible");
            if(!cover.classList.contains("after")){
                cover.classList.add("after");
            }
            if(!window.document.body.classList.contains("scroll-blocked")){
                window.document.body.classList.add("scroll-blocked");
            }
            cover.classList.remove("hidden");

            let loginBodyInputEmail = window.document.body.querySelector("#login-body-input-email");
            let loginBodyInputPassword = window.document.body.querySelector("#login-body-input-password");
            loginBodyInputEmail.value = "";
            loginBodyInputPassword.value = "";
            loginBodyInputEmail.focus();
            });
        }

    let userMenuLogout = window.document.body.querySelector("#user-menu-logout");
        if (userMenuLogout !== null) {
            userMenuLogout.addEventListener("click", function() {
            function callback() {
                window.location.reload();
            }
            function fallback() {
                alert("예상치 못한 오류가 발생하였습니다. 잠시 후 다시 시도해주세요.");
            }
            xhr("POST", "/user/logout", callback, fallback);
        });
    }

    let userMenuRegister = window.document.body.querySelector("#user-menu-register");
        if (userMenuRegister != null) {
            userMenuRegister.addEventListener("click", function () {
                let userMenu = window.document.body.querySelector("#user-menu");
                let register = window.document.body.querySelector("#register");
                let cover = window.document.body.querySelector("#cover");
                userMenu.classList.remove("visible");
                register.classList.add("visible");
                if (!cover.classList.contains("after")) {
                    cover.classList.add("after");
                }
                if (!window.document.body.classList.contains("scroll-blocked")) {
                    window.document.body.classList.add("scroll-blocked");
                }
                cover.classList.remove("hidden");

                let registerBodyInputEmail = window.document.body.querySelector("#register-body-input-email");
                let registerBodyInputPassword = window.document.body.querySelector("#register-body-input-password");
                let registerBodyInputPasswordCheck = window.document.body.querySelector("#register-body-input-password-check");
                let registerBodyInputName = window.document.body.querySelector("#register-body-input-name");
                let registerBodyInputNickname = window.document.body.querySelector("#register-body-input-nickname");
                let registerBodyInputContact = window.document.body.querySelector("#register-body-input-contact");
                let registerBodyInputAddress = window.document.body.querySelector("#register-body-input-address");
                let registerBodyInputBirth = window.document.body.querySelector("#register-body-input-birth");
                registerBodyInputEmail.value = "";
                registerBodyInputPassword.value = "";
                registerBodyInputPasswordCheck.value = "";
                registerBodyInputName.value = "";
                registerBodyInputNickname.value = "";
                registerBodyInputContact.value = "";
                registerBodyInputAddress.value = "";
                registerBodyInputBirth.value = "";
                registerBodyInputEmail.focus();
            });
        }

    let loginTopClose = window.document.body.querySelector("#login-top-close");
    loginTopClose.addEventListener("click", function(){
        let login = window.document.body.querySelector("#login");
        let cover = window.document.body.querySelector("#cover");
        login.classList.remove("visible");
        window.document.body.classList.remove("scroll-blocked");
        cover.classList.add("hidden");
    });

    let registerTopClose = window.document.body.querySelector("#register-top-close");
        registerTopClose.addEventListener("click", function () {
            let register = window.document.body.querySelector("#register");
            let cover = window.document.body.querySelector("#cover");
            register.classList.remove("visible");
            window.document.body.classList.remove("scroll-blocked");
            cover.classList.add("hidden");
        });

    //로그인
    let loginBodyButton = window.document.body.querySelector("#login-body-button");
    loginBodyButton.addEventListener("click", function() {
        let loginBodyInputEmail = window.document.body.querySelector("#login-body-input-email");
        let loginBodyInputPassword = window.document.body.querySelector("#login-body-input-password");
        if(loginBodyInputEmail.value === "") {
            alert("이메일을 입력해주세요.");
            loginBodyInputEmail.focus();
        } else if(loginBodyInputPassword.value === "") {
            alert("비밀번호를 입력해주세요.");
            loginBodyInputPassword.focus();
        } else {
            function callback(responseText) {
                if (responseText === "NORMALIZATION_FAILURE") {
                    alert ("올바른 이메일 혹은 비밀번호를 입력해주세요.");
                } else if (responseText === "LOGIN_FAILURE") {
                    alert ("이메일 혹은 비밀번호가 올바르지 않습니다. 다시 한 번 확인해 주세요.");
                } else if (responseText === "LOGIN_SUCCESS") {
                    window.location.reload(); //페이지 새로고침
                }
            }
            function fallback() {
                alert("예상치 못한 오류가 발생하였습니다. 잠시 후 다시 시도해주세요.");
            }

            let formData = new FormData();
            formData.append("email", loginBodyInputEmail.value);   // Controller 에서 받는 RequestParameter 이름과 동일
            formData.append("password", loginBodyInputPassword.value);
            xhr("POST","/user/login",callback, fallback, formData);
        }
    });

    //회원가입
     let registerBodyButton = window.document.body.querySelector("#register-body-button");
     registerBodyButton.addEventListener("click", function () {
          let registerBodyInputEmail = window.document.body.querySelector("#register-body-input-email");
          let registerBodyInputPassword = window.document.body.querySelector("#register-body-input-password");
          let registerBodyInputPasswordCheck= window.document.body.querySelector("#register-body-input-password-check");
          let registerBodyInputName = window.document.body.querySelector("#register-body-input-name");
          let registerBodyInputNickname = window.document.body.querySelector("#register-body-input-nickname");
          let registerBodyInputContact = window.document.body.querySelector("#register-body-input-contact");
          let registerBodyInputAddress = window.document.body.querySelector("#register-body-input-address");
          let registerBodyInputBirth = window.document.body.querySelector("#register-body-input-birth");

          if (registerBodyInputEmail.value === "") {
                alert("이메일을 입력해주세요.");
                loginBodyInputEmail.focus();
          } else if (registerBodyInputPassword.value === "") {
                alert("비밀번호를 입력해주세요.");
                loginBodyInputPassword.focus();
          } else if (registerBodyInputPasswordCheck.value === "") {
                alert("비밀번호를 확인해주세요.");
                registerBodyInputPasswordCheck.focus();
          } else if (registerBodyInputName.value === "") {
                alert("이름을 입력해주세요.");
                registerBodyInputName.focus();
          } else if (registerBodyInputNickname.value === "") {
                alert("닉네임을 입력하세요.");
                registerBodyInputNickname.focus();
          } else if (registerBodyInputContact.value === "") {
                alert("연락처을 입력하세요.");
                registerBodyInputContact.focus();
          } else if (registerBodyInputAddress.value === "") {
                alert("주소를 입력하세요.");
                registerBodyInputAddress.focus();
          } else if (registerBodyInputBirth.value === "") {
                alert("생년월일을 입력하세요.");
                registerBodyInputBirth.focus();
          } else {
                      function callback(responseText) {
                          if (responseText === "NORMALIZATION_FAILURE") {
                              alert ("올바른 값을 입력해주세요.");
                          } else if (responseText === "EMAIL_DUPLICATE") {
                              alert("이미 사용 중인 이메일입니다. ");
                          } else if (responseText === "NICKNAME_DUPLICATE") {
                              alert("이미 사용 중인 닉네임입니다. ");
                          } else if (responseText === "FAILURE") {
                              alert("알 수 없는 이유로 회원가입을 못했습니다. 잠시 후 다시 시도해주세요.");
                          } else if (responseText === "SUCCESS") {
                              window.location.reload(); //페이지 새로고침
                          }
                      }

                      function fallback() {
                          alert("예상치 못한 오류가 발생하였습니다. 잠시 후 다시 시도해주세요.");
                      }

                      let formData = new FormData();
                      formData.append("email", registerBodyInputEmail.value);
                      formData.append("password", registerBodyInputPassword.value);
                      formData.append("name", registerBodyInputName.value);
                      formData.append("nickname", registerBodyInputNickname.value);
                      formData.append("contact", registerBodyInputContact.value);
                      formData.append("address", registerBodyInputAddress.value);
                      formData.append("birth", registerBodyInputBirth.value);
                      xhr("POST","/user/register",callback, fallback, formData);
          } 
     });

}
    // Method : HTTP 요청 메서드 (GET, POST)
    // callback : 성공시 반환하는 값 혹은 함수 / fallback : 실패시 "
    function xhr(method, url, callback, fallback, formData) {
        let xhr = new XMLHttpRequest();
        xhr.open(method,url);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {  // 에러/정상이든 관계없이 끝났다면
                if (xhr.status >= 200 && xhr.status < 300) {
                    // 정상
                    callback(xhr.responseText);
                } else {
                    // 잘못 되었다
                    fallback();
                }
            }
        };
        if (typeof(formData) == "undefined") {  // formData 가 전달이 안되었다면
            xhr.send();
        } else {
            xhr.send(formData);
        }

    }

window.document.addEventListener("DOMContentLoaded", domContentLoaded);

window.addEventListener("load", onLoad);