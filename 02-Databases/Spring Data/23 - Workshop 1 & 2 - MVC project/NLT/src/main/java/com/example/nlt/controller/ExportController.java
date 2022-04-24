package com.example.nlt.controller;

import com.example.nlt.models.dto.ExportEmployeeDTO;
import com.example.nlt.service.EmployeeService;
import com.example.nlt.service.ProjectService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ExportController {
    private final ProjectService projectService;
    private final EmployeeService employeeService;
    private final Gson gson;

    @Autowired
    public ExportController(ProjectService projectService, EmployeeService employeeService, Gson gson) {
        this.projectService = projectService;
        this.employeeService = employeeService;
        this.gson = gson;
    }

    @GetMapping("/export/project-if-finished")
    public ModelAndView showFinishedProjects(){
        ModelAndView modelAndView = new ModelAndView("export/export-project-if-finished");

        String result = this.projectService.getFinishedProducts();

        modelAndView.addObject("projectsIfFinished", result);

        return modelAndView;
    }

    @GetMapping("/export/employees-above")
    public ModelAndView showEmployeesAbove25(){
        ModelAndView modelAndView = new ModelAndView("export/export-employees-with-age");

        List<ExportEmployeeDTO> employeesAbove25 = this.employeeService.getEmployeesAbove25();

        StringBuilder sb = new StringBuilder();
        this.gson.toJson(employeesAbove25, sb);

        modelAndView.addObject("employeesAbove", sb.toString());

        return modelAndView;
    }

}
