package cc.kinami.wp.dao;

import cc.kinami.wp.WifiProbeApplication;
import cc.kinami.wp.model.po.SingleRecordPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WifiProbeApplication.class)
public class SingleRecordDAOTest {
    @Autowired
    SingleRecordDAO singleRecordDAO;

    @Test
    public void simpleTest() {
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
        singleRecordDAO.insert(singleRecordPO);
        System.out.println(singleRecordPO.toString());
    }
}
