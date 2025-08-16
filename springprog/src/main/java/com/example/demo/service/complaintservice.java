package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.complaint;
import com.example.demo.repository.complaintrepository;
import java.util.List;
import java.util.Optional;

@Service
public class complaintservice {
    @Autowired
    private complaintrepository complaintRepository;

    public List<complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    public void saveComplaint(complaint complaint) {
        complaintRepository.save(complaint);
    }

    public complaint getComplaintById(Long id) {
        return complaintRepository.findById(id).orElse(null);
    }

    public void updateStatus(Long id, String status) {
        Optional<complaint> optionalComplaint = complaintRepository.findById(id);
        if (optionalComplaint.isPresent()) {
            complaint complaint = optionalComplaint.get();
            complaint.setStatus(status);
            complaintRepository.save(complaint);
        }
    }

    public void deleteComplaint(Long id) {
        complaintRepository.deleteById(id);
    }
}
