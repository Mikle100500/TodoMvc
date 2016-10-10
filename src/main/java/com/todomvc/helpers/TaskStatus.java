package com.todomvc.helpers;

public enum TaskStatus{

    ACTIVE("false"),
    COMPLETED("true");

    private final String status;

    TaskStatus(String status){
        this.status = status;
    }

    @Override
    public String toString(){
        return status;
    }

}