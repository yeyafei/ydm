package com.framework.crud.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.framework.base.BaseService;
import com.framework.crud.bean.user.User;
import com.framework.crud.dao.user.UserDao;
import com.framework.util.SystemUtilities;

/**
 * @author yyf
 *
 */
@Service
public class UserService extends BaseService<User, UserDao>{
	
	
	@Override
	public String getListToTableHtml(List<User> list) {
		StringBuffer tableHtml = new StringBuffer();
		String[] ths = new String[]{"序号","用户名","用户级别","激活状态","创建时间","创建人"};
		tableHtml.append("<tr>");
		for (String s : ths)
			tableHtml.append("<th>" + s + "</th>");
		tableHtml.append("</tr>");
		for (int i = 1; i <= list.size(); i++) {
			User user = list.get(i-1);
			if(i % 2 != 1)
				tableHtml.append("<tr class='colortr' xh='"+i+"' onclick='changeTrColor(this," + i + ")'>");
			else
				tableHtml.append("<tr class='nocolortr' xh='"+i+"' onclick='changeTrColor(this," + i + ")'>");
			tableHtml.append("<td>"+i+ "<input type='hidden' id='IDS" + i + "' value='" + user.getId() + "' /></td>");
			tableHtml.append("<td>" + user.getName() + "</td>");
			tableHtml.append("<td>" + user.getLevel() + "</td>");
			tableHtml.append("<td>" + user.getActive() + "</td>");
			tableHtml.append("<td>" + user.getCreatDate()+ "</td>");
			tableHtml.append("<td>" + user.getCeratOper() + "</td>");
			tableHtml.append("</tr>");
		}
		return tableHtml.toString();
	}
	
	
	/**
	 * 增加
	 * baseGetCode (编号)
	 * @param user
	 * @throws Exception
	 */
	public void addUser(User user) throws Exception {
		user.setCode(baseGetCode(user));
		user.setCreatDate(SystemUtilities.getSysDateTime());
		baseSave(user);
	}

	/**
	 * 删除（此处是物理删除）
	 * @param user
	 * @throws Exception
	 */
	public void delUser(User user) throws Exception {
		baseParDel(user);
		
	}

	/**
	 * 修改 
	 * @param user
	 * @throws Exception
	 */
	public void updateUser(User user) throws Exception {
		baseUpdate(user);
		
	}
	
	/**
	 * 查找（此处是查找全部）
	 * @return
	 * @throws Exception
	 */
	public List<User> getUser() throws Exception {
		return baseSelectAll(new User());
	}


	/**
	 * 查找username
	 * @param name
	 * @throws Exception 
	 */
	public void checkUser(User user) throws Exception {
		User u=baseParSelect(user);
		if(u.getId()!=0){
			throw new Exception("用户名重复");
		}
		
	}


	/**
	 * 通过id获取user
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public User getUserById(String id) throws Exception {
		User u = new User();
		u.setId(Integer.parseInt(id));
		return baseParSelect(u);
	}



}
