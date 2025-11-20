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

    public int getLevelValue() {
        return levelValue;
    }

	/**
	 *
	 */
    public boolean greaterThanOrEqualTo(InternshipLevel other) {
        return this.levelValue >= other.levelValue;
    }
}
