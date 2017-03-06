package com.framework.crud.service.manage;

import java.util.List;

import org.springframework.stereotype.Service;

import com.framework.base.BaseService;
import com.framework.crud.bean.manage.Notice;
import com.framework.crud.dao.manage.NoticeDao;

/**
 * 通告
 * 
 * @author yyf
 * 
 */
@Service
public class NoticeService extends BaseService<Notice, NoticeDao> {
	@Override
	public String getListToTableHtml(List<Notice> list) {
		StringBuffer tableHtml = new StringBuffer();
		String[] ths = new String[]{"发表日期","发表人","标题","内容"};
		tableHtml.append("<tr>");
		for (String s : ths)
			tableHtml.append("<th>" + s + "</th>");
		tableHtml.append("</tr>");
		for (int i = 1; i <= list.size(); i++) {
			Notice notice = list.get(i-1);
			if(i % 2 != 1)
				tableHtml.append("<tr class='colortr' xh='"+i+"' onclick='changeTrColor(this," + i + ")'>");
			else
				tableHtml.append("<tr class='nocolortr' xh='"+i+"' onclick='changeTrColor(this," + i + ")'>");
			tableHtml.append("<td>" + notice.getCreateDate()+ "<input type='hidden' id='IDS" + i + "' value='" + notice.getId() + "' /></td>");
			tableHtml.append("<td>" + notice.getCreateOper() + "</td>");
			tableHtml.append("<td>" + notice.getTitle() + "</td>");
			tableHtml.append("<td>" + notice.getContent() + "</td>");
			tableHtml.append("</tr>");
		}
		return tableHtml.toString();
	}
}
