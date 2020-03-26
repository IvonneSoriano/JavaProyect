/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controllers;

import sv.edu.udb.models.Comment;
import sv.edu.udb.models.CommentDAO;

/**
 *
 * @author Rick
 */
public class CommentController {

    public boolean saveComment(Comment c) {
        return new CommentDAO().save(c);
    }
}
