package com.framework.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.framework.crud.bean.manage.Module;

/**
 * 系统管理工具类
 *
 */
public class ManageUtils {
	
	/**
	 * 拼接每个页面不同的操作
	 * @param s
	 * @param menulist
	 * @param listSize 
	 * @return
	 */
	public static String splitMenu(StringBuffer s, List<Module> menulist, int listSize){
		if(menulist == null ||listSize==0){
			s.append(",\"operate\": \"\" }");
		}else{
			s.append(",\"operate\": \"");
			s.append("<label>操作：</label>");
			for(Module module : menulist){
//				if(module.getCode().indexOf("SELPO") > -1){
//					s.append("<select id='" + module.getCode() + "' class='select' style='margin-top:-5px;'></select>");
//				}else{
					s.append("<input type='button'  class='layui-btn layui-btn-mini' onclick='javascript:"+ module.getUrl()+ "' value='"+ module.getDescription()+"'/>");
//				}
			}
			String menu ="menu";
			s.append("<label id='"+menu+"'></label>");
			s.append("\"}");
		}
		System.out.println(s.toString());
		return s.toString();
	}
	
	/**
	 * 根据parentid 获取模块信息
	 * @param parentId
	 * @param modules
	 * @return
	 */
	public static List<Module> getModuleByParentId(String parentId, List<Module> modules) {
		List<Module> result = new ArrayList<Module>();
		for(Module module : modules)
			if(parentId.equals(module.getParentid()))
				result.add(module);
		for(Module module2 : result){
			List<Module> result2 = new ArrayList<Module>();
			for(Module module : modules){
				if(module2.getNum().equals(module.getParentid()))
					result2.add(module);
			}
			module2.setList(result2);
		}
		return result;
	}

	/**
	 * 去除重复模块数据
	 * @param modules
	 */
	@SuppressWarnings("unchecked")
	public static List<Module> removeRepeatData(List<Module> modules) {
//		HashSet<Module> h = new HashSet<Module>(modules);  
//		modules.clear();  
//		modules.addAll(h);  
		Set set = new HashSet();
		List list = new ArrayList();
		for (Iterator iter = modules.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (set.add(element))
				list.add(element);
		}
		modules.clear();
		modules.addAll(list);
		return modules;
	}
	
	/**
	 * 判断字符串是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[\\-\\+]?((([0-9]{1,3})([,][0-9]{3})*)|([0-9]+))?([\\.]([0-9]+))?$");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 根据长度截取字符
	 * @param cuidname
	 * @param i
	 * @return
	 */
	public static String SubStrByLen(String str, int i) {
		if(StringUtils.isBlank(str))
			return "";
		if(i > str.length())
			return str;
		return str.substring(0, i);
	}
	
	
	public synchronized static String getUploadId() {
		Calendar c = Calendar.getInstance();

		return c.getTimeInMillis() + "";
	}
}
