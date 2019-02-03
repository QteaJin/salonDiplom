package com.salon.service.checklist;

import com.salon.repository.bean.checklist.CheckListBean;
import com.salon.repository.bean.quickorder.QuickOrderBean;
import com.salon.repository.entity.checklist.CheckList;
import com.salon.service.CRUDService;
import com.salon.utility.EnumStatusCheckList;

import java.util.List;

public interface CheckListService extends CRUDService<CheckListBean, CheckList> {
    QuickOrderBean createQuickOrde(QuickOrderBean orderBean);
    List<CheckListBean> getCheckListByStatusAndDayAndMountsAndYear(Long workerId,
                                                                   EnumStatusCheckList status,
                                                                   Integer day, Integer mounts, Integer year );
}
