package com.dto;

import lombok.Data;

import java.util.List;

@Data

public class EvaluationWebPaginationDTO {
    int crrPage;
    int sumPage;
    List<EvaluationWebDTO> listEvaluationWeb;
}
