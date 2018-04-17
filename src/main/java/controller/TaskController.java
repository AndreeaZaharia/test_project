package controller;

import dto.ResponseDTO;
import dto.TaskDTO;
import entity.Task;
import exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ITaskService;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private ITaskService taskService;

    @RequestMapping(value = "/{taskId}", method = RequestMethod.GET)
    public ResponseDTO getById(@PathVariable Long taskId) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO.setBody(taskService.getById(taskId));
        } catch (BusinessException ex) {
            responseDTO.setMessage(ex.getMessage());
        }

        return responseDTO;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseDTO create(@RequestBody Task task) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setBody(taskService.create(task));
        return responseDTO;
    }
}
