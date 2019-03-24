package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.models.Category;
import ru.itis.models.Client;
import ru.itis.models.Item;
import ru.itis.repositories.ItemRepository;

import java.util.List;

public class ItemServicesImpl implements ItemServices{

    @Autowired
    ItemRepository itemRepository;



    public ItemServicesImpl(){
    }


    @Override
    public List<Item> getItemsByClient(Integer ownerId) {
        return itemRepository.getItems(ownerId);
    }

    @Override
    public List<Item> findItems(String name) {
        return itemRepository.findItem(name);
    }

    @Override
    public List<Item> getItemsInCategory(Integer categoryId) {
        return null;
    }

    @Override
    public List<Category> getCategories() {
        return null;
    }

    @Override
    public List<Item> getItemsInLocation(Integer locationId) {
        return itemRepository.getItemsInLocation(locationId);
    }

    @Override
    public List<Item> getItemsInBuilding(Integer buildingId) {
        return itemRepository.getItemsInBuilding(buildingId);
    }

    @Override
    public Item getItemById(Integer itemId) {
        return itemRepository.getItem(itemId);
    }

    @Override
    public List<Item> getAllItemsNotInOrder() {
        return itemRepository.getAllItemsNotInOrder();
    }

    @Override
    public Item createItem(Item item) {
        return itemRepository.createItem(item);
    }
}
