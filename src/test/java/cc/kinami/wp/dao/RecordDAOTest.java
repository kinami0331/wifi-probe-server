package cc.kinami.wp.dao;

import cc.kinami.wp.WifiProbeApplication;
import cc.kinami.wp.model.po.FullRecordPO;
import cc.kinami.wp.model.po.SingleRecordPO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WifiProbeApplication.class)
public class RecordDAOTest {
    @Autowired
    RecordDAO recordDAO;

    @Test
    public void insertFullRecordTest() {
        FullRecordPO fullRecordPO;
        fullRecordPO = FullRecordPO.builder()
                .mmac("123")
                .lat(24.44)
                .lon(23.33)
                .rate("1")
                .mid("123")
                .time(new Timestamp(1))
                .build();
        int rv = recordDAO.insertFullRecord(fullRecordPO);
        System.out.println(rv);
        System.out.println(fullRecordPO.getId());
    }

    @Test
    public void insertSingleRecordTest() {
        SingleRecordPO singleRecordPO = SingleRecordPO.builder()
                .mac("00:00:00:00:00:00")
                .router("??")
                .rssi(1)
                .rssi1(1)
                .rssi2(1)
                .rssi3(1)
                .rssi4(1)
                .connected(Boolean.TRUE)
                .sleeping(Boolean.FALSE)
                .essid0("000")
                .essid1("111")
                .essid2("222")
                .essid3("333")
                .essid4("444")
                .essid5("555")
                .essid6("666")
                .tarMac("00:00:00:00:00:00")
                .tarSsid("sadf")
                .build();
        int rv = recordDAO.insertSingleRecord(singleRecordPO);
        Assert.assertEquals(1, rv);
        System.out.println(singleRecordPO.toString());
    }

    @Test
    public void insertRecordRelation() {
        recordDAO.insertRecordRelation(1, 2);
    }
}
