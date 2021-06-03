

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static <K, V> void main(String[] args) {

        DecimalFormat decimalFormat = new DecimalFormat("0.#####");
        String result = decimalFormat.format(10.00);
        System.out.println(result);
//        String itemList = "src/item_files/ItemData.txt";
//
//        Adventure adventure = new Adventure(itemList);
//
//        String thing = adventure.printLootMap();
//
//        System.out.println(thing);
    }
}