package com.dreamteam.dhome.register.microservice.StepsDefinitions;

import com.dreamteam.dhome.register.microservice.query.projections.EmployeeView;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class FeatureUS07Steps {


    RestTemplate restTemplate = new RestTemplate();
    String fooResourceUrl = "http://localhost:8082/employees";

    @Given("que el cliente desea actualizar sus datos")
    public void queElClienteDeseaActualizarSusDatos() {
        ResponseEntity<EmployeeView> response = restTemplate.getForEntity(fooResourceUrl+"/username/Alonso",EmployeeView.class);
        EmployeeView employeeView = response.getBody();
        System.out.println(employeeView.getName());
    }

    @When("ingrese en el ícono de un lápiz")
    public void ingreseEnElÍconoDeUnLápiz() {
    }

    @Then("podrá editar sus datos")
    public void podráEditarSusDatos() {
        EmployeeView employeeView = new EmployeeView();
        employeeView.setDni("123456789");
        ResponseEntity<EmployeeView> employeeView1=restTemplate.postForEntity(fooResourceUrl+"/username/",employeeView,EmployeeView.class);
        Assert.assertEquals(employeeView1.getBody().getDni(),"123456789");
    }
}
