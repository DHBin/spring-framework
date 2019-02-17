package cn.dhbin.proxy;

import cn.dhbin.proxy.dao.UserDao;
import cn.dhbin.proxy.dao.UserDaoImpl;
import cn.dhbin.proxy.dao.UserDaoLogImpl;
import cn.dhbin.proxy.dao.UserDaoTimeImpl;
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


		UserDao newInstance = ProxyUtil.newInstance(userDao, (proxy, method, args1) -> {
			System.out.println("执行目标方法前");
			Object invoke = method.invoke(userDao, args1);
			System.out.println("执行目标方法后");
			return invoke;
		});
		if (newInstance != null) {
			newInstance.delete();
		}
	}
}
