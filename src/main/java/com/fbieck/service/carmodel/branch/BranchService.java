package com.fbieck.service.carmodel.branch;

import com.fbieck.entities.carmodel.Branch;
import com.fbieck.repositories.carmodel.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchService implements IBranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public Iterable<Branch> findAll() {
        return branchRepository.findAll();
    }
}
