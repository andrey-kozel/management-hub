package com.example.github.model;

import java.io.Serializable;

public class Permissions implements Serializable {

    private boolean push;
    private boolean pull;
    private boolean admin;
}