package com.example.registrasi._0420230009.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.registrasi._0420230009.entity.Mahasiswa;

public interface MahasiswaRepository extends JpaRepository<Mahasiswa, Long> {

    // ================= CEK UNIK =================
    boolean existsByEmail(String email);
    boolean existsByNoHp(String noHp);

  
    boolean existsByNim(String nim);

    // ================= SEARCH =================
    List<Mahasiswa> findByNamaContainingIgnoreCaseOrNimContainingIgnoreCase(
            String nama,
            String nim
    );

    
    List<Mahasiswa> findByEmailContainingIgnoreCase(String email);

    // ================= STATISTIK DASHBOARD =================
    long countByStatus(String status);
}