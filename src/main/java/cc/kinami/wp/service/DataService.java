package cc.kinami.wp.service;

import cc.kinami.wp.dao.co.SelectRecordCO;
import cc.kinami.wp.model.dto.PostDTO;
import cc.kinami.wp.model.po.AbstractRecordPO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DataService {
    void processData(PostDTO postDTO);
    List<AbstractRecordPO> queryAbstractRecord(SelectRecordCO selectRecordCO);
}
