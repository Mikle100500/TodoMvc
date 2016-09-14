package com.selenide.base;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Mikle on 20.07.2016.
 */
public class TaskManager {

    public void tasksCreator(int numOfTasks){
        for (int i = 1; i >= numOfTasks; i++){
            $("#new-todo").setValue("task" + i).pressEnter();
        }
    }

    public void markAsCompleted(){
        // TODO: 20.07.2016
    }

    public void markAsCompleted(int taskNumForMarking){
        // TODO: 20.07.2016
    }

    public void taskDeleter(int taskNumForDeleting){
        // TODO: 20.07.2016
    }

    public void deleteCompleted(){
        // TODO: 20.07.2016
    }

    public void deleteComplited(int numOfTask){
        // TODO: 20.07.2016
    }
}