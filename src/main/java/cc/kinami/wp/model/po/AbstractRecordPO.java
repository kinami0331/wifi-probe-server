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
public class AbstractRecordPO {
    // 探测设备id
    private String mid;
    // 探测设备地址
    private String mmac;
    // 记录时间
    private String time;
    // 目标设备id
    private String mac;
    // 如果是路由器，则路由器的名称
    private String router;
    // rssi值
    private Integer rssi;
    // 目标路由器的ssid
    private String tarSsid;
    // 目标路由器的mac地址
    private String tarMac;
    // 是否连接
    private Boolean connected;
    // 是否睡眠状态
    private Boolean sleeping;
    // 距离
    private Double distance;
}
