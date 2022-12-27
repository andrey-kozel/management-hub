package com.example.github.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Permissions implements Serializable {

    private boolean push;
    private boolean pull;
    private boolean admin;
}