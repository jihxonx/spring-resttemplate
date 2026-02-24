package org.example.springresttemplateserver.dto;

import lombok.Getter;
import org.example.springresttemplateserver.entity.Item;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ItemResponseDto {
    private final List<Item> items = new ArrayList<>();

    public void setItems(Item item) {
        items.add(item);
    }
}
