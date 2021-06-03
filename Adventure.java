import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Represents an Adventure object
 */
public class Adventure {
    private final HashMap<StringKey, Item> map;

    /**
     * Instantiates an Adventure object from the filename passed
     *
     * @param filename a file of Item objects
     */
    public Adventure(String filename) {
        if (filename == null) throw new IllegalArgumentException("Invalid fileName.");

        map = new HashMap<>();

        try {
            List myList = Files.readAllLines(Path.of(filename));

            Iterator<String> myListCollection = myList.iterator();

            String[] item;

            Item value;
            StringKey key;

            while (myListCollection.hasNext()) {
                item = myListCollection.next().split(",");

                String name = item[0].trim();
                int gold = Integer.parseInt(item[1].trim());
                double weight = Double.parseDouble(item[2].trim());

                value = new Item(name, gold, weight);

                key = new StringKey(value.getName());

                map.put(key, value);
            }

        } catch (NoSuchFileException e) {
            throw new IllegalArgumentException("Invalid fileName.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the map field
     *
     * @return the map field
     */
    public HashMap<StringKey, Item> getMap() {
        return map;
    }

    /**
     * Returns a String of all the data stored in the map field alphabetically
     * except for entries with a value of 0 for goldPieces
     *
     * @return a String of all data stored in the map field
     */
    public String printLootMap() {
        ArrayList<String> keyList = new ArrayList<>();

        Iterator<StringKey> keys = map.keys();

        while (keys.hasNext()) {
            keyList.add(keys.next().getKeyName());
        }

        Collections.sort(keyList);
        Iterator<String> stringKeys = keyList.iterator();

        String loot = "";

        while (stringKeys.hasNext()) {

            StringKey key = new StringKey(stringKeys.next());

            Item item = map.get(key);

            int gold = item.getGoldPieces();

            if (gold > 0) {
                loot += item.toString() + "\n";
            }
        }

        return loot;
    }
}
