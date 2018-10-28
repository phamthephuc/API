package com.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data


public class CategoryResponseDTO {
    private Long id;
    private String name;
    private List<LocationProfileForTypeDTO> listLocationOfCategory;

    public CategoryResponseDTO(Long id, String name, List<LocationProfileForTypeDTO> listLocationOfCategory) {
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

    public List<LocationProfileForTypeDTO> getListLocationOfCategory() {
        return listLocationOfCategory;
    }

    public void setListLocationOfCategory(List<LocationProfileForTypeDTO> listLocationOfCategory) {
        this.listLocationOfCategory = listLocationOfCategory;
    }
}
