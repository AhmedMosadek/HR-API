package iti.jets.exceptions;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    private String errorMessage;
    private int errorCode;
    private String errorDescription;
}
