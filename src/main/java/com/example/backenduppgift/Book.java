package com.example.backenduppgift;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Date;

public class Book {

    @JacksonXmlProperty(localName = "author")
    public String author;

    @JacksonXmlProperty(localName = "title")
    public String title;

    @JacksonXmlProperty(localName = "genre")
    public String category;

    public double price;

    @JacksonXmlProperty(localName = "publish_date")
    public Date publish_date;

    public String description;

    @JacksonXmlProperty(isAttribute = true)
    public String id;

    public String text;
}
