package org.example.asterixapi1706.Service;


import org.example.asterixapi1706.Model.Character;
import org.example.asterixapi1706.Model.CharacterDTO;
import org.example.asterixapi1706.Model.CharacterRepo;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AsterixServiceTest {

    private final CharacterRepo mockRepo = mock(CharacterRepo.class);
    private final UtilService mockUtils = mock(UtilService.class);

    @Test
    void getAllCharacters_ShouldReturnEmptyList_whenCalledInitially() {
        //GIVEN
        AsterixService service = new AsterixService(mockRepo, mockUtils);
        when(mockRepo.findAll()).thenReturn(Collections.EMPTY_LIST);
        //WHEN
        List<Character> characters = service.getAllCharacters();
        //THEN
        assertEquals(Collections.EMPTY_LIST, characters);
    }
    @Test
    void findCharacterById_shouldReturnCharacter_whenCalledBySameCharacterId() {
        //GIVEN
        AsterixService service = new AsterixService(mockRepo, mockUtils);
        Character expected = new Character("1","Asterix",22,"Krieger");
        when(mockRepo.findById("1")).thenReturn(Optional.of(expected));
        //WHEN
        Character actual = service.findCharacterById("1");
        //THEN
        assertEquals(expected, actual);

    }
    @Test
    void updateCharacter_ShouldReturnChangedCharacter_whenCalledByChangedCharacter() {
        //GIVEN
        AsterixService service = new AsterixService(mockRepo, mockUtils);
        Character expected = new Character("1","Asterix",22,"Krieger");
        Character actual = new Character("1","Asterix",33,"Krieger");
        when(mockRepo.save(expected)).thenReturn(expected);
        //WHEN
        actual = service.updateCharacter(expected);
        //THEN
        assertEquals(expected, actual);

    }

    @Test
    void deleteCharacter_shouldDeleteCharacter_whenCalledBySameCharacter() {
        //GIVEN
        AsterixService service = new AsterixService(mockRepo, mockUtils);
        Character expected = new Character("1","Asterix",22,"Krieger");
        //WHEN
        service.deleteCharacter(expected);
        //THEN
        verify(mockRepo).delete(expected);
    }



    @Test
    void addCharacter_shouldAddCharacter_whenCalledBySameCharacter() {
        AsterixService service = new AsterixService(mockRepo, mockUtils);
        Character expected = new Character("1","Asterix",22,"Krieger");
        when(mockUtils.returnRandomId()).thenReturn("1");
        when(mockRepo.save(expected)).thenReturn(expected);
        //WHEN
        Character actual = service.addCharacter(new CharacterDTO("Asterix",22,"Krieger"));
        //THEN
        assertEquals(expected, actual);



    }
}