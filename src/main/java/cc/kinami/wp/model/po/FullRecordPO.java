package cc.kinami.wp.model.po;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FullRecordPO {
    private Integer id;
    private Timestamp createTime;
    private Timestamp updatedTime;
    private String mid;
    private String mmac;
    private String rate;
    private Timestamp time;
    private Double lat;
    private Double lon;
}
