<%@ page import="com.aircnc.web.vos.UserVo" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html" trimDirectiveWhitespaces="true"%>
<%
    Object userVoObject = session.getAttribute("UserVo");
    UserVo userVo = userVoObject instanceof UserVo ? (UserVo) userVoObject : null;
%>
<!-- trimDirectiveWhitespaces="true" : 자바 코드가 있던 부분(공백) 이 사라진다 -->
<!-- 로그인 성공이면 UserVo 의 userVo 를 집어넣고, 실패시 null 를 집어넣는다 -->
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="숙소, 체험, 장소를 모두 한 곳에서 - 에어씨엔씨">
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <title>숙소, 체험, 장소를 모두 한 곳에서 - 에어씨엔씨</title>
    <link rel="stylesheet" href="resources/stylesheets/dev.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap">
    <script defer src="resources/scripts/dev.js"></script>
</head>
<body class="preload loading">
    <div id="cover" class="body-item cover"></div>
    <div id="loading" class="body-item loading">
        <div class="loading-item icon"></div>
        <div class="loading-item text">잠시만 기다려 주세요</div>
    </div>
    <div id="login" class="body-item login">
        <div class="login-item top">
            <div id="login-top-close" class="top-item close"></div>
            <div class="top-item title">로그인</div>
        </div>
        <div class="login-item body">
            <div class="body-item input"><input id="login-body-input-email" type="email" placeholder="이메일" maxlength="100"></div>
            <div class="body-item input"><input id="login-body-input-password" type="password" placeholder="비밀번호" maxlength="100"></div>
            <div class="body-item link">비밀번호를 잊으셨나요?</div>
            <div id ="login-body-button" class="body-item button">로그인</div>
            <div class="body-item splitted">
                <div class="splitted-item text">에어씨엔씨 계정이 없으세요?</div>
                <div class="splitted-item link">회원가입</div>
            </div>
        </div>
    </div>
    <div id="register" class="body-item register">
        <div class="register-item top">
            <div id="register-top-close" class="top-item close"></div>
            <div class="top-item title">회원가입</div>
        </div>
        <div class="register-item body">
            <div class="body-item input"><input id="register-body-input-email" type="email" placeholder="이메일" maxlength="100"></div>
            <div class="body-item input"><input id="register-body-input-password" type="password" placeholder="비밀번호" maxlength="100"></div>
            <div class="body-item input"><input id="register-body-input-password-check" type="password" placeholder="비밀번호확인" maxlength="100"></div>
            <div class="body-item input"><input id="register-body-input-name" type="text" placeholder="이름" maxlength="100"></div>
            <div class="body-item input"><input id="register-body-input-nickname" type="text" placeholder="닉네임(2자 이상)" maxlength="100"></div>
            <div class="body-item input"><input id="register-body-input-contact" type="text" placeholder="연락처(ex.01012345678)" maxlength="100"></div>
            <div class="body-item input"><input id="register-body-input-address" type="text" placeholder="주소" maxlength="100"></div>
            <div class="body-item input"><input id="register-body-input-birth" type="text" placeholder="생년월일(ex.961021)" maxlength="100"></div>
            <div id ="register-body-button" class="body-item button">회원가입</div>
            <div class="body-item splitted">
                <div class="splitted-item text">이메일/비밀번호 찾기</div>
                <div class="splitted-item link">로그인 하기</div>
            </div>
        </div>
    </div>
    <div id="user-menu" class="body-item user-menu">
        <% if (userVo == null) { %>
        <div id="user-menu-register" class="user-menu-item menu strong">회원 가입</div>
        <div id="user-menu-login" class="user-menu-item menu">로그인</div>
        <% } else { %>
        <div class="user-menu-item menu"><strong><%= userVo.getNickName() %></strong> 님 환영합니다.</div>
        <div class="user-menu-item menu">개인 정보 수정</div>
        <div id="user-menu-logout" class="user-menu-item menu">로그아웃</div>
        <% } %>
        <div class="user-menu-item splitter"></div>
        <div class="user-menu-item menu">숙소 호스트 되기</div>
        <div class="user-menu-item menu">체험 호스팅하기</div>
        <div class="user-menu-item menu">도움말</div>
    </div>
    <div class="body-item warning">
        <a class="warning-item text">에어씨앤씨의 코로나19 대응 방안에 대한 최신 정보를 확인하세요.</a>
    </div>
    <header class="body-item header size-limited">
        <div class="header-item top">
            <div class="top-item logo">
                <img class="logo-item image" src="resources/images/aircnc_logo.png">
            </div>
            <nav class="top-item menu">
                <div class="menu-item selected">
                    <a>숙소</a>
                    <div class="menu-item-pointer"></div>
                </div>
                <div class="menu-item">
                    <a>고객문의</a>
                    <div class="menu-item-pointer"></div>
                </div>
            </nav>
            <div class="top-item user">
                <div id="header-top-user-menu" class="user-item menu">
                    <div class="menu-item icon"></div>
                    <div class="menu-item shape"></div>
                </div>
            </div>
        </div>
        <div class="header-item book">
            <div class="book-item container">
                <div class="container-item input">
                    <div class="input-item title">위치</div>
                    <div class="input-item text">어디로 여행가세요?</div>
                </div>
                <div class="container-item input">
                    <div class="input-item title">체크인</div>
                    <div class="input-item text">날짜 추가</div>
                </div>
                <div class="container-item input">
                    <div class="input-item title">체크아웃</div>
                    <div class="input-item text">날짜 추가</div>
                </div>
                <div class="container-item input">
                    <div class="input-item title">인원</div>
                    <div class="input-item text">게스트 추가</div>
                </div>
                <div class="container-item search-button">
                    <div class="search-button-item image"></div>
                    <div class="search-button-item text">검색</div>
                </div>
            </div>
        </div>
    </header>
    <div class="body-item landscape size-limited">
        <div class="landscape-item bg"></div>
        <div class="landscape-item content">
            <div class="content-item title">가까운 곳에서 즐기는<br>색다른 여행</div>
            <div class="content-item text">가까운 곳의 숨겨진 아름다움을 발견하는 색다른<br>휴가를 즐겨보세요.</div>
            <div class="content-item link">가까운 여행지 둘러보기</div>
        </div>
    </div>
    <div class="body-item feature size-limited">
        <div class="feature-item">
            <div class="feature-item-image"></div>
            <div class="feature-item-text">
                <a class="feature-item-text-title">온라인 체험</a>
                <a class="feature-item-text-description">세계 각지의 호스트가 진행하고 모두 함께 즐기는 독특한 액티비티</a>
            </div>
        </div>
        <div class="feature-item">
            <div class="feature-item-image"></div>
            <div class="feature-item-text">
                <a class="feature-item-text-title">독특한 공간</a>
                <a class="feature-item-text-description">단순한 숙소 이상의 특별함이 담긴 공간</a>
            </div>
        </div>
        <div class="feature-item">
            <div class="feature-item-image"></div>
            <div class="feature-item-text">
                <a class="feature-item-text-title">집 전체</a>
                <a class="feature-item-text-description">일행만을 위한 편안한 공간에서 친구 및 가족과 오붓한 시간을 보내세요.일행만을 위한 편안한 공간에서 친구 및 가족과 오붓한 시간을 보내세요.</a>
            </div>
        </div>
    </div>
    <div class="body-item experience size-full">
        <div class="experience-item container">
            <div class="container-item top">
                <div class="top-item left">
                    <a class="left-item title">브로드웨이 온라인 체험</a>
                    <a class="left-item content">슬기로운 집콕 생활을 위한 색다른 방법 - 실시간으로 진행되는 인터랙티브 브로드웨이 공연에 참여하고 출연진과 대화를 나눠보세요.</a>
                </div>
                <div class="top-item stretch"></div>
                <div class="top-item right">
                    <div class="right-item see-all">모두 둘러보기</div>
                </div>
            </div>
            <div class="container-item grid">
                <div class="grid-item one">
                    <div class="grid-item-image"></div>
                    <div class="grid-item-text">라스베이거스 댄서의 하루</div>
                </div>
                <div class="grid-item two">
                    <div class="grid-item-image"></div>
                    <div class="grid-item-text">아이샤 잭슨과 함께하는 사랑, 빛, 치유의 저녁</div>
                </div>
                <div class="grid-item three">
                    <div class="grid-item-image"></div>
                    <div class="grid-item-text">마술과 독심술을 즐기는 밤</div>
                </div>
                <div class="grid-item four">
                    <div class="grid-item-image"></div>
                    <div class="grid-item-text">라이브 뮤지컬 참여하기</div>
                </div>
            </div>
        </div>
    </div>
    <footer class="body-item footer size-full">
        <div class="footer-item container">
            <div class="container-item grid">
                <div class="grid-item">
                    <ul class="grid-item-menu">
                        <li>소개</li>
                        <li>에어비앤비 이용 방법</li>
                        <li>뉴스룸</li>
                        <li>에어비앤비 플러스</li>
                        <li>에어비앤비 Luxe</li>
                        <li>호텔투나잇</li>
                    </ul>
                </div>
                <div class="grid-item">
                    <ul class="grid-item-menu">
                        <li>커뮤니티</li>
                        <li>다양성 및 소속감</li>
                        <li>접근성</li>
                        <li>에어비앤비 어소시에이트</li>
                    </ul>
                </div>
                <div class="grid-item">
                    <ul class="grid-item-menu">
                        <li>호스팅하기</li>
                        <li>숙소 호스팅</li>
                        <li>온라인 체험 호스팅하기</li>
                        <li>체험 호스팅하기</li>
                        <li>브라이언 체스키 CEO의 메세지</li>
                        <li>책임감 있는 호스팅</li>
                    </ul>
                </div>
                <div class="grid-item">
                    <ul class="grid-item-menu">
                        <li>에어비앤비 지원</li>
                        <li>코로나19 관련 업데이트</li>
                        <li>도움말 센터</li>
                        <li>예약 취소 옵션</li>
                    </ul>
                </div>
            </div>
            <div class="container-item copy">
                <div class="copy-item left">
                    <div class="left-item copy">&copy; 2020 Aircnc, Inc. All rights reserved.</div>
                    <div class="left-item disclaimer">
                        <div class="disclaimer-item text">개인정보 처리방침</div>
                        <div class="disclaimer-item splitter"></div>
                        <div class="disclaimer-item text">이용약관</div>
                        <div class="disclaimer-item splitter"></div>
                        <div class="disclaimer-item text">사이트맵</div>
                    </div>
                </div>
                <div class="copy-item stretch"></div>
                <div class="copy-item right">
                    <a class="right-item instagram" href="https://www.instagram.com" target="_blank"></a>
                    <a class="right-item facebook" href="https://www.facebook.com" target="_blank"></a>
                    <a class="right-item twitter" href="https://www.twitter.com" target="_blank"></a>
                </div>
            </div>
        </div>
    </footer>
</body>
</html>