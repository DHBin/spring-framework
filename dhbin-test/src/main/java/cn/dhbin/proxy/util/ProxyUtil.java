package cn.dhbin.proxy.util;

import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author DHB
 */
public class ProxyUtil {


	/**
	 * content
	 * .java
	 * .class
	 * .new
	 * <p>
	 * 生成代理对象
	 *
	 * @return 代理对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(T target, InvocationHandler handler) {
		System.out.println("======newInstance======");
		Class<?> targetClass = target.getClass();

		// 构建构造器
		ParameterSpec handlerSpec = ParameterSpec.builder(InvocationHandler.class, "handler").build();
		MethodSpec constructorSpec = MethodSpec.constructorBuilder()
				.addModifiers(Modifier.PUBLIC)
				.addParameter(targetClass, "target")
				.addParameter(handlerSpec)
				.addStatement("this.$N = $N", "target", "target")
				.addStatement("this.$N = $N", "handler", "handler")
				.build();

		List<MethodSpec> methodSpecs = new ArrayList<>();

		// 构建方法
		for (Class<?> targetClassInterface : targetClass.getInterfaces()) {
			for (Method method : targetClassInterface.getMethods()) {
				Class<?> returnType = method.getReturnType();
				List<ParameterSpec> parameterSpecs = new ArrayList<>();
				for (Parameter parameter : method.getParameters()) {
					ParameterSpec parameterSpec = ParameterSpec.builder(parameter.getType(), parameter.getName()).build();
					parameterSpecs.add(parameterSpec);
				}
				MethodSpec.Builder builder = MethodSpec.methodBuilder(method.getName())
						.addModifiers(Modifier.PUBLIC)
						.addParameters(parameterSpecs)
						.addAnnotation(Override.class)
						.returns(returnType);


				builder.addStatement("$T thisClass = target.getClass()", Class.class)
						.addStatement("$T methodName = $S", String.class, method.getName())
						.addStatement("$T[] params = new $T[]{$N}", Class.class, Class.class, Arrays.stream(method.getParameters())
								.map(parameter -> parameter.getType().getName() + ".class")
								.collect(Collectors.joining(","))
						)
						.addStatement("Object[] args = new Object[]{$N}", parameterSpecs.stream()
								.map(parameterSpec -> parameterSpec.name)
								.collect(Collectors.joining(","))
						);
				if (returnType.equals(Void.TYPE)) {

					builder.beginControlFlow("try")
							.addStatement("$N.invoke(this, thisClass.getMethod(methodName, params), $N)", handlerSpec, "args")
							.nextControlFlow("catch ($T t)", Throwable.class)
							.addStatement("$T.out.println(t)", System.class)
							.endControlFlow();

				} else {
					builder.beginControlFlow("try")
							.addStatement("return ($T) $N.invoke(this, thisClass.getMethod(methodName, params), $N)", returnType, handlerSpec, "args")
							.nextControlFlow("catch ($T t)", Throwable.class)
							.addStatement("$T.out.println(t)", System.class)
							.addStatement("return null")
							.endControlFlow();
				}
				methodSpecs.add(builder.build());
			}
		}
		String className = targetClass.getSimpleName() + "$proxy";
		String packageName = "cn.dhbin.proxy";

		// 构建类
		TypeSpec typeSpec = TypeSpec.classBuilder(className)
				.addModifiers(Modifier.PUBLIC)
				.addSuperinterfaces(Arrays.stream(targetClass.getInterfaces()).map(TypeName::get).collect(Collectors.toList()))
				.addMethod(constructorSpec)
				.addMethods(methodSpecs)
				.addField(targetClass, "target")
				.addField(InvocationHandler.class, "handler")
				.addAnnotation(AnnotationSpec.builder(SuppressWarnings.class).addMember("value", "$S", "unchecked").build())
				.build();
		JavaFile javaFile = JavaFile.builder(packageName, typeSpec).build();
		String tmpDir = System.getProperty("java.io.tmpdir");
		System.out.println("生成目录：" + tmpDir);
		System.out.println(javaFile.toString());
		try {
			javaFile.writeTo(new File(tmpDir));
			compiler(tmpDir + packageName.replace(".", "/") + "/" + className + ".java");

			URL[] urls = new URL[]{new URL("file:" + tmpDir)};
			URLClassLoader urlClassLoader = new URLClassLoader(urls);
			Class<T> aClass = (Class<T>) urlClassLoader.loadClass(packageName + "." + className);
			Constructor<T> constructor = aClass.getConstructor(targetClass, InvocationHandler.class);
			return constructor.newInstance(target, handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("======newInstance======");
		return null;
	}

	/**
	 * 编译java文件
	 *
	 * @param javaFilePath java文件路径
	 */
	private static void compiler(String javaFilePath) {
		try {
			JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
			StandardJavaFileManager javaFileManager = javaCompiler.getStandardFileManager(null, null, null);
			File javaFile = new File(javaFilePath);
			Iterable<? extends JavaFileObject> javaFileObjects = javaFileManager.getJavaFileObjects(javaFile);
			JavaCompiler.CompilationTask task = javaCompiler.getTask(null, javaFileManager, null, null, null, javaFileObjects);
			task.call();
			javaFileManager.close();
		} catch (Exception ignored) {
		}
	}
}
