package com.example.opencarwash.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class PageViewController {
    @RequestMapping(value = { "/", "/{x:[\\w\\-]+}", "/(?!api).*|/**" })
    @ResponseBody
    public ModelAndView loginPage(){

        return new ModelAndView("index");
    }
}