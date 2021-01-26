package cc.kinami.wp.controller;

import cc.kinami.wp.model.dto.PostDTO;
import cc.kinami.wp.service.DataService;
import cc.kinami.wp.websocket.MacDetectWebSocket;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
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
}
