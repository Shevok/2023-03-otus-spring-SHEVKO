package com.hw05.hw05.shell;

import com.hw05.hw05.converters.PrintConverter;
import com.hw05.hw05.model.Book;
import com.hw05.hw05.model.Comment;
import com.hw05.hw05.services.CommentService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;
import java.util.Optional;

@ShellComponent
public class CommentShell {

    private final CommentService commentService;

    private final ShellHelper shellHelper;

    private final PrintConverter<Comment> commentPrintConverter;

    private final ShellOutput shellOutput;

    public CommentShell(CommentService commentService, ShellHelper shellHelper,
                        PrintConverter<Comment> commentPrintConverter, ShellOutput shellOutput) {
        this.commentService = commentService;
        this.shellHelper = shellHelper;
        this.commentPrintConverter = commentPrintConverter;
        this.shellOutput = shellOutput;
    }

    @ShellMethod(value = "get comment", key = {"g c", "get comment"})
    public void getCommentById(@ShellOption(value = {"--id"}) Long commentId) {
        Optional<Comment> comment = commentService.getById(commentId);
        shellHelper.validateOptionalObjAndShowResult(comment, commentPrintConverter);
    }

    @ShellMethod(value = "get all comments", key = {"l c", "list comment"})
    public void getAllComments() {
        List<Comment> comments = commentService.getAll();
        shellHelper.validateObjColAndShowResult(comments, commentPrintConverter);
    }

    @ShellMethod(value = "add comment", key = {"a c", "add comment"})
    public void addComment(@ShellOption(value = {"--c"}) String content, @ShellOption(value = {"--bid"}) Long bookId) {
        Comment comment = new Comment(content, new Book(bookId));
        Comment createdComment = commentService.insert(comment);
        shellOutput.success(createdComment, commentPrintConverter);
    }

    @ShellMethod(value = "delete comment", key = {"d c", "delete comment"})
    public void deleteCommentById(@ShellOption(value = {"--id"}) Long commentId) {
        commentService.deleteById(commentId);
        List<Comment> comments = commentService.getAll();
        shellHelper.validateObjColAndShowResult(comments, commentPrintConverter);
    }

    @ShellMethod(value = "update comment", key = {"u c", "update comment"})
    public void updateComment(@ShellOption(value = {"--id"}) Long commentId,
                              @ShellOption(value = {"--c"}) String content) {
        Comment commentToUpdate = new Comment(commentId, content);
        Comment updatedComment = commentService.update(commentToUpdate);
        shellOutput.success(updatedComment, commentPrintConverter);
    }
}
