package cn.dhbin.aop.aspect;

import cn.dhbin.service.SingService;
import cn.dhbin.service.impl.DefaultSingServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 测试Aspect，实现打印日志
 * <p>
 * 使用{@link Aspect}声明切面
 *
 * Aspect中在{@link Aspect}中配置AspectJ的perthis 、pertarget
 * 还不支持percflow, percflowbelow, 和 pertypewithin
 *
 * prethis ：里面传入类方法，指定哪个类上使用原型(prototype)，与{@link org.springframework.context.annotation.Scope}一起使用
 *
 * Spring Aop 与 AspectJ的最大的区别是Spring Aop是在运行时动态执行的，AspectJ是在编译时就完成。
 *
 * {@link org.aspectj.lang.JoinPoint} 在{@link Pointcut}这个注解下的方法中加入参数可以获取横切点对象
 * 可以通过{@link JoinPoint#getArgs()} 获取方法参数
 * {@link JoinPoint#getTarget()} 获取目标对象
 * {@link JoinPoint#getThis()} 获取代理对象（当前对象）
 *
 * 在{@link Around}中使用{@link ProceedingJoinPoint}（这个是{@link JoinPoint}的子类），可以通过
 * {@link ProceedingJoinPoint#proceed()}前后实现环绕
 *
 * {@link DeclareParents}可以实现动态为实例接入新方法
 *
 * @author DHB
 */
@Component
@Aspect
public class LogAspect {

	/**
	 * https://blog.csdn.net/u010502101/article/details/76944753
	 * <p>
	 * "+"表示personService的所有子类；defaultImpl 表示默认需要添加的新的类
	 * <p>
	 * 只能用于有接口实现的类，否则报[xxxx] is not an interface
	 */
	@DeclareParents(value = "cn.dhbin.service.PersonService+", defaultImpl = DefaultSingServiceImpl.class)
	public SingService singService;


	/**
	 * execution比较常用，粒度比较小，功能强大
	 */
	@Pointcut("execution(* cn.dhbin.service.*.*(..))")
	public void pointCutExecution() {
	}


	/**
	 * 和execution类似，但这个只能适配包名，比execution粒度大
	 */
	@Pointcut("within(cn.dhbin.service.impl.StudentPersonServiceImpl)")
	public void pointCutWithin() {
	}


	/**
	 * 判断当前对象（代理后的对象）
	 */
	@Pointcut("this(cn.dhbin.service.PersonService)")
	public void pointCutThis() {
	}


	/**
	 * 判断目标对象，与this的区别是：this代理后的对象
	 */
	@Pointcut("target(cn.dhbin.service.impl.StudentPersonServiceImpl)")
	public void pointCutTarget() {
	}

	/**
	 * 前置
	 */
	@Before("pointCutExecution() && pointCutWithin() && pointCutThis() && pointCutTarget()")
	public void before() {
		System.out.println("before");
	}

	/**
	 * 后置
	 */
	@After("pointCutExecution()")
	public void after() {
		System.out.println("after");
	}

	@Around("pointCutExecution()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) {
		System.out.println("around before");
		Object o = null;
		try {
			o = proceedingJoinPoint.proceed();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		System.out.println("around after");
		return o;
	}

}
