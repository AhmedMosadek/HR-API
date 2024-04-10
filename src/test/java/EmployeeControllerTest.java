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
        request.header("Accept", "text/plain");
        String json = "{"
                + "\"firstName\":\"Ahmed3\","
                + "\"lastName\":\"Mosadek\","
                + "\"salary\":100000"
                + "}";



        // Act
        Response response = request.post(Entity.entity(json, MediaType.APPLICATION_JSON));
        // Assert
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Test
    public void testUpdateEmployee() {
        // Arrange
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/hr/webapi/employees/22");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);
        request.header("Accept", "text/plain");
        String json = "{"
                + "\"firstName\":\"Ahmed3\","
                + "\"lastName\":\"Mosadek\","
                + "\"salary\":10"
                + "}";
        // Set employeeDto properties according to your requirement

        // Act
        Response response = request.put(Entity.entity(json, MediaType.APPLICATION_JSON));

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
        WebTarget target = client.target("http://localhost:9090/hr/webapi/employees/23");
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);
        request.header("Accept", "text/plain");

        // Act
        Response response = request.delete();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}