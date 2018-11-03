package com.dto;

import lombok.Data;
import java.util.ArrayList;


@Data

public class TypeResponseDTO {
    private LocationProfileForTypeDTO topLocationOfType;
    private ArrayList<CategoryResponseDTO> listCategoryResponse;

    public TypeResponseDTO(LocationProfileForTypeDTO topLocationOfType, ArrayList<CategoryResponseDTO> listCategoryResponse) {
        this.topLocationOfType = topLocationOfType;
        this.listCategoryResponse = listCategoryResponse;
    }

    public TypeResponseDTO() {

    }

    public LocationProfileForTypeDTO getTopLocationOfType() {
        return topLocationOfType;
    }

    public void setTopLocationOfType(LocationProfileForTypeDTO topLocationOfType) {
        this.topLocationOfType = topLocationOfType;
    }

    public ArrayList<CategoryResponseDTO> getListCategoryResponse() {
        return listCategoryResponse;
    }

    public void setListCategoryResponse(ArrayList<CategoryResponseDTO> listCategoryResponse) {
        this.listCategoryResponse = listCategoryResponse;
    }
}
