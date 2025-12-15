package com.saurabh.journal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor      // REQUIRED for deserialization
@AllArgsConstructor
public class SentimentData {
    private String sentiment;
    private String email;
}
