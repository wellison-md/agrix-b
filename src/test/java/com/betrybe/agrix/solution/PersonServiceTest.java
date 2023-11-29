package com.betrybe.agrix.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.exception.PersonNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

/**
 * The type Person service test.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PersonServiceTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private PersonService personService;

  private Person person;

  @MockBean
  private PersonRepository personRepository;


  @Test
  public void testPersonByIdEndpointThrowsAnException() {
    Mockito.when(personRepository.findById(any()))
        .thenReturn(Optional.empty());
    Long invalidId = -1L;
    assertThrows(PersonNotFoundException.class, () -> personService.getPersonById(invalidId));
  }

  @Test
  public void testPersonByIdEndpointReturnsPersonWithSuccess() {
    Long newId = 1L;
    Person newPerson = generateMockPerson(newId);

    Mockito.when(personRepository.findById(any()))
        .thenReturn(Optional.of(newPerson));
    Person responseFromApi = personService.getPersonById(newId);
    assertEquals(newPerson, responseFromApi);
  }

  @Test
  public void testPersonByUsernameEndpointReturnsPersonWithSuccess() {
    Long newId = 2L;
    Person newPerson = generateMockPerson(newId);

    Mockito.when(personRepository.findByUsername(any()))
        .thenReturn(Optional.of(newPerson));
    Person responseFromApi = personService.getPersonByUsername("wellison");

    assertEquals(newPerson, responseFromApi);
  }

  private Person generateMockPerson(Long id) {
    Person person = new Person();
    person.setId(id);
    person.setPassword("password");
    person.setUsername("wellison");
    return person;
  }
}
