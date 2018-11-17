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


}
