package cc.kinami.wp.dao;

import cc.kinami.wp.model.po.SingleRecordPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SingleRecordDAO {
    int insert(@Param("singleRecord")SingleRecordPO singleRecordPO);
}
