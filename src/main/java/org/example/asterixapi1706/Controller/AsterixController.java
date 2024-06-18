package org.example.asterixapi1706.Controller;

import lombok.RequiredArgsConstructor;
import org.example.asterixapi1706.Model.Character;
import org.example.asterixapi1706.Model.CharacterDTO;
import org.example.asterixapi1706.Service.AsterixService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/asterix/characters")
@RequiredArgsConstructor
public class AsterixController {

    private final AsterixService service;

    @GetMapping
public List<Character> getAllCharacters() {
    return service.getAllCharacters();
}
@GetMapping("/findByName")
public List<Character> findCharacters(@RequestParam(required = false, defaultValue = "") String name) {
        return service.findCharacter(name);
}
    @GetMapping("/{profession}ByAge")
    public int averageAgeByProfessionC(@PathVariable String profession) {
        return service.averageAgeByProfession(profession);
    }

@PostMapping
    public Character addCharacterC(@RequestBody CharacterDTO character) {
        return service.addCharacter(character);
}
@PutMapping
    public Character updateCharacter(@RequestBody Character character) {
        return service.updateCharacter(character);
}
@DeleteMapping()
    public void deleteCharacter(@RequestBody Character character) {
        service.deleteCharacter(character);
}
@GetMapping("/{id}")
    public Character getCharacterById(@PathVariable String id) {
    return service.findCharacterById(id);
}
    @PutMapping("/{id}")
    public Character updateCharacterById(@PathVariable String id, @RequestBody CharacterDTO character) {
        return service.updateCharacterById(id, character);
    }
    @DeleteMapping("/{id}")
    public void deleteCharacterById(@PathVariable String id) {
        service.deleteCharacterById(id);
    }
}
