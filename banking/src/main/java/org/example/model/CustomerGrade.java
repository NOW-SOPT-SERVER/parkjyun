package org.example.model;

public enum CustomerGrade {
    VIP("VIP"), BASIC("BASIC");

    private final String gradeName;

    CustomerGrade(String name) {
        this.gradeName = this.name();
    }
}
