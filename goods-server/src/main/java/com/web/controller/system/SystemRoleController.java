package com.web.controller.system;

import com.web.base.BaseController;
import com.web.util.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;

@Controller
@RequestMapping(value = "role")
public class SystemRoleController extends BaseController {

	/* 查询 角色列表 */
	@RequestMapping(value = "roleList")
	public ModelAndView roleList(HttpServletRequest request, HttpServletResponse response) {

		return null;
	}

	/**
	 * 跳转新增角色
	 */
	@RequestMapping(value = "jumpAddRole")
	public ModelAndView jumpAddRole(HttpServletRequest request) {

		return null;
	}

	/**
	 * 
	 * 新增角色
	 * 
	 */
	@RequestMapping(value = "addRole")
	@ResponseBody
	public ResponseEntity<?> addRole(HttpServletRequest request) {
		return  null;

	}

	/**
	 * 判断角色是否已存在
	 * 
	 */
	@RequestMapping(value = "CheckRoleName")
	@ResponseBody
	public Map<String, Boolean> CheckRoleName(HttpServletRequest request) {

		return null;
	}

	/* 删除角色 */
	@RequestMapping(value = "deleteRole")
	public ModelAndView deleteRole(HttpServletRequest request, HttpServletResponse response) {

		return roleList(request, response);
	}

	// 修改角色权限跳转页面
	@RequestMapping(value = "updateRoleMenu")
	public ModelAndView updateRoleMenu(HttpServletRequest request, HttpServletResponse response) {

		return null;

	}



	/* 修改角色权限 */
	@RequestMapping(value = "updateUserRole")
	public ModelAndView updateUserRole(HttpServletRequest request, HttpServletResponse response) {

		return null;
	}

}
