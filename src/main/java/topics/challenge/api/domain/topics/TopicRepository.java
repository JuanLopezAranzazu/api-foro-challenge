package topics.challenge.api.domain.topics;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic,Long> {
    Page<TopicListDTO> findByStatusTrue(Pageable pagination);
}
