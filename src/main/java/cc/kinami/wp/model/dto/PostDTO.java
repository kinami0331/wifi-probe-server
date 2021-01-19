package cc.kinami.wp.model.dto;

import cc.kinami.wp.model.pojo.SingleData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private String id;
    private String mmac;
    private String rate;
    private String time;
    private Double lat;
    private Double lon;
    private List<SingleData> data;
}
