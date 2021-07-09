package demo.designPatterns.observer;

public class MyTest {

	public static void main(String[] args) {
		Subject subject = new Subject();

		new WeixinObserver("zhangsan", subject);
		new WeixinObserver("lisi", subject);
		new WeixinObserver("wangwu", subject);

		subject.setMessage("台湾回归了");
	}
}
