package com.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data


public class CategoryResponseDTO {
    private Long id;
    private String name;
<<<<<<< HEAD
    private List<LocationProfileForTypeDTO> listLocationOfCategory;

    public CategoryResponseDTO(Long id, String name, List<LocationProfileForTypeDTO> listLocationOfCategory) {
=======
    private List<LocationProfileDTO> listLocationOfCategory;

    public CategoryResponseDTO(Long id, String name, List<LocationProfileDTO> listLocationOfCategory) {
>>>>>>> edb20ba80bd3d383a3f1c9026b89bec78532cc7f
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

<<<<<<< HEAD
    public List<LocationProfileForTypeDTO> getListLocationOfCategory() {
        return listLocationOfCategory;
    }

    public void setListLocationOfCategory(List<LocationProfileForTypeDTO> listLocationOfCategory) {
=======
    public List<LocationProfileDTO> getListLocationOfCategory() {
        return listLocationOfCategory;
    }

    public void setListLocationOfCategory(List<LocationProfileDTO> listLocationOfCategory) {
>>>>>>> edb20ba80bd3d383a3f1c9026b89bec78532cc7f
        this.listLocationOfCategory = listLocationOfCategory;
    }
}
