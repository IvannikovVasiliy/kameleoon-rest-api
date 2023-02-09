package com.kameleoon.service;

import com.kameleoon.model.Quote;
import com.kameleoon.model.TimestampModel;
import com.kameleoon.model.Timestamps;
import com.kameleoon.model.User;
import com.kameleoon.repository.QuoteRepository;
import com.kameleoon.repository.TimestampRepository;
import com.kameleoon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimestampService {

    private final TimestampRepository timestampRepository;
    private final UserRepository userRepository;
    private final QuoteRepository quoteRepository;

    public List<Timestamps> getAllTimestamps() {
        return timestampRepository.findAll();
    }

    public boolean createTimestamp(@RequestBody TimestampModel timestampModel) {
        User user = userRepository.findByLogin(timestampModel.getUserLogin());
        Quote quote = quoteRepository.findByText(timestampModel.getQuoteText());

        Timestamps timestamps = new Timestamps();
        timestamps.setPressedAt(new Timestamp(new Date().getTime()));
        timestamps.setUser(user);
        timestamps.setQuote(quote);
        timestamps.setScore(timestampModel.getScore());

        timestampRepository.save(timestamps);

        return true;
    }
}
