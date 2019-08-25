package cn.dhbin.aop.aopcontext.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author donghaibin
 * @date 2019/8/25
 */
@Component
@Aspect
public class TestAspect {

	@Pointcut("execution(* cn.dhbin.aop.aopcontext.service.impl..*.*(..))")
	public void pointCut(){}


	@Before("pointCut()")
	public void before() {
		System.out.println("running...");
	}
}
