package com.salon.service.checklist;

import com.salon.repository.bean.checklist.CheckListBean;
import com.salon.repository.bean.checklist.CheckListClientHistoryBean;
import com.salon.repository.bean.checklist.CheckListNewOrderBean;
import com.salon.repository.bean.quickorder.QuickOrderBean;
import com.salon.repository.entity.checklist.CheckList;
import com.salon.service.CRUDService;
import com.salon.utility.EnumStatusCheckList;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public interface CheckListService extends CRUDService<CheckListBean, CheckList> {
    QuickOrderBean createQuickOrde(QuickOrderBean orderBean);
    List<CheckListBean> getCheckListByStatusAndDayAndMountsAndYear(Long workerId,
                                                                   EnumStatusCheckList status,
                                                                   Integer day, Integer mounts, Integer year );
    
    List<CheckListClientHistoryBean> getClientHistory(HttpServletRequest req,
    																 Integer year,Integer month,String status);
    
	Map<Timestamp, List<Timestamp>> getFreeDatesForOrder(CheckListNewOrderBean checkListNewOrderBean);
	
	String createNewOrder(CheckListNewOrderBean checkListNewOrderBean);
}
