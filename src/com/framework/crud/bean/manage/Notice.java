package com.framework.crud.bean.manage;

import com.framework.base.BaseEntity;
import com.framework.core.annotation.TableName;

/**
 * 通告模块
 * 
 * @author yyf
 * 
 */
@TableName(name = "notice")
public class Notice extends BaseEntity {
	private int id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 创建人
	 */
	private String createOper;
	/**
	 * 创建时间
	 */
	private String createDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateOper() {
		return createOper;
	}

	public void setCreateOper(String createOper) {
		this.createOper = createOper;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
