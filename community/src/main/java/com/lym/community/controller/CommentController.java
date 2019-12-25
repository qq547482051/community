package com.lym.community.controller;
import com.lym.community.dto.CommentCreateDTO;
import com.lym.community.dto.ResultDTO;
import com.lym.community.exception.CustomizeErrorCode;
import com.lym.community.model.Comment;
import com.lym.community.model.User;
import com.lym.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
        public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                HttpServletRequest request) {
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
            }
            Comment comment = new Comment();
            comment.setParent_id(commentCreateDTO.getParent_id());
            comment.setContent(commentCreateDTO.getContent());
            comment.setType(commentCreateDTO.getType());
            comment.setGmt_modified(System.currentTimeMillis());
            comment.setGmt_create(System.currentTimeMillis());
            comment.setCommentator(user.getId());
            comment.setLike_count(0);
            commentService.insert(comment);
            return ResultDTO.okOf();
        }
    }
