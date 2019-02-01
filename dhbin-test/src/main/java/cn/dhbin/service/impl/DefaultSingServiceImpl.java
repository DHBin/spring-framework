package cn.dhbin.service.impl;

import cn.dhbin.service.SingService;
import org.springframework.stereotype.Service;

/**
 * 唱歌的默认实现
 *
 * @author DHB
 */
@Service
public class DefaultSingServiceImpl implements SingService {

	@Override
	public void sing() {
		System.out.println("唱歌");
	}

}
