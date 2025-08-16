package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.complaint;

public interface complaintrepository extends JpaRepository<complaint, Long> {
}
