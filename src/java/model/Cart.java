/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Cart {
    private ArrayList<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
        
    private int getQuantityById(String id) {
        return getItemById(id).getSoLuong();
    }
    public Item getItemById(String id) {
        for (Item i : items) {
            if (i.getSach().getMaSach().equals(id)) {
                return i;
            }
        }
        return null;
    }
    
    public void addItem(Item t) {
        if (getItemById(t.getSach().getMaSach()) != null) {
            Item m = getItemById(t.getSach().getMaSach());
            m.setSoLuong(m.getSoLuong() + t.getSoLuong());
        } else {
            items.add(t);
        }
    }
    
    public void deleteItem(String id) {
        if (getItemById(id) != null) {
            items.remove(getItemById(id));
        } 
    }
    
    public float getTotalMoney() {
        float t = 0;
        for (Item i : items) {
            t += (i.getSoLuong() * i.getSach().getGiaSach());
        }
        return t;
    }
    
}
