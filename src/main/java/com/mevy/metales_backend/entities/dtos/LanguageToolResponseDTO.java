package com.mevy.metales_backend.entities.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class LanguageToolResponseDTO {

    private Software software;
    private Warnings warnings;
    private Language language;
    private List<Match> matches;

    @JsonProperty("sentenceRanges")
    private List<List<Integer>> sentenceRanges;

    @JsonProperty("extendedSentenceRanges")
    private List<ExtendedSentenceRange> extendedSentenceRanges;

    @Getter
    public static class Software {
        private String name;
        private String version;
        private String buildDate;
        private int apiVersion;
        private boolean premium;
        private String premiumHint;
        private String status;
    }

    @Getter
    public static class Warnings {
        private boolean incompleteResults;
    }

    @Getter
    public static class Language {
        private String name;
        private String code;
        private DetectedLanguage detectedLanguage;
    }

    @Getter
    public static class DetectedLanguage {
        private String name;
        private String code;
        private double confidence;
        private String source;
    }

    @Getter
    public static class Match {
        private String message;
        private String shortMessage;
        private List<Replacement> replacements;
        private int offset;
        private int length;
        private Context context;
        private String sentence;
        private Type type;
        private Rule rule;
        private boolean ignoreForIncompleteSentence;
        private int contextForSureMatch;
    }

    @Getter
    public static class Replacement {
        private String value;
    }

    @Getter
    public static class Context {
        private String text;
        private int offset;
        private int length;
    }

    @Getter
    public static class Type {
        private String typeName;
    }

    @Getter
    public static class Rule {
        private String id;
        private String description;
        private String issueType;
        private boolean isPremium;
        private Category category;
    }

    @Getter
    public static class Category {
        private String id;
        private String name;
    }

    @Getter
    public static class ExtendedSentenceRange {
        private int from;
        private int to;
        private List<DetectedLanguageRate> detectedLanguages;
    }

    @Getter
    public static class DetectedLanguageRate {
        private String language;
        private double rate;
    }
}
