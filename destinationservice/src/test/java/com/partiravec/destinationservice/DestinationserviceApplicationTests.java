package com.partiravec.destinationservice;

import com.partiravec.destinationservice.controllers.DestinationController;
import com.partiravec.destinationservice.dao.CountryDao;
import com.partiravec.destinationservice.dao.DestinationDao;
import com.partiravec.destinationservice.models.Country;
import com.partiravec.destinationservice.models.Destination;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
class DestinationserviceApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DestinationController destinationController;

    @Autowired
    private DestinationDao destinationDao;


    @Test
    void contextLoads() {
        assertThat(destinationController).isNotNull();
        assertThat(restTemplate).isNotNull();

    }

    @Test
    public void theApiDefaultPathReturnGreeting() {
        assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/", String.class)).contains("[DestinationService] Hello World");
    }

    @Test
    public void thereAreNoDestinationsSetInTheBeggining() {
        Iterable<Destination> destinations = destinationDao.findAll();
        int counter = 0;
        for (Object i : destinations) {
            counter++;
        }
        assertThat(counter).isEqualTo(0);
    }

    @Test
    public void userCanGetADestinationByItsId() {
        Destination destination = Mockito.mock(Destination.class);
        System.out.println(destination);

        destinationDao.save(destination);
        assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/destination/"+destination.getId(), Destination.class))
                .isEqualTo(destination);

    }

}
