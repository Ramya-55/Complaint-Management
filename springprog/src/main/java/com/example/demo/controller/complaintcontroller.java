package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.complaint;
import com.example.demo.service.complaintservice;

@Controller
@RequestMapping("/complaints")
public class complaintcontroller {
    @Autowired
    private complaintservice complaintService;

    @GetMapping
    public String listComplaints(Model model) {
        model.addAttribute("complaints", complaintService.getAllComplaints());
        return "complaint-list";
    }

    @GetMapping("/new")
    public String showComplaintForm(Model model) {
        model.addAttribute("complaint", new complaint());
        return "complaint-form";
    }

    @PostMapping("/save")
    public String saveComplaint(@ModelAttribute complaint complaint) {
        complaintService.saveComplaint(complaint);
        return "redirect:/complaints";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("complaint", complaintService.getComplaintById(id));
        return "edit-complaint";
    }

    @PostMapping("/update")
    public String updateComplaint(@RequestParam Long id, @RequestParam String status) {
        complaintService.updateStatus(id, status);
        return "redirect:/complaints";
    }

    @GetMapping("/delete/{id}")
    public String deleteComplaint(@PathVariable Long id) {
        complaintService.deleteComplaint(id);
        return "redirect:/complaints";
    }

    @GetMapping("/status/{id}")
    public String checkStatus(@PathVariable Long id, Model model) {
        complaint complaint = complaintService.getComplaintById(id);
        if (complaint == null) {
            return "error";  // Redirects to an error page if the ID is invalid
        }
        model.addAttribute("complaint", complaint);
        return "status-check";
     }
    @GetMapping("/status")
    public String showStatusForm() {
        return "status-form"; // Create status-form.html in templates
    }

    @Controller
    @RequestMapping("/complaints")
    public class ComplaintController {

       

        @PostMapping("/status")
        public String checkStatus(@RequestParam Long id, Model model) {
            complaint complaint = complaintService.getComplaintById(id);
            if (complaint == null) {
                model.addAttribute("error", "Complaint ID not found.");
                return "status-form";  // Show the same form with an error message
            }
            model.addAttribute("complaint", complaint);
            return "status-check"; // Show the status details page
        }
    }

}
