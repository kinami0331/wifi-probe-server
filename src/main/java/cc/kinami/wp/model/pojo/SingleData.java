package cc.kinami.wp.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SingleData {
    private String mac;
    private String router;
    private Integer rssi;
    private Integer rssi1;
    private Integer rssi2;
    private Integer rssi3;
    private Integer rssi4;
    private String ts;
    private String tmc;
    private String tc;
    private String ds;
    private String essid0;
    private String essid1;
    private String essid2;
    private String essid3;
    private String essid4;
    private String essid5;
    private String essid6;
    private Double range;
}
