package com.framework.crud.bean.user;

import com.framework.base.BaseEntity;
import com.framework.core.annotation.Code;
import com.framework.core.annotation.Primary;
import com.framework.core.annotation.TableName;
import com.framework.core.annotation.TempField;

/**
 * @author yyf
 * 
 * 用户Bean
 * Parimary 定义的有唯一性字段
 * Code 编码字段 一般是系统在插入时生成 如 U17010001（编码头 年 月 计数字段）
 * TempField 非数据库字段（在CRUD中会将其忽略）
 * @author yyf
 * 
 */

@TableName(name = "user")
public class User extends BaseEntity {
	private int id;
	
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
	 * 用户年龄/非数据库字段
	 */
	@TempField
	private String age;
	/**
	 * 用户联系方式
	 */
	private String phone;
	/**
	 * 用户备注
	 */
	private String remark;

	public void setUser(String name, String age, String phone, String remark) {
		this.name = name;
		this.age = age;
		this.phone = phone;
		this.remark = remark;
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [age=" + age + ", code=" + code + ", id=" + id + ", name="
				+ name + ", phone=" + phone + ", remark=" + remark + "]";
	}

}
