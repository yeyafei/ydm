package com.framework.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.framework.crud.bean.manage.Module;
import com.framework.util.ManageUtils;


/**
 * Controller 基础实现方法 
 */
@SuppressWarnings("unchecked")
public class BaseController<T extends BaseEntity, S extends BaseService> extends BaseAction{
	@Autowired
	public S Service;
	
	/**
	 * 根据查询条件获取数据信息
	 */
	@RequestMapping(value="/list", method = { RequestMethod.POST })
	public void list(HttpServletRequest request,HttpServletResponse response, Model model
			, @RequestParam(defaultValue = "1") int pageNum
			, @RequestParam(defaultValue = "10") int pageSize
			, @RequestParam(defaultValue = "") String parentid
			, @ModelAttribute T entity) throws Exception{
		entity.setData(pageNum, pageSize);
		int totalCount = Service.baseSelectEqCount(entity); //绝对查询 暂无时间比较
		
		int pageAllCount = (totalCount/pageSize) + (totalCount % pageSize > 0 ? 1 : 0);
		System.out.println(pageAllCount);
		List<Module> menulist = null;
		if(StringUtils.isNoneBlank(parentid)){
			List<Module> modules = (List<Module>)request.getSession().getAttribute("MODULES");
			menulist = ManageUtils.getModuleByParentId(parentid, modules);
		}
		String resultJson = listToJson(Service.baseSelectEq(entity), pageNum, pageAllCount,menulist);
		ajaxJson(resultJson,response);
	}
	
	/**
	 * 将List数据转换成Json数据
	 * @param list
	 * @param pageNum
	 * @param pageAllCount
	 * @return
	 */
	public String listToJson(List<T> list, int pageNum, int pageAllCount,List<Module> menulist) {
		StringBuffer tableHtml = new StringBuffer();
		if (list == null || list.size() == 0) {
			tableHtml.append("<div class='notRecord'>没有记录!</div>");
		} else {
			tableHtml.append("<table class='layui-table'>");
			tableHtml.append(Service.getListToTableHtml(list));
			tableHtml.append("</table>");
		}
		StringBuffer result = new StringBuffer("{\"table\": \"");
		result.append(tableHtml);
		result.append("\",\"pageNum\" : \"" + pageNum + "\",\"pageAllCount\":\"" + pageAllCount + "\"");
		return ManageUtils.splitMenu(result, menulist,list.size());
	}
}
