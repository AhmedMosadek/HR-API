package iti.jets.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentDto {
    private int id;
    private String name;
    private int managerID;
}
