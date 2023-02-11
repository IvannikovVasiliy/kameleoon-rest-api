package com.kameleoon.controller;

import com.kameleoon.model.Role;
import com.kameleoon.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleRepository roleRepository;

    @GetMapping
    public String get() {
        roleRepository.findAll().stream().forEach(x -> System.out.println(x.getName()));
        return "OK";
    }
}
