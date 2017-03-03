package com.framework.crud.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.framework.base.BaseController;
import com.framework.crud.bean.user.User;
import com.framework.crud.service.user.UserService;

/**
 * @author yyf
 *
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController<User, UserService> {
	@Autowired
	private UserService userService;
	
	
	/**
	 * 进入新增或修改页面
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.GET })
	public String edit(HttpServletRequest request, Model model,
			@RequestParam(defaultValue = "") String id) {
		if (StringUtils.isNotBlank(id)) {
			try {
				User user = userService.getUserById(id);
				model.addAttribute("user", user);
			} catch (Exception e) {
				model.addAttribute("errormsg", "[ " + e.getMessage() + "! ]");
				e.printStackTrace();
			}
		}
		return viewpath("useredit");
	}

	
	
	/**
	 * 保存操作
	 * @param request
	 * @param model
	 * @param module
	 * @return
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.POST })
	public String save(HttpServletRequest request, Model model, @ModelAttribute User user) {
		try {
			User u = (User) request.getSession().getAttribute("USER");
			if ("".equals(user.getIds())) { // 新增
				userService.checkUser(user);
				user.setCeratOper(u.getName());
				userService.addUser(user);
			} else { // 修改
				user.setId(Integer.parseInt(user.getIds()));
				userService.updateUser(user);
			}
			model.addAttribute("msg", "[ 操作成功 ]");
			model.addAttribute("ifoper", "true");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "[ " + e.getMessage() + "! ]");
		}
		model.addAttribute("user", user);
		return viewpath("useredit");
	}

	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value = "/del", method = { RequestMethod.POST })
	public void del(HttpServletResponse response, @RequestParam(defaultValue = "") String id) {
		try {
			User user = new User();
			user.setId(Integer.parseInt(id));
			userService.delUser(user);
			ajaxText("删除成功", response);
		} catch (Exception e) {
			ajaxText("删除失败:" + e.getMessage(), response);
		}
	}


}
