package src.enums;

/**
 * The {@link InternshipLevel} enum is used as an attribute to {@link src.model.internship.InternshipOpportunity}.
 */
public enum InternshipLevel {
	/** The lowest internship level */
    BASIC(1),
	/** The middle internship level */
    INTERMEDIATE(2),
	/** The highest internship level */
    ADVANCED(3);

    private final int levelValue;

    InternshipLevel(int levelValue) {
        this.levelValue = levelValue;
    }

	/**
	 * Getter for {@code levelValue}
	 *
	 * @return {@code levelValue}
	 */
    public int getLevelValue() {
        return levelValue;
    }

	/**
	 * Compares the {@code levelValue} of an {@link InternshipLevel} to another.
	 *
	 * @param other The {@link InternshipLevel} instance to compare against.
	 * @return {@code true} if the {@code levelValue} is greater than or equal to {@code other}'s {@code levelValue}. {@code false} otherwise.
	 */
    public boolean greaterThanOrEqualTo(InternshipLevel other) {
        return this.levelValue >= other.levelValue;
    }
}
