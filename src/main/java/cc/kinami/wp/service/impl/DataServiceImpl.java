package cc.kinami.wp.service.impl;

import cc.kinami.wp.dao.RecordDAO;
import cc.kinami.wp.model.dto.PostDTO;
import cc.kinami.wp.model.po.FullRecordPO;
import cc.kinami.wp.model.po.SingleRecordPO;
import cc.kinami.wp.model.pojo.SingleData;
import cc.kinami.wp.service.DataService;
import cc.kinami.wp.websocket.MacDetectWebSocket;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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
        LocalDateTime localDateTime = LocalDateTime.parse(postDTO.getTime(), dateTimeFormatter);
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
        }
    }

    private String generateMessage(FullRecordPO fullRecordPO, SingleRecordPO singleRecordPO) {
        String msg = "";
        msg += "检测到一条动态！<br/>";
        msg += "嗅探设备信息：<br/>";
        msg += "&emsp;&emsp;嗅探设备id： " + fullRecordPO.getMid() + "<br/>";
        msg += "&emsp;&emsp;嗅探设备记录时间： " + fullRecordPO.getTime().toString() + "<br/>";
        msg += "目标设备信息：<br/>";
        msg += "&emsp;&emsp;mac： " + singleRecordPO.getMac() + "<br/>";
        msg += "&emsp;&emsp;rssi： " + singleRecordPO.getRssi().toString() + "<br/>";
        msg += "&emsp;&emsp;range： " + singleRecordPO.getDistance().toString() + "<br/>";
        if (singleRecordPO.getRouter() != null)
            msg += "&emsp;&emsp;router： " + singleRecordPO.getRouter() + "<br/>";
        if (singleRecordPO.getTarSsid() != null)
            msg += "&emsp;&emsp;tar_ssid： " + singleRecordPO.getTarSsid() + "<br/>";
        if (singleRecordPO.getTarMac() != null)
            msg += "&emsp;&emsp;tar_mac： " + singleRecordPO.getTarMac() + "<br/>";
        if (singleRecordPO.getConnected() != null)
            msg += "&emsp;&emsp;is_connect： " + singleRecordPO.getConnected().toString() + "<br/>";
        if (singleRecordPO.getSleeping() != null)
            msg += "&emsp;&emsp;is_sleeping： " + singleRecordPO.getSleeping().toString() + "<br/>";
        if (singleRecordPO.getEssid0() != null)
            msg += "&emsp;&emsp;essid0： " + singleRecordPO.getEssid0() + "<br/>";
        if (singleRecordPO.getEssid1() != null)
            msg += "&emsp;&emsp;essid1： " + singleRecordPO.getEssid1() + "<br/>";
        if (singleRecordPO.getEssid2() != null)
            msg += "&emsp;&emsp;essid2： " + singleRecordPO.getEssid2() + "<br/>";
        return msg;
    }

}
