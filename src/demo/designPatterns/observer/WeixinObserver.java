package demo.designPatterns.observer;

public class WeixinObserver extends Observer {

	private String nickname;

	public WeixinObserver(String nickname, Subject subject) {
		this.nickname = nickname;
		this.subject = subject;
		this.subject.addObserver(this);
	}

	@Override
	public void update(String message) {
		System.out.println(nickname + "收到了消息：" + message);
	}

}
