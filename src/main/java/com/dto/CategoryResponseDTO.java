package com.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data


public class CategoryResponseDTO {
    private Long id;
    private String name;
    private List<LocationProfileDTO> listLocationOfCategory;
}
