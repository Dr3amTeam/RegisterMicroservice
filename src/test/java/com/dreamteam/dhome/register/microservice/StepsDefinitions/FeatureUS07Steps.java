package com.dreamteam.dhome.register.microservice.StepsDefinitions;

import com.dreamteam.dhome.register.microservice.query.api.EmployeeQueryController;
import com.dreamteam.dhome.register.microservice.query.projections.EmployeeView;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@WebMvcTest(EmployeeQueryController.class)
public class FeatureUS07Steps {


    RestTemplate restTemplate = new RestTemplate();
    String fooResourceUrl = "http://localhost:8082/employees";

    @Test
    @Given("que el cliente desea actualizar sus datos")
    public void queElClienteDeseaActualizarSusDatos() {
        ResponseEntity<EmployeeView> response = restTemplate.getForEntity(fooResourceUrl+"/eba11849-2daf-453c-96c2-c40fa8c4ea3b",EmployeeView.class);
        EmployeeView employeeView = response.getBody();
        System.out.println(employeeView.getName());
    }

    @Test
    @When("ingrese en el ícono de un lápiz")
    public void ingreseEnElÍconoDeUnLápiz() {
    }

    @Test
    @Then("podrá editar sus datos")
    public void podráEditarSusDatos() {
        EmployeeView employeeView = new EmployeeView();
        ResponseEntity<EmployeeView> employeeView1=restTemplate.getForEntity(fooResourceUrl+"/eba11849-2daf-453c-96c2-c40fa8c4ea3b",EmployeeView.class);
        Assert.assertNotEquals(employeeView1.getBody().getPhone(),"123456789");
    }
}
