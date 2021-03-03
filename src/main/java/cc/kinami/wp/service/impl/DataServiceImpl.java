package cc.kinami.wp.service.impl;

import cc.kinami.wp.dao.RecordDAO;
import cc.kinami.wp.dao.co.SelectRecordCO;
import cc.kinami.wp.model.dto.PostDTO;
import cc.kinami.wp.model.po.AbstractRecordPO;
import cc.kinami.wp.model.po.FullRecordPO;
import cc.kinami.wp.model.po.SingleRecordPO;
import cc.kinami.wp.model.pojo.SingleData;
import cc.kinami.wp.service.DataService;
import cc.kinami.wp.websocket.MacDetectCsvWebSocket;
import cc.kinami.wp.websocket.MacDetectWebSocket;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class DataServiceImpl implements DataService {

    RecordDAO recordDAO;

    @Override
    public void processData(PostDTO postDTO) {
        // 返回值判断
        int returnValue;
        // 解析日期格式
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EE MMM dd HH:mm:ss yyyy", Locale.US);
        String postTime = postDTO.getTime();
        if (postTime.charAt(8) == ' ') {
            // System.out.println("时间有问题：" + postTime);
            StringBuilder strBuilder = new StringBuilder(postTime);
            strBuilder.setCharAt(8, '0');
            postTime = strBuilder.toString();
        }
        LocalDateTime localDateTime = LocalDateTime.parse(postTime, dateTimeFormatter);
        // System.out.println(localDateTime.toString());
        // 计算时间戳秒数
        long seconds = localDateTime.toEpochSecond(ZoneOffset.ofHours(8));
        // System.out.println(seconds);
        // 插入full record
        FullRecordPO fullRecordPO;
        fullRecordPO = FullRecordPO.builder()
                .time(new Timestamp(seconds * 1000))
                .rate(postDTO.getRate())
                .lon(postDTO.getLon())
                .lat(postDTO.getLat())
                .mmac(postDTO.getMmac())
                .mid(postDTO.getId())
                .build();
        returnValue = recordDAO.insertFullRecord(fullRecordPO);
        if (returnValue != 1)
            System.out.println("sth wrong!");
        int fullRecordID = fullRecordPO.getId();

        SingleRecordPO tarSingleRecordPO = null;
        // 插入single record
        for (SingleData singleData : postDTO.getData()) {
            // 构建singleRecordPO
            SingleRecordPO singleRecordPO;
            singleRecordPO = SingleRecordPO.builder()
                    .mac(singleData.getMac())
                    .tarSsid(singleData.getTs())
                    .tarMac(singleData.getTmc())
                    .essid0(singleData.getEssid0())
                    .essid1(singleData.getEssid1())
                    .essid2(singleData.getEssid2())
                    .essid3(singleData.getEssid3())
                    .essid4(singleData.getEssid4())
                    .essid5(singleData.getEssid5())
                    .essid6(singleData.getEssid6())
                    .rssi(singleData.getRssi())
                    .rssi1(singleData.getRssi1())
                    .rssi2(singleData.getRssi2())
                    .rssi3(singleData.getRssi3())
                    .rssi4(singleData.getRssi4())
                    .distance(singleData.getRange())
                    .router(singleData.getRouter())
                    .build();
            if (singleData.getDs() != null)
                singleRecordPO.setSleeping(singleData.getDs().equals("Y") ? Boolean.TRUE : Boolean.FALSE);
            if (singleData.getTc() != null)
                singleRecordPO.setConnected(singleData.getTc().equals("Y") ? Boolean.TRUE : Boolean.FALSE);
            recordDAO.insertSingleRecord(singleRecordPO);
            int singleRecordID = singleRecordPO.getId();
            // 添加关系记录
            recordDAO.insertRecordRelation(fullRecordID, singleRecordID);

            MacDetectWebSocket.sendMacDetectionInfo(singleData.getMac(),
                    generateMessage(fullRecordPO, singleRecordPO));

            if (singleData.getMac().equals("30:57:14:09:37:79"))
                tarSingleRecordPO = singleRecordPO;
        }
        MacDetectCsvWebSocket.sendMacDetectionInfo("30:57:14:09:37:79",
                generateCsvMessage(fullRecordPO, tarSingleRecordPO));
    }

    @Override
    public List<AbstractRecordPO> queryAbstractRecord(SelectRecordCO selectRecordCO) {
        System.out.println(selectRecordCO);
        List<AbstractRecordPO> abstractRecordPOList = recordDAO.listAbstractRecordList(selectRecordCO);
        System.out.println(abstractRecordPOList.size());
        return abstractRecordPOList;
    }

    private String generateMessage(FullRecordPO fullRecordPO, SingleRecordPO singleRecordPO) {
        AbstractRecordPO abstractRecordPO = AbstractRecordPO.builder()
                .mid(fullRecordPO.getMid())
                .mmac(fullRecordPO.getMmac())
                .time(fullRecordPO.getTime().toString())
                .mac(singleRecordPO.getMac())
                .router(singleRecordPO.getRouter())
                .rssi(singleRecordPO.getRssi())
                .tarSsid(singleRecordPO.getTarSsid())
                .tarMac(singleRecordPO.getTarMac())
                .connected(singleRecordPO.getConnected())
                .sleeping(singleRecordPO.getSleeping())
                .distance(singleRecordPO.getDistance())
                .build();
        try {
            String msg = new ObjectMapper().writeValueAsString(abstractRecordPO);
            // System.out.println(msg);
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private String generateCsvMessage(FullRecordPO fullRecordPO, SingleRecordPO singleRecordPO) {
        String msg = "";
        // 时间
        msg += fullRecordPO.getTime().toString() + ",";
        if (singleRecordPO == null) {
            msg += "0,,<br/>";
        } else {
            msg += "1,";
            msg += singleRecordPO.getRssi().toString() + ",";
            msg += singleRecordPO.getDistance().toString() + "<br/>";

        }
        return msg;
    }

}
