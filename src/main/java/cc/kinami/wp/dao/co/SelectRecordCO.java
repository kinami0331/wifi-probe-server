package cc.kinami.wp.dao.co;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SelectRecordCO {
    /**
     * 筛选时间段（左边界）
     */
    private Timestamp beginTime;
    /**
     * 筛选时间段（右边界）
     */
    private Timestamp endTime;
    private String mid;
    private String mac;
    private String tarSsid;
}
