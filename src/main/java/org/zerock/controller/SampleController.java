package org.zerock.controller;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;
import org.zerock.mapper.TimeMapper;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
    }


    @Setter(onMethod_ = @Autowired)
    private TimeMapper timeMapper;

    @GetMapping("/s1")
    public void sample(Model model) {
        log.info("sample");
        String now = timeMapper.getTime();
        model.addAttribute("now", now);
    }

    @RequestMapping("")
    public void basic() {
        log.info("basic ...................");
    }

    @RequestMapping(value="/basic", method={RequestMethod.GET, RequestMethod.POST})
    public void basicGet() {
        log.info("basic get ...............");
    }

    @RequestMapping(value="/basicOnlyGet")
    public void basicGet2() {
        log.info("basic get only get............");
    }

    @RequestMapping("/ex01")
    public String ex01(SampleDTO dto) {
        log.info("" + dto);
        return "ex01";
    }

    @RequestMapping("/ex02")
    public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
        log.info("name: " + name);
        log.info("age: " + age);
        return "ex02";
    }

    @RequestMapping("/ex02Array")
    public String ex02Array(@RequestParam("ids") String[] ids) {
        log.info("array ids: " + Arrays.toString(ids));

        return "ex02Array";
    }

    @GetMapping("/ex02Bean")
    public String ex02Bean(SampleDTOList list) {
        log.info("list dto: " + list);

        return "ex02Bean";
    }

    @GetMapping("/ex03")
    public String ex03(TodoDTO todo) {
        log.info("todo: " + todo);

        return "ex03";
    }

    public String home(Model model) {
        model.addAttribute("serverTime", new java.util.Date());

        return "home";
    }

}
