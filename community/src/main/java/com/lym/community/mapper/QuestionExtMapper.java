package com.lym.community.mapper;

import com.lym.community.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);
    int incComment_count(Question record);
    List<Question> selectRelated(Question question);
}