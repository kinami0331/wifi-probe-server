package cc.kinami.wp.controller;

import cc.kinami.wp.dao.co.SelectRecordCO;
import cc.kinami.wp.model.dto.CommonResultDTO;
import cc.kinami.wp.model.dto.PostDTO;
import cc.kinami.wp.model.po.AbstractRecordPO;
import cc.kinami.wp.service.DataService;
import cc.kinami.wp.websocket.MacDetectWebSocket;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class DataController {

    private final DataService dataService;

    @RequestMapping(value = "/data", method = RequestMethod.POST)
    public void getPostData(@RequestParam("data") String oriData) {
        // System.out.println(oriData);
        ObjectMapper objectMapper = new ObjectMapper();
        PostDTO postDTO = null;
        try {
            postDTO = objectMapper.readValue(oriData, PostDTO.class);
            // System.out.println(postDTO.toString());
            dataService.processData(postDTO);
        } catch (JsonMappingException e) {
            System.out.println(oriData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/queryRecordList", method = RequestMethod.GET)
    public CommonResultDTO<List<AbstractRecordPO>> queryRecordList(@RequestParam("mmid") String mmid,
                                                                   @RequestParam("mac") String mac,
                                                                   @RequestParam("tarSsid") String tarSsid,
                                                                   @RequestParam("beginTime") String beginTime,
                                                                   @RequestParam("endTime") String endTime) {

        SelectRecordCO selectRecordCO = new SelectRecordCO();
        if (!beginTime.equals(""))
            selectRecordCO.setBeginTime(new Timestamp(Long.parseLong(beginTime)));
        if (!endTime.equals(""))
            selectRecordCO.setEndTime(new Timestamp(Long.parseLong(endTime)));
        if (!mmid.equals(""))
            selectRecordCO.setMid(mmid);
        if (!mac.equals(""))
            selectRecordCO.setMac(mac);
        if (!tarSsid.equals(""))
            selectRecordCO.setTarSsid(tarSsid);
        // dataService.queryAbstractRecord(selectRecordCO);
        return new CommonResultDTO<>(400, "OK", dataService.queryAbstractRecord(selectRecordCO));
    }
}
