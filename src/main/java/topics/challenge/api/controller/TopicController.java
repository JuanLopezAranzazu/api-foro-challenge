package topics.challenge.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import topics.challenge.api.domain.topics.Topic;
import topics.challenge.api.domain.topics.TopicDTO;
import topics.challenge.api.domain.topics.TopicListDTO;
import topics.challenge.api.domain.topics.TopicRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    TopicRepository topicRepository;

    /*@GetMapping
    public List<Topic> getTopics(){
        return topicRepository.findAll();
    }*/

    /*@GetMapping
    public List<TopicListDTO> getTopics(){
        return topicRepository.findAll().stream().map(TopicListDTO::new).toList();
    }*/

    /*@GetMapping
    public Page<TopicListDTO> getTopics(@PageableDefault(size = 2) Pageable pagination){
        return topicRepository.findAll(pagination).map(TopicListDTO::new);
    }*/

    @GetMapping
    public ResponseEntity<Page<TopicListDTO>> getTopics(@PageableDefault(size = 2) Pageable pagination){
        return ResponseEntity.ok(topicRepository.findAll(pagination).map(TopicListDTO::new));
    }

    @GetMapping("/status")
    public ResponseEntity<Page<TopicListDTO>> getTopicsByStatus(@PageableDefault(size = 2) Pageable pagination){
        return ResponseEntity.ok(topicRepository.findByStatusTrue(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicListDTO> getTopicById(@PathVariable Long id){
        Topic topic=topicRepository.getReferenceById(id);
        var topicListDTO = new TopicListDTO(topic);
        return ResponseEntity.ok(topicListDTO);
    }

    @PostMapping
    public ResponseEntity<TopicListDTO> createTopic(@RequestBody @Valid TopicDTO topicDTO, UriComponentsBuilder uriComponentsBuilder){
        Topic topic=topicRepository.save(new Topic(topicDTO));
        TopicListDTO topicListDTO=new TopicListDTO(topic);
        URI uri=uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(topicListDTO);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateTopic(@PathVariable Long id, @RequestBody @Valid TopicDTO topicDTO){
        Topic topic=topicRepository.getReferenceById(id);
        topic.updateData(topicDTO);
        return ResponseEntity.ok(new TopicListDTO(topic));
    }

    // eliminar registro en la base de datos
    /*@DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable Long id){
        Topic topic=topicRepository.getReferenceById(id);
        topicRepository.delete(topic);
        return ResponseEntity.noContent().build();
    }*/

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable Long id){
        Topic topic=topicRepository.getReferenceById(id);
        topic.delete();
        return ResponseEntity.noContent().build();
    }
}
