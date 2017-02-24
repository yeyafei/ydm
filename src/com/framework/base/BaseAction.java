package com.framework.base;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class BaseAction{
	protected Logger logger = Logger.getLogger(this.getClass());
	protected static final String SUCCESS_MSG = "1"; //方法执行成功后的返回值

	protected String prefix="";
	private String url;
	
	// AJAX输出文本，返回null
	public String ajaxText(String text, HttpServletResponse response) {
		return ajax(text, "text/plain", response);
	}

	// AJAX输出HTML，返回null
	public String ajaxHtml(String html, HttpServletResponse response) {
		return ajax(html, "text/html", response);
	}

	// AJAX输出XML，返回null
	public String ajaxXml(String xml, HttpServletResponse response) {
		return ajax(xml, "text/xml", response);
	}

	// 根据字符串输出JSON，返回null
	public String ajaxJson(String jsonString, HttpServletResponse response) {
		return ajax(jsonString, "text/html", response);
	}

	// 输出json到页面

	public void printJson(JSONObject json, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("utf-8");
			PrintWriter pr = response.getWriter();
			pr.print(json);
			pr.flush();
			pr.close();
		} catch (IOException e) {
			//logger.error("类BaseAction输出json到页面时出现错误", e);
		}
	}
	
	public void printJson(JSONArray json, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("utf-8");
			PrintWriter pr = response.getWriter();
			pr.print(json);
			pr.flush();
			pr.close();
		} catch (IOException e) {
			//logger.error("类BaseAction打印json到页面时出现错误", e);
		}
	}

	// AJAX输出，返回null
	public String ajax(String content, String type, HttpServletResponse response) {
		try {
			response.setContentType(type + ";charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			logger.error("类BaseActionAjax返回页面时出现错误", e);
		}
		return null;
	}
	
	/**
	 * 跳转页面
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "view{page}")
	public String view(@PathVariable("page") String page,
			@RequestParam(defaultValue = "") String num, Model model){
		model.addAttribute("parentid", num);
		return viewpath(page);
	}

	/**
	 * 根据control的注解获得路径
	 * @param jsp
	 * @return
	 */
	protected String viewpath(String jsp){
		if(StringUtils.isBlank(url)){
			String packageurl = this.getClass().getPackage().getName();
			url = packageurl.substring(packageurl.lastIndexOf(".")+1);
			packageurl = packageurl.replace("." + url, "");
			url = packageurl.substring(packageurl.lastIndexOf(".")+1) + "/" + url;
			url = prefix+url;
			if(url.contains("controller/")){
				url=url.replace("controller/", "");
			}
			if(url.indexOf("webcontent/") > -1){
				url = url.replace("webcontent/", "");
			}
		}
		return url+"/"+jsp;
	}
}
