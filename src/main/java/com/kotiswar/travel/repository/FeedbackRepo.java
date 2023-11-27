package com.kotiswar.travel.repository;

import com.kotiswar.travel.entitiy.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepo extends JpaRepository<Feedback, Long> {
}
