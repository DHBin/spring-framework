package cn.dhbin.proxy.dao.impl;

/**
 * @author DHB
 * <p>
 * 静态代理-继承
 */
public class UserDaoLogImpl extends UserDaoImpl {

	@Override
	public String getName() {
		System.out.println("使用静态代理-继承实现添加日志:" + "getName()");
		return super.getName();
	}

	@Override
	public void delete() {

	}

	@Override
	public void insert(String user) {

	}
}
