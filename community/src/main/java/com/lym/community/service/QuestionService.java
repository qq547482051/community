package com.lym.community.service;

import com.lym.community.dto.PaginationDTO;
import com.lym.community.dto.QuestionDTO;
import com.lym.community.mapper.QuestionMapper;
import com.lym.community.mapper.UserMapper;
import com.lym.community.model.Question;
import com.lym.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {


        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalPage;

        Integer totalCount = questionMapper.count();

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }

            if (page > totalPage) {
                page = totalPage;
            }

            paginationDTO.setPagination(totalPage, page);
            //size*(page-1)
            Integer offset = size * (page - 1);
            List<Question> questions = questionMapper.list(offset, size);
            List<QuestionDTO> questionDTOList = new ArrayList<>();
            for (Question question : questions) {
                User user = userMapper.findById(question.getCreator());
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(question, questionDTO);
                questionDTO.setUser(user);
                questionDTOList.add(questionDTO);
            }
            paginationDTO.setQuestions(questionDTOList);
            return paginationDTO;
        }

        public PaginationDTO list(Integer userId, Integer page, Integer size) {
            PaginationDTO paginationDTO = new PaginationDTO();

            Integer totalPage;

            Integer totalCount = questionMapper.countByUserId(userId);

            if (totalCount % size == 0) {
                totalPage = totalCount / size;
            } else {
                totalPage = totalCount / size + 1;
            }

            if (page < 1) {
                page = 1;
            }
            if (page > totalPage) {
                page = totalPage;
            }

            paginationDTO.setPagination(totalPage, page);

            //size*(page-1)
            Integer offset = size * (page - 1);
            List<Question> questions = questionMapper.listByUserId(userId, offset, size);
            List<QuestionDTO> questionDTOList = new ArrayList<>();

            for (Question question : questions) {
                User user = userMapper.findById(question.getCreator());
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(question, questionDTO);
                questionDTO.setUser(user);
                questionDTOList.add(questionDTO);
            }

            paginationDTO.setQuestions(questionDTOList);
            return paginationDTO;
        }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null){
            question.setGmt_create(System.currentTimeMillis());
            question.setGmt_modified(question.getGmt_create());
            questionMapper.create(question);
        }
        else{
            question.setGmt_modified(question.getGmt_create());
            questionMapper.update(question);
        }
    }
}