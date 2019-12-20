package com.lym.community.service;

import com.lym.community.exception.CustomizeErrorCode;
import com.lym.community.exception.CustomizeException;
import com.lym.community.model.Comment;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    public void insert(Comment comment) {
        if (comment.getParent_id() == null || comment.getParent_id() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
    }
}
