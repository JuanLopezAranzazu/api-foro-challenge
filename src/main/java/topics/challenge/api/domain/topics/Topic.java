package topics.challenge.api.domain.topics;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/*@Entity
@Table(name = "topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private Long topicId;
    private String title;
    private String message;
    @Enumerated(EnumType.STRING)
    private Course course;

    public Topic(){}

    public Topic(String title, String message, Course course) {
        this.title = title;
        this.message = message;
        this.course = course;
    }

    public Long getTopicId() {
        return topicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}*/

@Entity
@Table(name = "topics")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    @Enumerated(EnumType.STRING)
    private Course course;
    private LocalDate date=LocalDate.now();
    private Boolean status;

    public Topic(TopicDTO topicData){
        this.status=true;
        this.title=topicData.title();
        this.message=topicData.message();
        this.course=topicData.course();
    }

    public void updateData(TopicDTO topicDTO){
        if(topicDTO.title()!=null){
            this.title=topicDTO.title();
        }
        if(topicDTO.message()!=null){
            this.message=topicDTO.message();
        }
        if(topicDTO.course()!=null) {
            this.course = topicDTO.course();
        }
    }

    public void delete(){
        this.status=false;
    }
}
