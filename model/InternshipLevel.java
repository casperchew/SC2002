package model;

public enum InternshipLevel {
    BASIC(1),
    INTERMEDIATE(2),
    ADVANCED(3);

    private final int levelValue;

    InternshipLevel(int levelValue) {
        this.levelValue = levelValue;
    }

    public int getLevelValue() {
        return levelValue;
    }

    public boolean greaterThanOrEqualTo(InternshipLevel other) {
        return this.levelValue >= other.levelValue;
    }
}