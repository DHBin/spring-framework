package cn.dhbin.proxy.dao.impl;

import cn.dhbin.proxy.dao.UserDao;

/**
 * @author DHB
 * <p>
 * 实现类
 */
public class UserDaoImpl implements UserDao {

	@Override
	public String getName() {
		System.out.println(" String getName()");
		return "DHB";
	}

	@Override
	public void delete() {
		System.out.println("void delete()");
	}

	@Override
	public void insert(String user) {
		System.out.println("void insert(String user)");
	}
}
