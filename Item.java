import java.text.DecimalFormat;

/**
 * Represents an Item object
 */
public class Item {

    private String name;
    private int goldPieces;
    private double weight;

    /**
     * Initializes a Item object, sets name, goldPieces,
     * and weight using parameter values
     *
     * @param name       String value for the Item name
     * @param goldPieces int value for the Item value
     * @param weight     double value for the Item weight
     */
    public Item(String name, int goldPieces, double weight) {
        this.name = name;
        this.goldPieces = goldPieces;
        this.weight = weight;
    }

    /**
     * Returns the name of the Item object
     *
     * @return the Item name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the Item object
     *
     * @param name String value for the Item name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the goldPieces of the Item object
     *
     * @return the Item goldPieces
     */
    public int getGoldPieces() {
        return goldPieces;
    }

    /**
     * Sets the goldPieces for the Item object
     *
     * @param goldPieces int value for the Item value
     */
    public void setGoldPieces(int goldPieces) {
        this.goldPieces = goldPieces;
    }

    /**
     * Returns the weight of the Item object
     *
     * @return the Item weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the weight for the Item object
     *
     * @param weight double value for the Item weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Returns a string representation of the Item object
     *
     * @return A string representation of the Item object
     */
    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        String weightString = decimalFormat.format(weight);
        return String.format(name + " is worth " + goldPieces + "gp and weighs " + weightString + "kg");
    }


    /**
     * Returns true if the Items compared are identical, and false if they are not
     *
     * @param obj An object to compare with the Item object that calls the method
     * @return true or false
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }

        Item other = (Item) obj;

        return this.name.equals(other.name) && this.weight == other.weight && this.goldPieces == other.goldPieces;
    }

    /**
     * Returns an int based on the comparison between the Strings
     *
     * @param other the Item object to be compared with the Item object that call the method
     * @return an int based on String comparison
     */
    public int compareTo(Item other) {
        return this.name.compareTo(other.name);
    }
}
