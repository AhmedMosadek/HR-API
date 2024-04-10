import iti.jets.dtos.EmployeeDto;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class EmployeeControllerTest {

    @Test
    public void testCreateEmployee() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/employees");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("Ahmed");
        employeeDto.setLastName("Ali");
        employeeDto.setSalary(BigDecimal.valueOf(5000.00));


        // Act
        Response response = request.post(Entity.entity(employeeDto, MediaType.APPLICATION_JSON));

        // Assert
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Test
    public void testUpdateEmployee() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/employees/1");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("Ahmed");
        employeeDto.setLastName("Ali");
        employeeDto.setSalary(BigDecimal.valueOf(5000.00));
        // Set employeeDto properties according to your requirement

        // Act
        Response response = request.put(Entity.entity(employeeDto, MediaType.APPLICATION_JSON));

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testFindEmployeeById() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/employees/1");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testFindAllEmployees() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/employees");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testDeleteEmployee() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/employees/1");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.delete();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}