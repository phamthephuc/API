package com.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data


public class CategoryResponseDTO {
    private Long id;
    private String name;
    private List<LocationProfileDTO> listLocationOfCategory;

    public CategoryResponseDTO(Long id, String name, List<LocationProfileDTO> listLocationOfCategory) {
        this.id = id;
        this.name = name;
        this.listLocationOfCategory = listLocationOfCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LocationProfileDTO> getListLocationOfCategory() {
        return listLocationOfCategory;
    }

    public void setListLocationOfCategory(List<LocationProfileDTO> listLocationOfCategory) {
        this.listLocationOfCategory = listLocationOfCategory;
    }
}
