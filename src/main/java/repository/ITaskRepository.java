package repository;

import entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ITaskRepository extends CrudRepository<Task,Long> {
}
