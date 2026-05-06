package com.example.registrasi._0420230009.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.registrasi._0420230009.config.CaptchaService;
import com.example.registrasi._0420230009.entity.Mahasiswa;
import com.example.registrasi._0420230009.entity.ProgramStudi;
import com.example.registrasi._0420230009.repository.ProgramStudiRepository;
import com.example.registrasi._0420230009.service.MahasiswaService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MahasiswaController {

    private final MahasiswaService service;
    private final ProgramStudiRepository repo;
    private final CaptchaService captchaService;

    public MahasiswaController(MahasiswaService s,
                               ProgramStudiRepository r,
                               CaptchaService c){
        this.service = s;
        this.repo = r;
        this.captchaService = c;
    }

    // ================= FORM =================
    @GetMapping("/")
    public String form(Model model, HttpSession session){

        model.addAttribute("mhs", new Mahasiswa());
        model.addAttribute("prodi", repo.findAll());

        String captcha = captchaService.generateCaptcha();
        session.setAttribute("captcha", captcha);
        model.addAttribute("captcha", captcha);

        return "form";
    }

    // block direct GET save
    @GetMapping("/save")
    public String block(){
        return "redirect:/";
    }

    // ================= SAVE =================
    @PostMapping("/save")
    public String save(@ModelAttribute Mahasiswa m,
                       @RequestParam(required = false) Long prodiId,
                       @RequestParam(required = false) String inputCaptcha,
                       Model model,
                       HttpSession session){

        model.addAttribute("mhs", m);
        model.addAttribute("prodi", repo.findAll());

        String captcha = (String) session.getAttribute("captcha");

        boolean error = false;

        // ================= CAPTCHA =================
        if (inputCaptcha == null || captcha == null || !inputCaptcha.equals(captcha)) {
            model.addAttribute("captchaError", "Captcha salah");
            error = true;
        }

        // ================= PRODI =================
        if (prodiId == null) {
            model.addAttribute("prodiError", "Program studi wajib dipilih");
            error = true;
        }

        // ================= DUPLIKAT EMAIL & NO HP (TAMBAHAN BARU) =================
        if (service.findAll().stream().anyMatch(x -> x.getEmail().equalsIgnoreCase(m.getEmail()))) {
            model.addAttribute("emailError", "Email sudah terdaftar");
            error = true;
        }

        if (service.findAll().stream().anyMatch(x -> x.getNoHp().equals(m.getNoHp()))) {
            model.addAttribute("noHpError", "Nomor HP sudah terdaftar");
            error = true;
        }

        // ================= VALIDASI SERVICE =================
        String valid = service.validate(m);
        if (!valid.equals("OK")) {
            model.addAttribute("error", valid);
            error = true;
        }

        // kalau ada error balik form
        if (error) {
            String newCaptcha = captchaService.generateCaptcha();
            session.setAttribute("captcha", newCaptcha);
            model.addAttribute("captcha", newCaptcha);

            return "form";
        }

        // ================= SET PRODI =================
        ProgramStudi prodi = repo.findById(prodiId).orElse(null);
        m.setProgramStudi(prodi);

        // ================= SAVE =================
        service.save(m);

        session.removeAttribute("captcha");

        return "redirect:/success";
    }

    // ================= SUCCESS =================
    @GetMapping("/success")
    public String success(){
        return "success";
    }

    // ================= CAMPUS INFO =================
    @GetMapping("/campus-info")
    public String campusInfo(){
        return "campus-info";
    }
}