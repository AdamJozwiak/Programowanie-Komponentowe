package com.resources;

import java.util.ListResourceBundle;

public class Resources_pl extends ListResourceBundle{

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"title", "Autorzy"},
                {"authors", "Michał Pęśko i Adam Jóźwiak"}
        };
    }
}
