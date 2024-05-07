package com.example.backenduppgift;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class Catalog {
    @JacksonXmlProperty(localName = "book")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Book> book;
}
