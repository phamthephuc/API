package com.dto;

import lombok.Data;
import java.util.ArrayList;


@Data

public class TypeResponseDTO {

    private Long id;
    private LocationProfileDTO topLocationOfType;
    private ArrayList<CategoryResponseDTO> listCategoryResponse;


}
