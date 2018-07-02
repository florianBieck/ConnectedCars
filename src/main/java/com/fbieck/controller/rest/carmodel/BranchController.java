package com.fbieck.controller.rest.carmodel;

import com.fbieck.entities.carmodel.Branch;
import com.fbieck.service.carmodel.branch.IBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BranchController {

    @Autowired
    private IBranchService branchService;

    @RequestMapping(value = "/branch/all")
    private Iterable<Branch> findAll() {
        return branchService.findAll();
    }
}
