package cn.dhbin.proxy.dao.impl;

import cn.dhbin.proxy.dao.UserDao;

/**
 * @author DHB
 * 静态代理-聚合
 */
public class UserDaoTimeImpl extends UserDaoLogImpl {

	private UserDao userDao;

	public UserDaoTimeImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public String getName() {
		System.out.println("使用静态代理-聚合实现添加运行时间:" + "getName()");
		return userDao.getName();
	}

	@Override
	public void delete() {

	}

	@Override
	public void insert(String user) {

	}
}
