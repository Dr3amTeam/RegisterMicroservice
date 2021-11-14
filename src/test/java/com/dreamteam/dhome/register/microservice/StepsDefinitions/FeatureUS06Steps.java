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
public class FeatureUS06Steps {

    RestTemplate restTemplate = new RestTemplate();
    String fooResourceUrl = "http://localhost:8082/employees/";

    @Test
    @Given("que el cliente desea conocer el número telefónico del trabajador")
    public void queElClienteDeseaConocerElNúmeroTelefónicoDelTrabajador() {
       ResponseEntity<EmployeeView[]> response = restTemplate.getForEntity(fooResourceUrl,EmployeeView[].class);
        Assert.assertEquals(response.getBody().length,4);
    }

    @Test
    @When("el cliente ingrese en la sección de trabajador")
    public void elClienteIngreseEnLaSecciónDeTrabajador() {
    }

    @Test
    @Then("muestra sus datos personales.")
    public void muestraSusDatosPersonales() {
        ResponseEntity<EmployeeView[]> response = restTemplate.getForEntity(fooResourceUrl+"username/hugoR",EmployeeView[].class);
        Assert.assertEquals(response.getBody().length,1);
    }


}
