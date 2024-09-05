package com.spring.quiz;


public enum QuizStatus {
    BEFORE("BEFORE", "퀴즈 제출 미완료"),
    PROGRESS("PROGRESS", "퀴즈 진행 중"),
    COMPLETED("COMPLETED", "퀴즈 제출 완료");

    private final String key;
    private final String value;

    QuizStatus(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
