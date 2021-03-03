package cc.kinami.wp.dao;

import cc.kinami.wp.dao.co.SelectRecordCO;
import cc.kinami.wp.model.po.AbstractRecordPO;
import cc.kinami.wp.model.po.FullRecordPO;
import cc.kinami.wp.model.po.SingleRecordPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordDAO {
    int insertFullRecord(@Param("fullRecord") FullRecordPO fullRecordPO);

    int insertSingleRecord(@Param("singleRecord") SingleRecordPO singleRecordPO);

    int insertRecordRelation(int fullID, int singleID);

    List<AbstractRecordPO> listAbstractRecordList(@Param("condition") SelectRecordCO selectRecordCO);

}
