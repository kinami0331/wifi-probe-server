package cc.kinami.wp.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SingleRecordPO {
    private Integer id;
    private Timestamp createTime;
    private Timestamp updatedTime;
    private String mac;
    private String router;
    private Integer rssi;
    private Integer rssi1;
    private Integer rssi2;
    private Integer rssi3;
    private Integer rssi4;
    private String tarSsid;
    private String tarMac;
    private Boolean connected;
    private Boolean sleeping;
    private String essid0;
    private String essid1;
    private String essid2;
    private String essid3;
    private String essid4;
    private String essid5;
    private String essid6;
    private Double distance;
}