package cn.dhbin.proxy;

import cn.dhbin.proxy.dao.UserDao;
import cn.dhbin.proxy.dao.impl.UserDaoImpl;
import cn.dhbin.proxy.dao.impl.UserDaoLogImpl;
import cn.dhbin.proxy.dao.impl.UserDaoTimeImpl;
import cn.dhbin.proxy.util.ProxyUtil;

/**
 * @author DHB
 */
public class ProxyTest {

	public static void main(String[] args) {
		UserDao userDao = new UserDaoImpl();
		System.out.println(userDao.getName());
		System.out.println(new UserDaoLogImpl().getName());
		System.out.println(new UserDaoTimeImpl(userDao).getName());


		// 测试自己写的动态代理
		UserDao newInstance = ProxyUtil.newInstance(userDao, (proxy, method, args1) -> {
			System.out.println("执行目标方法前");
			Object invoke = method.invoke(userDao, args1);
			System.out.println("执行目标方法后");
			return invoke;
		});
		if (newInstance != null) {
			newInstance.delete();
			newInstance.getName();
			newInstance.insert("user");
		}
	}
}
