package com.bonc.staff.controller;

import com.bonc.staff.service.ControlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xukj
 */
@Slf4j
@RestController
public class IndexController {

    @Autowired
    ControlService controlService;

    @RequestMapping("/")
    public String index() {
        return "hello~";
    }

    @RequestMapping("/run")
    public String run() {
        int count = controlService.build();
        return "本次执行导入人员人数：" + count;
    }
}
