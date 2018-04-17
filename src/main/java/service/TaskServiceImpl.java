package service;

import dto.TaskDTO;
import entity.Task;
import exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ITaskRepository;

@Transactional
@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private ITaskRepository taskRepository;

    @Override
    public TaskDTO getById(Long taskId) throws BusinessException {
        Task task = taskRepository.findOne(taskId);
        if (task == null) {
            throw new BusinessException("TASK_NOT_FOUND");
        }
        return new TaskDTO(task);
    }

    @Override
    public Long create(Task task) {
        Task savedTask = taskRepository.save(task);
        return savedTask.getId();
    }

    @Override
    public void update(Task task) {

    }

    @Override
    public void delete(Long taskId) {

    }
}
