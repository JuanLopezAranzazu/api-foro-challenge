package topics.challenge.api.domain.topics;

public record TopicListDTO(
        Long id,
        String title,
        String message,
        Course course
) {

    public TopicListDTO(Topic topic){
        this(topic.getId(),topic.getTitle(),topic.getMessage(),topic.getCourse());
    }
}
