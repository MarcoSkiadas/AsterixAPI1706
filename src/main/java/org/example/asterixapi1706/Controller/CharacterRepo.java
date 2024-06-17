package org.example.asterixapi1706.Controller;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface CharacterRepo extends MongoRepository<Character, String> {

    List<Character> findByName(String name);
    List<Character> findByProfession(String profession);

}
