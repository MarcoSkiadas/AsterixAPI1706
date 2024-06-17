package org.example.asterixapi1706.Controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asterix/characters")
@RequiredArgsConstructor
public class AsterixController {

    private final CharacterRepo characterRepo;

    @GetMapping
public List<Character> getAllCharacters() {
    return characterRepo.findAll();
}
@GetMapping("/findByName")
public List<Character> findCharacter(@RequestParam(required = false, defaultValue = "") String name) {
        return characterRepo.findByName(name);
}

@PostMapping
    public Character addCharacter(@RequestBody Character character) {
        return characterRepo.save(character);
}
@PutMapping
    public Character updateCharacter(@RequestBody Character character) {
        return characterRepo.save(character);
}
@DeleteMapping()
    public void deleteCharacter(@RequestBody Character character) {
        characterRepo.delete(character);
}
}
