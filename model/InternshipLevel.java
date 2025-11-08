package model;

/**
 * Represents the difficulty or expertise level required for an internship opportunity.
 * <p>
 * Levels are ordered as {@link #BASIC}, {@link #INTERMEDIATE}, and {@link #ADVANCED}.
 * Each level has an associated numeric value to facilitate comparisons.
 * </p>
 *
 * <p>
 * This enum can be used to filter internship opportunities based on a student's
 * year of study or skill level.
 * </p>
 *
 * @see model.internship.InternshipOpportunity
 */
public enum InternshipLevel {

    /** Basic internship level, suitable for early-year students. */
    BASIC(1),

    /** Intermediate internship level, suitable for mid-year students. */
    INTERMEDIATE(2),

    /** Advanced internship level, suitable for late-year students or those with experience. */
    ADVANCED(3);

    /** Numeric value associated with the level for comparison purposes. */
    private final int levelValue;

    /**
     * Constructs an {@code InternshipLevel} with the specified numeric value.
     *
     * @param levelValue the numeric value representing the level
     */
    InternshipLevel(int levelValue) {
        this.levelValue = levelValue;
    }

    /**
     * Returns the numeric value associated with this internship level.
     *
     * @return the level value
     */
    public int getLevelValue() {
        return levelValue;
    }

    /**
     * Determines whether this level is greater than or equal to another level.
     *
     * @param other the other {@code InternshipLevel} to compare with
     * @return {@code true} if this level's value is greater than or equal to the other level's value;
     *         {@code false} otherwise
     */
    public boolean greaterThanOrEqualTo(InternshipLevel other) {
        return this.levelValue >= other.levelValue;
    }
}
