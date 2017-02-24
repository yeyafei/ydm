package com.framework.crud.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	 * 增加操作
	 * @return
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.GET })
	public String add() {
		try {
			User user = new User();
//			user.setUser("name", "age", "phone", "remark");
			userService.addUser(user);
		} catch (Exception e) {
			//model 异常抛出操作
		}
		return "redirect:/user/get.do";
	}

	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value = "/del", method = { RequestMethod.GET })
	public String del() {
		try {
			User user = new User();
//			user.setName("name"); 
			userService.delUser(user);
		} catch (Exception e) {
			//model 异常抛出操作
		}
		return "redirect:/user/get.do";
	}

	/**
	 * 修改操作
	 * @return
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.GET })
	public String update() {
		try {
			User user = new User();
//			user.setUser("name", "age2", "phone2", "remark2");
			user.setCode("U17010001"); //code在此bean中有些特殊
			userService.updateUser(user);
		} catch (Exception e) {
			//model 异常抛出操作
		}
		return "redirect:/user/get.do";
	}

	/**
	 * 查询操作
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/get", method = { RequestMethod.GET })
	public @ResponseBody String show() {
		List<User> users = null;
		try {
			users = userService.getUser();
		} catch (Exception e) {
			//model 异常抛出操作
		}
		return users.toString();
	}

}
