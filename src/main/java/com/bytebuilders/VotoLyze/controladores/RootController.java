package com.bytebuilders.VotoLyze.controladores;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class RootController {

    String projectUrl = "http://localhost:8080/ProjectVotoLyze_V6/index.html";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void method(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", projectUrl);
        httpServletResponse.setStatus(302);
    }
}