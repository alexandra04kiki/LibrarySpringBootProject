package com.example.LibrarySpringBootProject.dto;

import lombok.Builder;

@Builder
public record BookDto (String title, String author, Double price, Integer stock){}
