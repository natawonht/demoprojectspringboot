package com.ais.demoproject.model;

import lombok.Data;

@Data
@lombok.Generated
public class Result {
    
    Object resultResponse;
    Object resultDescription;
    Object resultObject;

    public Result(){}

    public Result(Object resultResponse, Object resultDescription, Object resultObject){
        this.resultResponse = resultResponse;
        this.resultDescription = resultDescription;
        this.resultObject = resultObject;
    }
}





