package com.leap.neakta.service;

import com.leap.neakta.entity.*;
import com.leap.neakta.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final PinRepository pinRepository;
    private final UserRepository userRepository;

    @Transactional
    public String vote(UUID pinId, String voteType) {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Pin pin = pinRepository.findById(pinId)
                .orElseThrow(() -> new RuntimeException("Pin not found"));

        // Check if already voted
        var existing = voteRepository.findByPinIdAndUserId(pinId, user.getId());

        if (existing.isPresent()) {
            Vote currentVote = existing.get();

            // If same vote type → remove it (toggle off)
            if (currentVote.getVoteType().equals(voteType)) {
                voteRepository.delete(currentVote);
                adjustCounts(pin, voteType, -1);
                pinRepository.save(pin);
                return "Vote removed";
            }

            // If different vote type → update it
            adjustCounts(pin, currentVote.getVoteType(), -1);
            currentVote.setVoteType(voteType);
            voteRepository.save(currentVote);
            adjustCounts(pin, voteType, +1);
            pinRepository.save(pin);
            return "Vote updated to " + voteType;
        }

        // New vote
        Vote vote = new Vote();
        vote.setPin(pin);
        vote.setUser(user);
        vote.setVoteType(voteType);
        voteRepository.save(vote);
        adjustCounts(pin, voteType, +1);
        pinRepository.save(pin);
        return "Voted: " + voteType;
    }

    private void adjustCounts(Pin pin, String voteType, int delta) {
        switch (voteType) {
            case "UPVOTE" -> {
                pin.setUpvoteCount(pin.getUpvoteCount() + delta);
                pin.setScore(pin.getScore() + delta);
            }
            case "FLAG" -> pin.setFlagCount(pin.getFlagCount() + delta);
            case "CONFIRM_EXISTS" -> {
                // Recalculate still_exists_pct based on confirm votes
                // Simplified: just track via flag for now
            }
        }
    }
}