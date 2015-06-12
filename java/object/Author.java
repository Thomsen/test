
public class Author {

    /**
     * Describe name here.
     */
    private String name;
    
    /**
     * Describe realName here.
     */
    private String realName;
    
    /**
     * Describe birthYear here.
     */
    private String birthYear;
    
    /**
     * Describe deathYear here.
     */
    private String deathYear;

    public Author(String name, String realName, String birthYear, String deathYear) {
        this.name = name;
        this.realName = realName;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    /**
     * Get the <code>Name</code> value.
     *
     * @return a <code>String</code> value
     */
    public final String getName() {
        return name;
    }

    /**
     * Set the <code>Name</code> value.
     *
     * @param name The new Name value.
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * Get the <code>RealName</code> value.
     *
     * @return a <code>String</code> value
     */
    public final String getRealName() {
        return realName;
    }

    /**
     * Set the <code>RealName</code> value.
     *
     * @param realName The new RealName value.
     */
    public final void setRealName(final String realName) {
        this.realName = realName;
    }

    /**
     * Get the <code>BirthYear</code> value.
     *
     * @return a <code>String</code> value
     */
    public final String getBirthYear() {
        return birthYear;
    }

    /**
     * Set the <code>BirthYear</code> value.
     *
     * @param birthYear The new BirthYear value.
     */
    public final void setBirthYear(final String birthYear) {
        this.birthYear = birthYear;
    }

    /**
     * Get the <code>DeathYear</code> value.
     *
     * @return a <code>String</code> value
     */
    public final String getDeathYear() {
        return deathYear;
    }

    /**
     * Set the <code>DeathYear</code> value.
     *
     * @param deathYear The new DeathYear value.
     */
    public final void setDeathYear(final String deathYear) {
        this.deathYear = deathYear;
    }


}
