package service.impl;

import service.Service;

/**
 * My service implementation
 */
public class ServiceImpl implements Service {

    private String myField = "two";

    @Override
    public String execute(String argument) {
        String result = argument + " " + myField;
        return result;
    }

}
