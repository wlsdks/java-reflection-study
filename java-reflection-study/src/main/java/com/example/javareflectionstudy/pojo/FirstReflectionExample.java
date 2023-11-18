package com.example.javareflectionstudy.pojo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class FirstReflectionExample {

    private final String secret;
    private final int number;

    public FirstReflectionExample(String secret, int number) {
        this.secret = secret;
        this.number = number;
    }

    private void secretMethod() {
        System.out.println("Secret: " + secret + ", Number: " + number);
    }

    // 리플렉션을 사용하여 비공개 필드와 메서드에 접근하는 메서드
    public void useReflection() throws Exception {
        FirstReflectionExample myObject = new FirstReflectionExample("hidden", 123);
        Class<?> clazz = myObject.getClass();

        // 비공개 필드 'secret'에 접근
        Field secretField = clazz.getDeclaredField("secret");
        secretField.setAccessible(true);
        System.out.println("secret 필드의 값: " + secretField.get(myObject));

        // 비공개 메서드 'secretMethod' 호출
        Method secretMethod = clazz.getDeclaredMethod("secretMethod");
        secretMethod.setAccessible(true);
        System.out.println("secretMethod 메서드 실행:");
        secretMethod.invoke(myObject);
    }

    public static void main(String[] args) {
        try {
            // FirstReflectionExample 인스턴스 생성
            FirstReflectionExample example = new FirstReflectionExample("test", 42);

            // useReflection 메서드를 호출하여 리플렉션 사용 확인
            example.useReflection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


