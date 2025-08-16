package com.example1.model;

/**
 * Enum для типів грантів
 */
public enum GrantType {
    RESEARCH("Науковий грант"),
    EDUCATIONAL("Освітній грант"),
    STARTUP("Стартап грант"),
    SOCIAL("Соціальний грант"),
    CULTURAL("Культурний грант");

    private final String description;

    GrantType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
