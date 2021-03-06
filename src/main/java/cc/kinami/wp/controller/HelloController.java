package cc.kinami.wp.controller;

import cc.kinami.wp.dao.co.SelectRecordCO;
import cc.kinami.wp.model.dto.CommonResultDTO;
import cc.kinami.wp.model.po.AbstractRecordPO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public CommonResultDTO<String> queryRecordList() {

        return new CommonResultDTO<>(400, "OK", "hello");
    }
}
