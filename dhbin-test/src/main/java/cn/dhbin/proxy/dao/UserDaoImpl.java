package cn.dhbin.proxy.dao;

/**
 * @author DHB
 * <p>
 * 实现类
 */
public class UserDaoImpl implements UserDao {

	@Override
	public String getName() {
		return "DHB";
	}

	@Override
	public void delete() {
		System.out.println("delete");
	}

	@Override
	public void insert(String user) {

	}
}
