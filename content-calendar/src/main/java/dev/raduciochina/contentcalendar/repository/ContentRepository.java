package dev.raduciochina.contentcalendar.repository;

import dev.raduciochina.contentcalendar.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Integer> {

}
