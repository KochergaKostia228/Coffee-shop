package org.example.server;

import org.example.exception.FileException;

import static java.lang.System.setProperty;

public class CoffeeShopInitializer {
    public void schoolInitialize() {
        setProperty("test", "false");
        try {
            CoffeeShopDBInitializer.createTables();
            CoffeeShopDBInitializer.createMenuItemType();
            CoffeeShopDBInitializer.createPersonnelPosition();
        } catch (FileException e) {
            System.err.println(e.getMessage());
        }
    }
}
