package com.paul.startclass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ObjectMethodesApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(ObjectMethodesApplication.class, args);

		String test = "111";
		Thread th = new Thread(() -> {
			ObjectMethodesApplication.testMethode(test);
		});
		th.start();

		Thread th1 = new Thread(() -> {
			ObjectMethodesApplication.testMethode(test);
		});
		th1.start();
	}

	public static void testMethode(String testStr){
		synchronized (testStr){
			for(int i = 0; i<10; i++){
				if(i == 5) {
					testStr.notify();
					try {
						testStr.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName() + " "+ i);
			}
		}
	}

}
