package com.framework.crud.controller.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.framework.base.BaseController;
import com.framework.crud.bean.manage.Notice;
import com.framework.crud.service.manage.NoticeService;
/**
 * 通告模块
 * @author yyf
 *
 */
@Controller
@RequestMapping(value = "/notice")
public class NoticeController  extends BaseController<Notice, NoticeService>{

}
