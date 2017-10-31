package com.johnathanmsmith.mvc.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Date:   6/5/13 / 7:58 AM
 * Author: Johnathan Mark Smith
 * Email:  john@johnathanmarksmith.com
 * <p/>
 * Comments:
 * <p/>
 * This is my basic controller for my web app.
 */


@Controller
@RequestMapping("/ask")
class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String getName(@PathVariable String name, ModelMap model) {
        logger.debug("I am in the controller and got user name: " + name);
        model.addAttribute("user", name);
        return "helloworld";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getDisplayDefault(ModelMap model) {
        model.addAttribute("user", "Johnathan Mark Smith");
        return "helloworld";
    }

    @RequestMapping(value = "/user/saveUsers", method = {RequestMethod.POST})
    @ResponseBody
    public void saveUsers(@RequestBody List<User> users) {  //这边如果是通过表单提交的则不能加RequestBody
        logger.debug("saveUser() is executed!");
        for (User u : users) {
            System.out.println(u.getUserName() + ":" + u.getAddress());
        }
    }

    @RequestMapping(value = "/user/saveUser", method = {RequestMethod.POST})
    @ResponseBody
    public void saveUser(@RequestBody User u) {  //这边如果是通过表单提交的则不能加RequestBody
        logger.debug("saveUser() is executed!");
        System.out.println(u.getUserName() + ":" + u.getAddress());
    }
}
