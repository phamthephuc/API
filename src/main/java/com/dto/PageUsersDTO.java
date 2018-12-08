package com.dto;

import lombok.Data;

import java.util.List;

@Data

public class PageUsersDTO {

    private int sumPage;
    private int currentPage;
    private List<UsersProfileDTO> usersProfileDTOList;
    public  PageUsersDTO(){

    }

    public PageUsersDTO(int sumPage, int currentPage, List<UsersProfileDTO> usersProfileDTOS){
        this.sumPage = sumPage;
        this.currentPage = currentPage;
        this.usersProfileDTOList = usersProfileDTOS;
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

    public List<UsersProfileDTO> getUsersProfileDTOList() {
        return usersProfileDTOList;
    }

    public void setUsersProfileDTOList(List<UsersProfileDTO> usersProfileDTOList) {
        this.usersProfileDTOList = usersProfileDTOList;
    }
}
