package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dao.Roles;

public interface RoleRepository extends JpaRepository<Roles, Long> {

}
