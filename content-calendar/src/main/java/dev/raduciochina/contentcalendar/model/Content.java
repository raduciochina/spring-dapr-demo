package dev.raduciochina.contentcalendar.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
public class Content {
    @Id
    @SequenceGenerator(
            name = "content_id_sequence",
            sequenceName = "content_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "content_id_sequence"
    )
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime dateCreated;
    private LocalDateTime deadline;
    private LocalDateTime dateUpdated;
    private String url;
    private Type contentType;
    private Status status;
    private boolean isNotified;

    public Content() {
    }
    public Content(Integer id, String title, String description, LocalDateTime dateCreated, LocalDateTime deadline, LocalDateTime dateUpdated, String url, Type contentType, Status status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.deadline = deadline;
        this.dateUpdated = dateUpdated;
        this.url = url;
        this.contentType = contentType;
        this.status = status;
        this.isNotified = false;
    }

    public Integer getId() {
        return id;
    }

    public boolean getIsNotified() {return isNotified;}

    public LocalDateTime getDeadline() {
        return deadline;
    }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public String getUrl() {
        return url;
    }

    public Type getContentType() {
        return contentType;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dateCreated=" + dateCreated +
                ", deadline=" + deadline +
                ", dateUpdated=" + dateUpdated +
                ", url='" + url + '\'' +
                ", contentType=" + contentType +
                ", status=" + status +
                ", isNotified=" + isNotified +
                '}';
    }
}
