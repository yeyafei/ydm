package com.framework.crud.bean.user;

import com.framework.base.BaseEntity;
import com.framework.core.annotation.Code;
import com.framework.core.annotation.Primary;
import com.framework.core.annotation.TableName;
import com.framework.core.annotation.TempField;

/**
 * @author yyf
 * 
 */

@TableName(name = "user")
public class User extends BaseEntity {
	private int id;
	@TempField
	private String ids;

	/**
	 * 用户名 /唯一
	 */
	@Primary
	private String name;
	/**
	 * 用户编码 /唯一/U开头
	 */
	@Code(name = "U")
	@Primary
	private String code;

	/**
	 * 用户密码
	 */
	private String passWord;

	/**
	 * 用户级别
	 */
	private String level;

	/**
	 * 激活状态
	 */
	private String active;

	/**
	 * 创建时间
	 */
	private String creatDate;
	/**
	 * 创建人
	 */
	private String ceratOper;
	/**
	 * 用户角色
	 */
	private String userRole;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}


	public String getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(String creatDate) {
		this.creatDate = creatDate;
	}

	public String getCeratOper() {
		return ceratOper;
	}

	public void setCeratOper(String ceratOper) {
		this.ceratOper = ceratOper;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public User(){
		
	}
	public User(String name){
		this.name = name;
	}
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	@Override
	public String toString() {
		return "User [active=" + active + ", ceratOper=" + ceratOper
				+ ", code=" + code + ", creatDate=" + creatDate + ", id=" + id
				+ ", level=" + level
				+ ", name=" + name + ", passWord=" + passWord + ", userRole="
				+ userRole + "]";
	}

}
