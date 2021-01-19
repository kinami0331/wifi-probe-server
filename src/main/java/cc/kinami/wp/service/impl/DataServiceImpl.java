package cc.kinami.wp.service.impl;

import cc.kinami.wp.dao.SingleRecordDAO;
import cc.kinami.wp.model.dto.PostDTO;
import cc.kinami.wp.model.po.SingleRecordPO;
import cc.kinami.wp.model.pojo.SingleData;
import cc.kinami.wp.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
@AllArgsConstructor
public class DataServiceImpl implements DataService {

    SingleRecordDAO singleRecordDAO;

    @Override
    public void processData(PostDTO postDTO) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EE MMM dd HH:mm:ss yyyy", Locale.US);
        LocalDateTime localDateTime = LocalDateTime.parse(postDTO.getTime(), dateTimeFormatter);
        System.out.println(localDateTime.toString());
        long seconds = localDateTime.toEpochSecond(ZoneOffset.ofHours(8));
        System.out.println(seconds);
        for (SingleData singleData : postDTO.getData()) {
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
            if(singleData.getDs() != null)
                singleRecordPO.setSleeping(singleData.getDs().equals("Y") ? Boolean.TRUE : Boolean.FALSE);
            if(singleData.getTc() != null)
                singleRecordPO.setConnected(singleData.getTc().equals("Y") ? Boolean.TRUE : Boolean.FALSE);
            singleRecordDAO.insert(singleRecordPO);
        }
    }
}
