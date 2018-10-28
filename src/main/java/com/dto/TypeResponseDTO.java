package com.dto;

import lombok.Data;
import java.util.ArrayList;


@Data

public class TypeResponseDTO {
<<<<<<< HEAD
    private LocationProfileForTypeDTO topLocationOfType;
    private ArrayList<CategoryResponseDTO> listCategoryResponse;

    public TypeResponseDTO(LocationProfileForTypeDTO topLocationOfType, ArrayList<CategoryResponseDTO> listCategoryResponse) {
=======
    private LocationProfileDTO topLocationOfType;
    private ArrayList<CategoryResponseDTO> listCategoryResponse;

    public TypeResponseDTO(LocationProfileDTO topLocationOfType, ArrayList<CategoryResponseDTO> listCategoryResponse) {
>>>>>>> edb20ba80bd3d383a3f1c9026b89bec78532cc7f
        this.topLocationOfType = topLocationOfType;
        this.listCategoryResponse = listCategoryResponse;
    }

<<<<<<< HEAD
    public LocationProfileForTypeDTO getTopLocationOfType() {
        return topLocationOfType;
    }

    public void setTopLocationOfType(LocationProfileForTypeDTO topLocationOfType) {
=======
    public LocationProfileDTO getTopLocationOfType() {
        return topLocationOfType;
    }

    public void setTopLocationOfType(LocationProfileDTO topLocationOfType) {
>>>>>>> edb20ba80bd3d383a3f1c9026b89bec78532cc7f
        this.topLocationOfType = topLocationOfType;
    }

    public ArrayList<CategoryResponseDTO> getListCategoryResponse() {
        return listCategoryResponse;
    }

    public void setListCategoryResponse(ArrayList<CategoryResponseDTO> listCategoryResponse) {
        this.listCategoryResponse = listCategoryResponse;
    }
}
