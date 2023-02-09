package com.kameleoon.controller;

import com.kameleoon.model.TimestampModel;
import com.kameleoon.model.Timestamps;
import com.kameleoon.service.TimestampService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/score")
@RequiredArgsConstructor
public class TimestampController {

    private final TimestampService timestampService;

    @GetMapping
    public List<TimestampModel> getAllTimestamps() {
        return timestampService.getAllTimestamps()
                .stream()
                .map(t -> {
                    TimestampModel timestampModel = new TimestampModel();
                    timestampModel.setScore(t.getScore());
                    return timestampModel;
                })
                .toList();
    }

    @PostMapping
    public String createTimestamp(@RequestBody TimestampModel timestampModel) {
        timestampService.createTimestamp(timestampModel);

        return "Timestamp is ADDED";
    }
}
