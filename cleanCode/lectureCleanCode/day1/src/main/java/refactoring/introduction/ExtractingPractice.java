package refactoring.introduction;
/*
    파라미터 추출과 메서드 추출 실습의 시작 코드는 동일했지만
    refactoring 결과는 다른 것을 알 수 있다.
    어떤 목표?를 가지고 어떤 방법을 선택해서 refactoring 하느냐에 따라서 결과가 달라질 수 있다.
 */
public class ExtractingPractice {

}

class ExtractParameterPractice{
    static String getValueForField(String message, String fieldName) {
        final int indexOfFiledValue = message.indexOf(fieldName) + fieldName.length();
        final int indexOfEndOfFieldValue = message.indexOf("\"", indexOfFiledValue);
        return message.substring(indexOfFiledValue, indexOfEndOfFieldValue);
    }
}

class ExtractMethodPractice{
    static String getTextFromMessage(String message) {
        return getValueForField(message, "\"text\":\"");
    }

    static String getUsernameFromMessage(String message) {
        final String fieldName = "\"screen_name\":\"";
        return getValueForField(message, fieldName);
    }

    private static String getValueForField(String message, String fieldName) {
        final int indexOfFieldValue = message.indexOf(fieldName) + fieldName.length();
        final int indexOfEndOfFieldValue = message.indexOf("\"", indexOfFieldValue);
        return message.substring(indexOfFieldValue, indexOfEndOfFieldValue);
    }
}