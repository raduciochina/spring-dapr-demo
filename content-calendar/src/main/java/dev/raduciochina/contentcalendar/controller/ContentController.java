package dev.raduciochina.contentcalendar.controller;

import dev.raduciochina.contentcalendar.model.Content;
import dev.raduciochina.contentcalendar.repository.ContentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class ContentController {
    private final ContentRepository repository;
    @Autowired //not needed when there is only one constructor
    public ContentController(ContentRepository repository) {
        this.repository = repository;
    }
    @GetMapping("")
    public List<Content> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Content with id %d not found", id)
        ));
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody Content content){
        repository.save(content);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Content content, @PathVariable Integer id){
        if(repository.existsById(id)){
            repository.save(content);
    }
        else{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Content with id %d not found", id)
            );
        }
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        }
        else{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Content with id %d not found", id)
            );
        }
    }
}
