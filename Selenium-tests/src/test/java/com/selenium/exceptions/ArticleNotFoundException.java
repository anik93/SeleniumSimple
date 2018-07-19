package com.selenium.exceptions;

public class ArticleNotFoundException extends Exception {

    private static final long serialVersionUID = -2590367716941915996L;

    private String name;

    public ArticleNotFoundException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
