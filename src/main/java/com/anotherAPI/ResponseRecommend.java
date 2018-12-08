package com.anotherAPI;

public class ResponseRecommend {
    public Long data[];

    public void printData() {
        for(int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
    }
}
