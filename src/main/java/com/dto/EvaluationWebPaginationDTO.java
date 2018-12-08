package com.dto;

import lombok.Data;

import java.util.List;

@Data

public class EvaluationWebPaginationDTO {
    int crrPage;
    int sumPage;
    List<EvaluationWebDTO> listEvaluationWeb;

    public int getCrrPage() {
        return crrPage;
    }

    public void setCrrPage(int crrPage) {
        this.crrPage = crrPage;
    }

    public int getSumPage() {
        return sumPage;
    }

    public void setSumPage(int sumPage) {
        this.sumPage = sumPage;
    }

    public List<EvaluationWebDTO> getListEvaluationWeb() {
        return listEvaluationWeb;
    }

    public void setListEvaluationWeb(List<EvaluationWebDTO> listEvaluationWeb) {
        this.listEvaluationWeb = listEvaluationWeb;
    }
}
