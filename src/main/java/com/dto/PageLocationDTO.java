package com.dto;

import java.util.List;

public class PageLocationDTO {
    private int sumPage;
    private int currentPage;
    private List<LocationProfileDTO> listLocationProfieDTO;

    public PageLocationDTO() {

    }

    public int getSumPage() {
        return sumPage;
    }

    public void setSumPage(int sumPage) {
        this.sumPage = sumPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<LocationProfileDTO> getListLocationProfieDTO() {
        return listLocationProfieDTO;
    }

    public void setListLocationProfieDTO(List<LocationProfileDTO> listLocationProfieDTO) {
        this.listLocationProfieDTO = listLocationProfieDTO;
    }

    public PageLocationDTO(int sumPage, int currentPage, List<LocationProfileDTO> listLocationProfieDTO) {
        this.sumPage = sumPage;
        this.currentPage = currentPage;
        this.listLocationProfieDTO = listLocationProfieDTO;
    }
}
