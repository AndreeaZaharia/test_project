package service.impl;

import dto.TaskDTO;

import entity.Task;

import exception.BusinessException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import repository.ITaskRepository;

import service.ITaskService;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
@Service
@Transactional
public class TaskServiceImpl implements ITaskService
{
	//~ Instance fields --------------------------

	/**  */
	@Autowired
	private ITaskRepository taskRepository;
	//~ Methods ----------------------------------

	/** @see  service.ITaskService#getById(java.lang.Long) */
	@Override
	public TaskDTO getById(Long taskId) throws BusinessException
	{
		Task task = taskRepository.findOne(taskId);
		if (task == null)
		{
			throw new BusinessException("TASK_NOT_FOUND");
		}

		return new TaskDTO(task);
	}
	

	/** @see  service.ITaskService#create(entity.Task) */
	@Override
	public Long create(Task task)
	{
		Task savedTask = taskRepository.save(task);

		return savedTask.getId();
	}
	

	/** @see  service.ITaskService#update(entity.Task) */
	@Override
	public void update(Task task) { }
	

	/** @see  service.ITaskService#delete(java.lang.Long) */
	@Override
	public void delete(Long taskId) { }
}
