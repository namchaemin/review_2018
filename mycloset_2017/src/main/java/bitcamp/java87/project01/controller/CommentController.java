package bitcamp.java87.project01.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bitcamp.java87.project01.common.JavaToJson;
import bitcamp.java87.project01.domain.Comment;
import bitcamp.java87.project01.domain.Style;
import bitcamp.java87.project01.domain.User;
import bitcamp.java87.project01.service.CommentService;
import bitcamp.java87.project01.service.StyleService;
import bitcamp.java87.project01.service.UserService;

@Controller
@RequestMapping("/comment/*")
public class CommentController {

  /// Field
  @Autowired
  @Qualifier("commentServiceImpl")
  private CommentService commentService;

  @Autowired
  @Qualifier("userServiceImpl")
  private UserService userService;

  @Autowired
  @Qualifier("StyleServiceImpl")
  private StyleService styleService;

  public CommentController() {
    System.out.println("여기는또 어디냐" + this.getClass());
  }

  @Value("#{commonProperties['pageUnit']}")
  int pageUnit;

  @Value("#{commonProperties['pageSize']}")
  int pageSize;

  // @RequestMapping("/addUserView.do")
  // public String addUserView() throws Exception {
  @RequestMapping(value = "addComment", method = RequestMethod.POST)
  public @ResponseBody Comment addComment(@ModelAttribute("comment") Comment comment, HttpSession session) throws Exception {
   System.out.println("addComment : start()...");

   
   User user = (User)session.getAttribute("user"); 
   comment.setUserNo(user.getUser_no());
   System.out.println("닉값:"+comment.getNick());
   comment.setNick(comment.getNick());
   System.out.println(comment);
  
   commentService.addComment(comment);
   
   System.out.println("/comment/addComment : POST 여기는 코멘트콘트롤러");
    
     return  comment;
  }
  
  @RequestMapping(value = "getComment", method = RequestMethod.GET)
  public @ResponseBody Comment getComment(@RequestParam("cmtNo") String cmtNo, HttpSession session) throws Exception {
   System.out.println("getComment : start()...");
   System.out.println("cmtNo값:"+cmtNo);
     
    Comment cmt = new Comment();
     cmt=commentService.getComment(Integer.parseInt(cmtNo));
   
   System.out.println("/comment/getComment : GET 여기는 코멘트콘트롤러");
    
     return  cmt;
  }
  
  
  @RequestMapping(value = "getCommentList", method = RequestMethod.GET)
   public @ResponseBody Map<String,Object> getCommentList(@RequestParam("styleNo") int styleNo,Model model ,HttpSession session) throws Exception {
   System.out.println("getCommentList : start()...");
   System.out.println("styleNo값:"+styleNo);
   List<Comment> commentList = commentService.getCommentList(styleNo);
   

   Map<String,Object> map = new HashMap<String,Object>();
   map.put("commentList", commentList);
   map.put("user", session.getAttribute("user"));
     return  map;
  }

  @RequestMapping(value="deleteComment", method = RequestMethod.POST)
  public @ResponseBody boolean deleteComment(@RequestParam("cmtNo")int cmtNo) throws Exception{
    System.out.println("deleteComment : start()...");
    try{
    commentService.deleteComment(cmtNo);
    }catch (Exception e) {
    	System.out.println("에러남");
    	return false;
	}
    return true;
  }
 
   
}