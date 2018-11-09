package com.dto;

import java.util.List;

public class EvaluationPaginationDTO {
    int crrPage;
    int sumPage;
    List<EvaluationDTO> listEvaluation;

    public void setCrrPage(int crrPage) {
        this.crrPage = crrPage;
    }

    public void setListEvaluation(List<EvaluationDTO> listEvaluation) {
        this.listEvaluation = listEvaluation;
    }

    public int getCrrPage() {
        return crrPage;
    }

    public int getSumPage() {
        return sumPage;
    }

    public void setSumPage(int sumPage) {
        this.sumPage = sumPage;
    }

    public List<EvaluationDTO> getListEvaluation() {
        return listEvaluation;
    }

}
