package com.example.registrasi._0420230009.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.registrasi._0420230009.entity.ProgramStudi;

@Repository
public interface ProgramStudiRepository extends JpaRepository<ProgramStudi, Long> {
}