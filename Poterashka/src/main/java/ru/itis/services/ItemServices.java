package ru.itis.services;

import ru.itis.models.Category;
import ru.itis.models.Client;
import ru.itis.models.Item;

import java.util.List;

public interface ItemServices {
    List<Item> getItemsByClient(Integer ownerId);
    List<Item> findItems(String name);
    List<Item> getItemsInCategory(Integer categoryId);
    List<Category> getCategories();
    List<Item> getItemsInLocation(Integer locationId);
    List<Item> getItemsInBuilding(Integer buildingId);

    Item getItemById(Integer itemId);

    List<Item> getAllItemsNotInOrder();

    Item createItem(Item item);
}
