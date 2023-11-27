package com.kotiswar.travel.controllers;

import com.kotiswar.travel.entitiy.Feedback;
import com.kotiswar.travel.payloads.FeedbackDto;
import com.kotiswar.travel.services.FeedbackService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final ModelMapper modelMapper;

    @Autowired
    public FeedbackController(FeedbackService feedbackService, ModelMapper modelMapper) {
        this.feedbackService = feedbackService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<FeedbackDto>> getAllFeedback() {
        List<FeedbackDto> feedbacks = feedbackService.getAllFeedback().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackDto> getFeedbackById(@PathVariable Long id) {
        return feedbackService.getFeedbackById(id)
                .map(this::convertToDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FeedbackDto> createFeedback(@RequestBody FeedbackDto feedbackDto) {
        Feedback feedback = convertToEntity(feedbackDto);
        Feedback createdFeedback = feedbackService.createFeedback(feedback);
        return new ResponseEntity<>(convertToDto(createdFeedback), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }

    // Method to convert Feedback entity to FeedbackDto
    private FeedbackDto convertToDto(Feedback feedback) {
        return modelMapper.map(feedback, FeedbackDto.class);
    }

    // Method to convert FeedbackDto to Feedback entity
    private Feedback convertToEntity(FeedbackDto feedbackDto) {
        return modelMapper.map(feedbackDto, Feedback.class);
    }
}
