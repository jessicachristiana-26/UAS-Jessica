package com.example.registrasi._0420230009.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.registrasi._0420230009.entity.Mahasiswa;
import com.example.registrasi._0420230009.service.MahasiswaService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

    private final MahasiswaService service;

    public AdminController(MahasiswaService s){
        this.service = s;
    }

    // ================= LOGIN =================
    @GetMapping("/admin")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/admin")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          Model model,
                          HttpSession session){

        if ("admin".equals(username.trim()) && "admin".equals(password.trim())) {
            session.setAttribute("admin", true);
            return "redirect:/dashboard";
        }

        model.addAttribute("error", "Username atau Password salah!");
        return "login";
    }

    // ================= DASHBOARD =================
    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session){

        if (session.getAttribute("admin") == null) {
            return "redirect:/admin";
        }

        model.addAttribute("data", service.findAll());

        model.addAttribute("total", service.countAll());
        model.addAttribute("pending", service.countByStatus("Pending"));
        model.addAttribute("verified", service.countByStatus("Verified"));
        model.addAttribute("rejected", service.countByStatus("Rejected"));

        return "dashboard";
    }

    // ================= SEARCH =================
    @GetMapping("/search")
    public String search(@RequestParam(required = false) String keyword,
                         @RequestParam(required = false) String status,
                         Model model,
                         HttpSession session){

        if (session.getAttribute("admin") == null) {
            return "redirect:/admin";
        }

        keyword = (keyword == null) ? "" : keyword.trim();

        List<Mahasiswa> data;

        // SEARCH LOGIC
        if (keyword.isEmpty()) {
            data = null;
        } else {
            data = service.search(keyword);
        }

        // FILTER STATUS 
        if (data != null && status != null && !status.isEmpty()) {
            data = data.stream()
                    .filter(m -> status.equals(m.getStatus()))
                    .toList();
        }

        model.addAttribute("data", data);
        model.addAttribute("keyword", keyword);
        model.addAttribute("status", status);

        return "search";
    }

    // ================= VERIFIKASI =================
    @GetMapping("/verifikasi/{id}")
    public String verifikasi(@PathVariable Long id,
                             Model model,
                             HttpSession session){

        if (session.getAttribute("admin") == null) {
            return "redirect:/admin";
        }

        Mahasiswa m = service.get(id);

        if (m == null) {
            return "redirect:/dashboard";
        }

        model.addAttribute("m", m);
        return "verifikasi";
    }

    // ================= PROSES VERIFIKASI =================
    @PostMapping("/verifikasi")
    public String proses(@RequestParam Long id,
                         @RequestParam String status,
                         HttpSession session){

        if (session.getAttribute("admin") == null) {
            return "redirect:/admin";
        }

        Mahasiswa m = service.get(id);

        if (m != null) {

            String finalStatus = switch (status.toLowerCase()) {
                case "terverifikasi" -> "Verified";
                case "ditolak" -> "Rejected";
                default -> "Pending";
            };

            m.setStatus(finalStatus);
            service.save(m);
        }

        return "redirect:/dashboard";
    }

    // ================= LOGOUT =================
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/admin";
    }
}