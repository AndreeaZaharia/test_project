package service;

import dto.TaskDTO;
import entity.Task;
import exception.BusinessException;

public interface ITaskService {
    TaskDTO getById(Long taskId)throws BusinessException;

    Long create(Task task);

    void update(Task task);

    void delete(Long taskId);
}
