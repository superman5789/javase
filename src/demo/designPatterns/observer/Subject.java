package demo.designPatterns.observer;

import java.util.ArrayList;
import java.util.List;

//主题
public class Subject {
	private List<Observer> observers = new ArrayList<Observer>();

	private String message;

	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	public void setMessage(String message) {
		this.message = message;
		notifyAllObservers();
	}

	public void notifyAllObservers() {
		for (Observer observer : observers) {
			observer.update(message);
		}
	}
}
