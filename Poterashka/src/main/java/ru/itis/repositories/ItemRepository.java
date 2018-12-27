package ru.itis.repositories;

import ru.itis.models.Category;
import ru.itis.models.Client;
import ru.itis.models.Item;

import java.util.List;

public interface ItemRepository {

    Item createItem(Item item);
    Item getItem(String name);
    Item getItem(Integer itemId);
    List<Item> getItems(Integer ownerId);
    List<Item> getItemsInLocation(Integer locationId);
    List<Item> getItemsInBuilding(Integer buildingId);

    List<Item> findItem(String name);

    List<Item> getAllItemsNotInOrder();
}
