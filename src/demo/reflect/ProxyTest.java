package demo.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

	public static void main(String[] args) throws Exception {
		//传入一个对象
		User user = (User) getProxyObject(new Doctor());
		System.out.println("################代理对象执行结果：" + user.getJob());
	}

//	private static Object getProxyObject(Object target) throws NoSuchMethodException, InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException {
//		Class<?> proxyClass = Proxy.getProxyClass(target.getClass().getClassLoader(), target.getClass().getInterfaces());
//		Constructor<?> proxyClassConstructor = proxyClass.getConstructor(InvocationHandler.class);
//		return proxyClassConstructor.newInstance((InvocationHandler) (proxy, method, args1) -> {
//			System.out.println("################开始执行方法：" + method.getName());
//			Object result = method.invoke(target, args1);
//			System.out.println("################执行结果：" + result);
//			return result;
//		});
//	}

	private static Object getProxyObject(Object target) {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(),
				(proxy, method, args) -> {
					System.out.println("################开始执行方法：" + method.getName());
					Object result = method.invoke(target, args);
					System.out.println("################执行结果：" + result);
					return result;
				}
		);
	}


}
