package com.framework.crud.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.framework.base.BaseService;
import com.framework.crud.bean.user.User;
import com.framework.crud.dao.user.UserDao;

/**
 * MYBAITS CRUD SERVICE
 * 
 * 结合controller中的四个基本增删改查
 * 
 * 更多操作在BaseService
 * 
 * @author yyf
 *
 */
@Service
public class UserService extends BaseService<User, UserDao>{
	
	/**
	 * 增加
	 * baseGetCode (编号)
	 * @param user
	 * @throws Exception
	 */
	public void addUser(User user) throws Exception {
		user.setCode(baseGetCode(user));
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

}
