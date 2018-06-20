package com.example.demo.constant;

public enum ProductUrl {
    ABC(1,"www.abc.com"),
    DEF(2,"www.def.com");

    public int num;
    public String url;

    ProductUrl(int num, String url) {
        this.num = num;
        this.url = url;
    }
}
