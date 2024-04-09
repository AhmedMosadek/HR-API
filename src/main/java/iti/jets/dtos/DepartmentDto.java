package iti.jets.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DepartmentDto implements Serializable {
    private int id;
    private String name;
    private int managerID;
}
