//package dev.raduciochina.contentcalendar.repository;
//
//import dev.raduciochina.contentcalendar.model.Content;
//import dev.raduciochina.contentcalendar.model.Status;
//import dev.raduciochina.contentcalendar.model.Type;
//import jakarta.annotation.PostConstruct;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class ContentCollectionRepository {
//    private final List<Content> contentCollection = new ArrayList<>();
//
//    public ContentCollectionRepository(){
//
//    }
//
//    public List<Content> findAll() {
//        return contentCollection;
//    }
//
//    public Optional<Content> findById(Integer id) {
//        return contentCollection.stream()
//                .filter(content -> content.getId().equals(id))
//                .findFirst();
//    }
//    public void save(Content content){
//        contentCollection.removeIf(c -> c.getId().equals(content.getId()));
//        contentCollection.add(content);
//    }
//    public void deleteById(Integer id){
//        contentCollection.removeIf(content -> content.getId().equals(id));
//    }
//    @PostConstruct
//    private void init(){
//        contentCollection.add(new Content(1, "Title 1", "Description 1", LocalDateTime.now(), null, "https://www.google.com", Type.ARTICLE, Status.IDEA));
//        contentCollection.add(new Content(2, "Title 2", "Description 2", null, null, "https://www.google.com", null, null));
//        contentCollection.add(new Content(3, "Title 3", "Description 3", null, null, "https://www.google.com", null, null));
//        contentCollection.add(new Content(4, "Title 4", "Description 4", null, null, "https://www.google.com", null, null));
//        contentCollection.add(new Content(5, "Title 5", "Description 5", null, null, "https://www.google.com", null, null));
//        contentCollection.add(new Content(6, "Title 6", "Description 6", null, null, "https://www.google.com", null, null));
//        contentCollection.add(new Content(7, "Title 7", "Description 7", null, null, "https://www.google.com", null, null));
//        contentCollection.add(new Content(8, "Title 8", "Description 8", null, null, "https://www.google.com", null, null));
//        contentCollection.add(new Content(9, "Title 9", "Description 9", null, null, "https://www.google.com", null, null));
//        contentCollection.add(new Content(10, "Title 10", "Description 10", null, null, "https://www.google.com", null, null));
//        contentCollection.add(new Content(11, "Title 11", "Description 11", null, null, "https://www.google.com", null, null));
//        contentCollection.add(new Content(12, "Title 12", "Description 12", null, null, "https://www.google.com", null, null));
//        contentCollection.add(new Content(13, "Title 13", "Description 13", null, null, "https://www.google.com", null, null));
//        contentCollection.add(new Content(14, "Title 14", "Description 14", null, null, "https://www.google.com", null, null));
//
//    }
//
//    public boolean existsById(Integer id) {
//        return contentCollection.stream()
//                .filter(content -> content.getId().equals(id)).count() == 1;
//    }
//}
