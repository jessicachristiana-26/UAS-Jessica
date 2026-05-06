package com.example.registrasi._0420230009.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.registrasi._0420230009.entity.Mahasiswa;
import com.example.registrasi._0420230009.repository.MahasiswaRepository;

@Service
public class MahasiswaService {

    private final MahasiswaRepository repo;

    public MahasiswaService(MahasiswaRepository repo){
        this.repo = repo;
    }

    // ================= VALIDASI =================
    public String validate(Mahasiswa m){

        if (m == null) return "Data tidak boleh kosong";

        if (m.getNama() == null || m.getNama().trim().length() < 4)
            return "Nama minimal 4 karakter";

        if (m.getNim() == null || !m.getNim().matches("\\d{10}"))
            return "NIM harus 10 digit angka";

        // TAMBAHAN: cek NIM sudah dipakai
        if (repo.existsByNim(m.getNim()))
            return "NIM sudah digunakan";

        if (m.getEmail() == null || m.getEmail().trim().isEmpty())
            return "Email wajib diisi";

        if (repo.existsByEmail(m.getEmail()))
            return "Email sudah digunakan";

        if (m.getNoHp() == null || m.getNoHp().trim().isEmpty())
            return "No HP wajib diisi";

        if (!m.getNoHp().matches("^(08|62)\\d{8,11}$"))
            return "Format No HP tidak valid";

        if (repo.existsByNoHp(m.getNoHp()))
            return "No HP sudah digunakan";

        if (m.getAlamat() == null || m.getAlamat().trim().length() < 15)
            return "Alamat minimal 15 karakter";

        if (m.getTanggalLahir() == null)
            return "Tanggal lahir wajib diisi";

        int umur = Period.between(m.getTanggalLahir(), LocalDate.now()).getYears();

        if (umur < 18)
            return "Minimal umur 18 tahun";

        return "OK";
    }

    // ================= SAVE =================
    public void save(Mahasiswa m){

        if (m.getStatus() == null || m.getStatus().isEmpty()) {
            m.setStatus("Pending");
        }

        repo.save(m);
    }

    // ================= FIND ALL =================
    public List<Mahasiswa> findAll(){
        return repo.findAll();
    }

    // ================= SEARCH =================
    public List<Mahasiswa> search(String k){

        if (k == null || k.trim().isEmpty()) {
            return List.of(); 
        }

        return repo.findByNamaContainingIgnoreCaseOrNimContainingIgnoreCase(k, k);
    }

    // ================= GET BY ID =================
    public Mahasiswa get(Long id){
        return repo.findById(id).orElse(null);
    }

    // ================= STATISTICS =================
    public long countAll(){
        return repo.count();
    }

    public long countByStatus(String status){

        if (status == null) return 0;

        return repo.countByStatus(status);
    }
}