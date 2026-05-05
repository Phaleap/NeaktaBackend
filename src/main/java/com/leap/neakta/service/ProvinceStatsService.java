package com.leap.neakta.service;

import com.leap.neakta.entity.Pin;
import com.leap.neakta.entity.Province;
import com.leap.neakta.entity.ProvinceStats;
import com.leap.neakta.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProvinceStatsService {

    private final ProvinceStatsRepository provinceStatsRepository;
    private final ProvinceRepository provinceRepository;
    private final PinRepository pinRepository;

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void recalculateStats() {
        log.info("Recalculating province stats at {}", LocalDateTime.now());

        List<Province> provinces = provinceRepository.findAll();

        for (Province province : provinces) {
            List<Pin> pins = pinRepository.findByProvince_Id(province.getId());

            int totalPins = pins.size();

            int totalUpvotes = pins.stream()
                    .mapToInt(Pin::getUpvoteCount)
                    .sum();

            long totalContributors = pins.stream()
                    .map(pin -> pin.getUser().getId())
                    .distinct()
                    .count();

            ProvinceStats stats = provinceStatsRepository
                    .findByProvinceId(province.getId())
                    .orElse(new ProvinceStats());

            stats.setProvince(province);
            stats.setTotalPins(totalPins);
            stats.setTotalUpvotes(totalUpvotes);
            stats.setTotalContributors((int) totalContributors);
            stats.setLastUpdated(LocalDateTime.now());

            provinceStatsRepository.save(stats);
        }

        assignRanks();
        log.info("Province stats recalculation complete");
    }

    private void assignRanks() {
        List<ProvinceStats> allStats = provinceStatsRepository.findAllOrderByRank();
        AtomicInteger rank = new AtomicInteger(1);
        for (ProvinceStats stats : allStats) {
            stats.setRank(rank.getAndIncrement());
            provinceStatsRepository.save(stats);
        }
    }

    public List<ProvinceStats> getLeaderboard() {
        return provinceStatsRepository.findAllOrderByRank();
    }
}