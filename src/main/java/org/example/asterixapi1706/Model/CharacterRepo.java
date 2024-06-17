package org.example.asterixapi1706.Model;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface CharacterRepo extends MongoRepository<Character, String> {

   public List<Character> findByName(String name);
   public List<Character> findByProfession(String profession);

}
