package org.example.asterixapi1706.Controller;

import org.example.asterixapi1706.Model.Character;
import org.example.asterixapi1706.Model.CharacterRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AsterixControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private CharacterRepo repo;

    @Test
    void getAllCharacters() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/asterix/characters"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

    @Test
    void findCharacters_shouldReturnCharacter_whenCalledByHisName()throws Exception {
        //GIVEN
        repo.save(new Character("1",
                "Asterix",22,
                "Krieger"));
        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.get("/asterix/characters/findByName?name=Asterix"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
        [
        {
        "id": "1",
        "name": "Asterix",
        "age": 22,
        "profession": "Krieger"
        }
            ]
"""))
                .andExpect(MockMvcResultMatchers.jsonPath("*.name").exists());

    }

    @Test
    void averageAgeByProfessionC_shouldReturn22_whenCalledWithProfessionAge22() throws Exception{
        //GIVEN
        repo.save(new Character("1",
                "Asterix",
                22,
                "Krieger"));
        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.get("/asterix/characters/KriegerByAge"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("22"));
    }

    @Test
    void addCharacterC() throws Exception{
        mvc.perform(MockMvcRequestBuilders.post("/asterix/characters")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
        
        {
        "name": "Asterix",
        "age": 22,
        "profession": "Krieger"
        }
            
"""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
{
        "name": "Asterix",
        "age": 22,
        "profession": "Krieger"
        }
"""));
    }

    @Test
    void updateCharacter() throws Exception{
        repo.save(new Character("1",
                "Asterix",
                22,
                "Krieger"));
        mvc.perform(MockMvcRequestBuilders.put("/asterix/characters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""

{
        "id": "1",
        "name": "Asterix",
        "age": 33,
        "profession": "Krieger"
        }
        
        """))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
{
        "id": "1",
        "name": "Asterix",
        "age": 33,
        "profession": "Krieger"
        }
"""));
    }


    @Test
    void getCharacterById_shouldReturnCharacter_whenCalledByCharacterID() throws Exception {
        //GIVEN
        repo.save(new Character("1",
                "Asterix",
                22,
                "Krieger"
                ));
        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.get("/asterix/characters/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
       {
        "id": "1",
        "name": "Asterix",
        "age": 22,
        "profession": "Krieger"
        }
"""));
    }

    @Test
    void updateCharacterById() throws Exception {
        repo.save(new Character("1",
                "Asterix",
                22,
                "Krieger"));
        mvc.perform(MockMvcRequestBuilders.put("/asterix/characters/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
    {
        "name": "Asterix",
        "age": 33,
        "profession": "Krieger"
    }
    """))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
    {
        "id": "1",
        "name": "Asterix",
        "age": 33,
        "profession": "Krieger"
    }
"""));
    }

    @Test
    void deleteCharacterById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/asterix/characters/1")
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}