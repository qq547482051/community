package com.lym.community.mapper;

import com.lym.community.dto.QuestionQueryDTO;
import com.lym.community.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);
    int incComment_count(Question record);
    List<Question> selectRelated(Question question);
    Integer countBySearch(QuestionQueryDTO questionQueryDTO);
    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}