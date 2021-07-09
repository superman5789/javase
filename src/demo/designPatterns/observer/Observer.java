package demo.designPatterns.observer;

//订阅者
public abstract class Observer {
	public Subject subject;

	public abstract void update(String message);
}
