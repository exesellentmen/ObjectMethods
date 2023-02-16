package com.paul.startclass;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ServiceLoader;

@SpringBootTest
class ObjectMethodesApplicationTests {

	@Test
	void contextLoads() {
	}

	// Получение результата toString() по умолчанию
	@Test
	void toStringObjectTest(){
		TestObject test = new TestObject();
		String toStringResult = test.toString();

		System.out.println(toStringResult); // com.paul.startclass.TestObject@21d6dc81
	}

	// Получение hashCode по умолчанию
	@Test
	void hashCodeObjectTest(){
		TestObject test = new TestObject();
		int hashCode = test.hashCode();

		System.out.println(hashCode); // 1990156323
	}

	// Работа с Class
	@Test
	void classTest() throws IllegalAccessException {
		TestObject testObject = new TestObject();
		testObject.setName("Test Name");
		testObject.setPassword("Test password");

		Class classTest = testObject.getClass();
		String className = classTest.getName(); // получаем имя класса
		Class[] interfaces = classTest.getInterfaces(); // Получаем список интерфейсов, которые реализует класс

		try {
			Method setName = classTest.getMethod("setName", String.class);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		//Работа с классом Field
		Field[] fields = classTest.getDeclaredFields(); // Получаем список полей, которые содержит класс объекта(имя поля, тип поля, )

		String name = (String) fields[0].get(testObject); // С помощью рефлексии получил значение поля имя

		boolean accessField = fields[1].canAccess(testObject); //Проверяем доступно ли поле

		if(fields[1].canAccess(testObject)){ // Проверяем доступность к полю, в данном случае доступа нет т к private
			String password = (String) fields[1].get(testObject); // Пробую получить пароль, но не могу потому что private
		}
		fields[1].setAccessible(true); // Мы разрешаем доступ для поля private
		String password2 = (String) fields[1].get(testObject); // Теперь мы можем получить доступ к полю
		boolean accessField2 = fields[1].canAccess(testObject); // Определяем есть ли у нас доступ к полю, теперь будет true

		fields[0].set(testObject,"New Name"); // Устанавливаю новое имя с помощью рефлексии

		fields[2].setInt(testObject,55); // Устанавливаем значение для примитива, например для int

		System.out.println();
	}

	@Autowired
	ApplicationContext context;

}

class TestObject implements TestInterface{
	public String name;
	private String password;
	public int testInt;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void testMethode(){

	}
	private void testPrivateMethode(){

	}
}


interface TestInterface{

}
