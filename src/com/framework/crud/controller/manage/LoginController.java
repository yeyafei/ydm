package com.framework.crud.controller.manage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.framework.base.BaseAction;
import com.framework.crud.bean.manage.Module;
import com.framework.crud.bean.user.User;
import com.framework.crud.service.manage.ModuleService;
import com.framework.crud.service.user.UserService;
import com.framework.util.ManageUtils;

@Controller
public class LoginController extends BaseAction{

@Autowired
private ModuleService moduleService;
	@Autowired
	private UserService userService;

	/**
	 * 登录
	 * 
	 * @param request
	 * @param response
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	@RequestMapping("/loginok")
	public void loginOk(HttpServletRequest request, HttpServletResponse response, @RequestParam(defaultValue = "") String username, @RequestParam(defaultValue = "") String password, Model model) throws Exception {
		try {
			if (StringUtils.isBlank(username) || StringUtils.isBlank(password))
				throw new Exception("用户名或密码不能为空!");

			User user = userService.baseParSelect(new User(username));
			if (user.getId() == 0)
				throw new Exception("用户名不存在!");

			if ("false".equals(user.getActive()))
				throw new Exception("用户已经冻结!");

			else {
				if (!password.equals(user.getPassWord())) {
					throw new Exception("密码错误,请重新输入!");
				} else {
			List<Module> MODULES = moduleService.getModules(true);
			List<Module> MODULES_ALL = moduleService.getModules(false);
			
				// 获取一级菜单
			List<Module> fristMenu = ManageUtils.getModuleByParentId("0", MODULES);
			request.getSession().setAttribute("FRISTMENU", fristMenu);
			request.getSession().setAttribute("FRISTMENUNUM", fristMenu.get(0).getNum());
			request.getSession().setAttribute("MODULES", MODULES);
			request.getSession().setAttribute("MODULES_ALL", MODULES_ALL);
			request.getSession().removeAttribute("errormsg");
			request.getSession().setAttribute("USER", user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("errormsg", e.getMessage());
			response.sendRedirect("/login.jsp");
			return;
		}
		response.sendRedirect("/main.jsp");
	}

	/**
	 * 根据一级菜单NUM值获取所属二级菜单
	 * 
	 * @param request
	 * @param response
	 * @param num
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getsecmenu")
	public void getSecMenu(HttpServletRequest request, HttpServletResponse response, String num) {
		StringBuffer html = new StringBuffer("");
		List<Module> MODULES = (List<Module>) request.getSession().getAttribute("MODULES");

		for (int i = 0; i < MODULES.size(); i++) {
			Module m = MODULES.get(i);
			String url = null;
			if (num.equals(m.getParentid())) {
				url = m.getUrl() + "?num=" + m.getNum();
				html.append("<a href=\"javascript:toReq('" + url + "')\" name='second' class='secondmenu'>" + m.getDescription() + "</a>&nbsp;|&nbsp;");
			}
		}

		String result = html.toString();
		if (result.length() > 0 && result.lastIndexOf("&nbsp;|&nbsp;") > 0) {
			result = result.substring(0, result.lastIndexOf("&nbsp;|&nbsp;"));
		}

		ajaxHtml(result, response);
	}


	/**
	 * 退出登录
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/loginout")
	public void loginOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().invalidate();
		login(response);
	}

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/updatepwd")
	public void updatePwd(HttpServletRequest request, HttpServletResponse response) throws Exception {

	}

	/**
	 * 跳转登录页面
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public void login(HttpServletResponse response) throws Exception {		
		
		response.sendRedirect("/index.jsp");
	}
}
