package cn.dhbin.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 测试Aspect，实现打印日志
 * <p>
 * 使用{@link Aspect}声明切面
 *
 * @author DHB
 */
@Component
@Aspect
public class LogAspect {


	@Pointcut("execution(* cn.dhbin.service.*.*(..))")
	public void pointCut(){}


	/**
	 * 前置
	 */
	@Before("pointCut()")
	public void before() {
		System.out.println("before");
	}
}
