package com.iquestint.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vladu
 */
@Getter
@Setter
public class FormUserDto {

    private List<UserTypeDto> types = new ArrayList<>();

    private List<UserStateDto> states = new ArrayList<>();
}
