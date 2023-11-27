package com.kotiswar.travel.services;

import com.kotiswar.travel.entitiy.Feedback;
import com.kotiswar.travel.repository.FeedbackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    private final FeedbackRepo feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepo feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public Optional<Feedback> getFeedbackById(Long id) {
        return feedbackRepository.findById(id);
    }

    public Feedback createFeedback(Feedback feedback) {
        // Add validation or business logic here if needed
        return feedbackRepository.save(feedback);
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }

    public Feedback updateFeedback(Long id, Feedback updatedFeedback) {
        return feedbackRepository.findById(id)
                .map(feedback -> {
                    feedback.setRating(updatedFeedback.getRating());
                    return feedbackRepository.save(feedback);
                })
                .orElseGet(() -> {
                    updatedFeedback.setId(id);
                    return feedbackRepository.save(updatedFeedback);
                });
    }

// Additional business logic and validations can be added as needed
}

