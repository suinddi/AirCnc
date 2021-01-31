package com.aircnc.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RootController {
    // http://127.0.0.1:18080/
    @RequestMapping(value = "/")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        // prefix : /WEB-INF/jsp/
        // suffix : .jsp
        // return : /WEB-INF/jsp/index.jsp
        request.setAttribute("Name", "Park");
        return "index";
    }
}