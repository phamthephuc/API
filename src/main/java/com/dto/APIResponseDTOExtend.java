package com.dto;

import com.model.ActionUser;
import lombok.Data;

import java.util.List;

@Data

public class APIResponseDTOExtend  {
    private int result_code;
    private String result_message;
    private Object data;
    private List<ActionUser> listAction;

    public APIResponseDTOExtend(int result_code, String result_message, Object data, List<ActionUser> listAction) {
        this.result_code = result_code;
        this.result_message = result_message;
        this.data = data;
        this.listAction = listAction;
    }
}
