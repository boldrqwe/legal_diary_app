package com.legal_diary_app.converters;

public interface Converter<TARGET, SOURCE> {
    TARGET convert(SOURCE source);
}
