/**
 * Represents an StringKey object
 */
public class StringKey {

    private String keyName;

    /**
     * Initializes a StringKey object and sets the keyName
     *
     * @param keyName String value for the StringKey keyName
     */
    public StringKey(String keyName) {
        this.keyName = keyName;
    }

    /**
     * Compares instance to another object. They are only equal
     * if they are both StringKey objects and have the
     * same keyName field
     *
     * @param obj The object compared with the instance
     * @return True if both objects are StringKeys and
     * have the same keyName field
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }

        StringKey other = (StringKey) obj;

        return this.keyName.equals(other.keyName);
    }

    /**
     * Returns the keyName field
     *
     * @return the keyName field
     */
    public String getKeyName() {
        return keyName;
    }

    /**
     * Sets the keyName field
     *
     * @param keyName the String to set the keyName field
     */
    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    /**
     * Returns an int based on the comparison between the Strings
     *
     * @param other the StringKey object to compare with the instance that called the method
     * @return an int based on String comparison
     */
    public int compareTo(StringKey other) {
        return this.keyName.compareTo(other.keyName);
    }

    /**
     * Returns a string representation of the StringKey object
     *
     * @return A string representation of the StringKey object
     */
    @Override
    public String toString() {
        return String.format("KeyName: " + keyName + " HashCode: " + this.hashCode());
    }

    /**
     * Returns a hash code based on the keyName field
     *
     * @return a hash code integer
     */
    @Override
    public int hashCode() {
        int hash = 0;

        int i = 0;
        for (int ascii : keyName.toCharArray()) {
            hash += ascii * Math.pow(31, i);
            i++;
        }
        return hash;
    }
}
