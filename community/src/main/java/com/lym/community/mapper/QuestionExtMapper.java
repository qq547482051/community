package com.lym.community.mapper;

import com.lym.community.model.Question;

public interface QuestionExtMapper {
    int incView(Question record);
    int incComment_count(Question record);
}