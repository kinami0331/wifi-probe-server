package cc.kinami.wp.controller;

import cc.kinami.wp.model.dto.CommonResultDTO;
import cc.kinami.wp.model.dto.PostDTO;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/test/")
public class TestController {

    @RequestMapping(value = "/data", method = RequestMethod.POST)
    public void getPostData(@RequestParam("data") String oriData) {
        // System.out.println(oriData);
        ObjectMapper objectMapper = new ObjectMapper();
        PostDTO postDTO = null;
//        try {
//            postDTO = objectMapper.readValue(oriData, PostDTO.class);
//            // System.out.println(postDTO.toString());
//            dataService.processData(postDTO);
//        } catch (JsonMappingException e) {
//            System.out.println(oriData);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @RequestMapping(value = "/keepConnection", method = RequestMethod.POST)
    public CommonResultDTO<String> getConnectionData(@RequestBody Map<String, String> data) {
        System.out.println(data);
        return new CommonResultDTO<>(200, "OK", "连接成功");
    }

}
