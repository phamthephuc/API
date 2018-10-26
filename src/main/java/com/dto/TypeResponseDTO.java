package com.dto;

import lombok.Data;
import java.util.ArrayList;


@Data

public class TypeResponseDTO {
    private LocationProfileDTO topLocationOfType;
    private ArrayList<CategoryResponseDTO> listCategoryResponse;

    public TypeResponseDTO(LocationProfileDTO topLocationOfType, ArrayList<CategoryResponseDTO> listCategoryResponse) {
        this.topLocationOfType = topLocationOfType;
        this.listCategoryResponse = listCategoryResponse;
    }

    public LocationProfileDTO getTopLocationOfType() {
        return topLocationOfType;
    }

    public void setTopLocationOfType(LocationProfileDTO topLocationOfType) {
        this.topLocationOfType = topLocationOfType;
    }

    public ArrayList<CategoryResponseDTO> getListCategoryResponse() {
        return listCategoryResponse;
    }

    public void setListCategoryResponse(ArrayList<CategoryResponseDTO> listCategoryResponse) {
        this.listCategoryResponse = listCategoryResponse;
    }
}
