package com.example.javareflectionstudy.pojo;

import java.lang.reflect.Field;

public class JsonSerializationExample {

    record SimplePOJO(
            String name,
            int age,
            boolean isStudent
    ) {
    }

    public static String convertToJson(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();  // 객체의 클래스 정보를 가져옴
        StringBuilder jsonBuilder = new StringBuilder("{");  // JSON 문자열을 구성하기 위한 StringBuilder
        Field[] fields = clazz.getDeclaredFields();  // 클래스의 모든 필드를 가져옴

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);  // private 필드에도 접근할 수 있도록 설정

            // JSON 키:값 쌍을 구성
            jsonBuilder.append("\"").append(field.getName()).append("\":");

            // 필드 타입이 String일 경우 따옴표로 감싸고, 그렇지 않으면 그대로 값을 추가
            if (field.getType().equals(String.class)) {
                jsonBuilder.append("\"").append(field.get(obj)).append("\"");
            } else {
                jsonBuilder.append(field.get(obj));
            }

            // 마지막 필드가 아니면 쉼표 추가
            if (i < fields.length - 1) {
                jsonBuilder.append(",");
            }
        }

        jsonBuilder.append("}");  // JSON 문자열 닫기
        return jsonBuilder.toString();  // 완성된 JSON 문자열 반환
    }

    public static void main(String[] args) throws IllegalAccessException {
        SimplePOJO pojo = new SimplePOJO("John Doe", 25, true);
        String jsonResult = convertToJson(pojo);
        System.out.println();
        System.out.println("======================================================================================");
        System.out.println("reflection을 활용하여 json으로 변환시킨 결과값: " + jsonResult);
        System.out.println("======================================================================================");
    }
}


