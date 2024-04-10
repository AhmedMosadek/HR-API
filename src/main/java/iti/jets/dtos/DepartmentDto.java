package iti.jets.dtos;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@XmlRootElement(name = "department")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class DepartmentDto implements Serializable {
    private int id;
    private String name;
    private int managerID;
}
