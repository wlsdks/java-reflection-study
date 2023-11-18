package com.example.javareflectionstudy.pojo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

    public class SecondReflectionExample {

    /**
     * Dog 클래스가 SecondReflectionExample 클래스의 내부 클래스로 선언되었기 때문에, static으로 선언하는 것이 맞다.
     * 이렇게 하면 Dog 클래스의 인스턴스를 SecondReflectionExample 클래스의 인스턴스 없이도 생성할 수 있다.
     */
    static class Dog {
        public String type; // 필드 추가

        public void display() {
            System.out.println("나는 reflection을 검증하는 강아지다.");
        }
    }




    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        Dog dog = new Dog();
        Class<? extends Dog> obj = dog.getClass();

        /**
         * 클래스 이름, 슈퍼 클래스, 접근 제한자 정보 출력하기
         *
         * 생성한 객체의 클래스 정보를 가져오기 위해 getClass() 메서드를 사용할 수 있다.
         * 이를 통해 클래스 이름, 수퍼클래스, 접근 제한자 등의 정보를 얻을 수 있다.
         */
        System.out.println();
        System.out.println("====================== 클래스 이름, 슈퍼 클래스, 접근 제한자 정보 출력 ======================");
        System.out.println("Name: " + obj.getName());
        System.out.println("Superclass: " + obj.getSuperclass().getName());
        System.out.println("Modifier: " + Modifier.toString(obj.getModifiers()));
        System.out.println("======================================= 종료 =======================================");
        System.out.println();

        /**
         * Method 정보 가져오기
         *
         * Method 클래스를 사용하여 클래스 내의 메서드 정보를 가져올 수 있다.
         * 이를 통해 메서드 이름, 접근 제한자, 반환 타입 등을 확인할 수 있어
         */
        Method[] methods = obj.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println("====================== Method(메서드) 정보 가져오기 ======================");
            System.out.println("Method Name: " + m.getName());
            System.out.println("Modifier: " + Modifier.toString(m.getModifiers()));
            System.out.println("Return Types: " + m.getReturnType());
            System.out.println("================================ 종료 ================================");
            System.out.println();
        }

        /**
         * 필드 정보 가져오기
         *
         * Field 클래스를 이용해 필드 정보를 가져오고 수정할 수 있다.
         * 필드 값 설정 및 가져오기, 접근 제한자 확인이 가능하다.
         */
        Field field1 = obj.getField("type");
        field1.set(dog, "labrador");
        String typeValue = (String) field1.get(dog);
        System.out.println("====================== 필드(Field) 정보 가져오기 ======================");
        System.out.println("Value: " + typeValue);
        System.out.println("Modifier: " + Modifier.toString(field1.getModifiers()));
        System.out.println("=============================== 종료 ===============================");
        System.out.println();

        /**
         * 생성자 정보 가져오기
         *
         * Constructor 클래스를 사용해 클래스의 생성자 정보를 가져올 수 있다.
         * 생성자 이름, 접근 제한자, 파라미터 수 등을 확인할 수 있다.
         */
        Constructor[] constructors = obj.getDeclaredConstructors();
        for (Constructor c : constructors) {
            System.out.println("============================== 생성자(Constructor) 정보 가져오기 ==============================");
            System.out.println("Constructor Name: " + c.getName());
            System.out.println("Modifier: " + Modifier.toString(c.getModifiers()));
            System.out.println("Parameters: " + c.getParameterCount());
            System.out.println("========================================== 종료 ===========================================");
            System.out.println();
        }
    }

}
