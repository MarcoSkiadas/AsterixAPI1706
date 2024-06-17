package org.example.asterixapi1706.Service;

import lombok.RequiredArgsConstructor;
import org.example.asterixapi1706.Model.Character;
import org.example.asterixapi1706.Model.CharacterDTO;
import org.example.asterixapi1706.Model.CharacterRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AsterixService {

    private final CharacterRepo characterRepo;


    public List<Character> getAllCharacters() {
        return characterRepo.findAll();
    }

    public List<Character> findCharacter(String name) {
        return characterRepo.findByName(name);
    }
    public int averageAgeByProfession(String profession) {
        List<Character> c = characterRepo.findByProfession(profession);
        int age = 0;
        for (Character cc : c) {
            age += cc.age();
        }
        age = age / c.size();
        return age;
    }


    public Character addCharacter(CharacterDTO character) {
        String id = UUID.randomUUID().toString();
        String name = character.name();
        int age = character.age();
        String profession = character.profession();
        Character c = new Character(id,name,age,profession);
        return characterRepo.save(c);
    }

    public Character updateCharacter(Character character) {
        return characterRepo.save(character);
    }

    public void deleteCharacter(Character character) {
        characterRepo.delete(character);
    }
    public Character findCharacterById(String id) {
        return characterRepo.findById(id).orElseThrow();
    }
    public Character updateCharacterById(String id) {
        Character character = characterRepo.findById(id).orElseThrow();
        return characterRepo.save(character);
    }

    public void deleteCharacterById(String id) {
        Character character = characterRepo.findById(id).orElseThrow();
        characterRepo.delete(character);
    }

}
