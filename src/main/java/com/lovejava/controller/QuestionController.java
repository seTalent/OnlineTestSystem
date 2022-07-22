package com.lovejava.controller;

import com.lovejava.common.R;
import com.lovejava.pojo.JudgementQuestion;
import com.lovejava.pojo.MultiQuestion;
import com.lovejava.pojo.SaqQuestion;
import com.lovejava.pojo.SingleQuestion;
import com.lovejava.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 试题的录入、修改、删除
 */
@Controller
@RequestMapping("/admin")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * 录入单选题
     * @param resp  允许跨域通信
     * @param ques  题干
     * @param major  专业方向
     * @param a  选项A
     * @param b  选项B
     * @param c  选项C
     * @param d  选项D
     * @return  录入反馈信息
     */
    @ResponseBody
    @PostMapping("/show/addsingle")
    public R<String> addSingle(HttpServletResponse resp,
                               @RequestParam("ques") String ques,
                               @RequestParam("major")String major,
                               @RequestParam("a") String a,
                               @RequestParam("b") String b,
                               @RequestParam("c") String c,
                               @RequestParam("d") String d){
        SingleQuestion singleQuestion=new SingleQuestion();
        singleQuestion.setQuestion(ques);
        singleQuestion.setMajor(major);
        singleQuestion.setAnswerA(a);
        singleQuestion.setAnswerB(b);
        singleQuestion.setAnswerC(c);
        singleQuestion.setAnswerD(d);
        if(questionService.insert(singleQuestion)!=0) {
            return R.success("录入单选题成功");
        }
        else return R.error("录入单选题失败");
    }

    /**
     * 录入多选题
     * @param resp  允许跨域通信
     * @param ques  题干
     * @param major  专业方向
     * @param a  选项A
     * @param b  选项B
     * @param c  选项C
     * @param d  选项D
     * @return  录入反馈信息
     */
    @ResponseBody
    @PostMapping("/show/addmulti")
    public R<String> addMulti(HttpServletResponse resp,
                              @RequestParam("ques") String ques,
                              @RequestParam("major")String major,
                              @RequestParam("a") String a,
                              @RequestParam("b") String b,
                              @RequestParam("c") String c,
                              @RequestParam("d") String d){
        MultiQuestion multiQuestion=new MultiQuestion();
        multiQuestion.setQuestion(ques);
        multiQuestion.setMajor(major);
        multiQuestion.setAnswerA(a);
        multiQuestion.setAnswerB(b);
        multiQuestion.setAnswerC(c);
        multiQuestion.setAnswerD(d);

        if(questionService.insert(multiQuestion)!=0) {
            return R.success("录入多选题成功");
        }
        else return R.error("录入多选题失败");
    }

    /**
     * 录入判断题
     * @param resp  允许跨域通信
     * @param ques  题干
     * @param major  专业方向
     * @return  录入反馈信息
     */
    @ResponseBody
    @PostMapping("/show/addjudge")
    public R<String> addJudge(HttpServletResponse resp,
                              @RequestParam("ques") String ques,
                              @RequestParam("major")String major){
        JudgementQuestion judgementQuestion=new JudgementQuestion();
        judgementQuestion.setQuestion(ques);
        judgementQuestion.setMajor(major);

        if(questionService.insert(judgementQuestion)!=0) {
            return R.success("录入判断题成功");
        }
        else return R.error("录入判断题失败");
    }

    /**
     * 录入简答题
     * @param resp  允许跨域通信
     * @param ques  题干
     * @param major  专业方向
     * @return  录入反馈信息
     */
    @ResponseBody
    @PostMapping("/show/addsaq")
    public R<String> addSaq(HttpServletResponse resp,
                            @RequestParam("ques") String ques,
                            @RequestParam("major")String major){
        SaqQuestion saqQuestion=new SaqQuestion();
        saqQuestion.setQuestion(ques);
        saqQuestion.setMajor(major);

        if(questionService.insert(saqQuestion)!=0) {
            return R.success("录入简答题成功");
        }
        else return R.error("录入简答题失败");
    }

    /**
     * 修改单选题
     * @param resp  允许跨域通信
     * @param qid  根据单选id查找修改的题目
     * @param ques  题干
     * @param major  专业方向
     * @param a  选项A
     * @param b  选项B
     * @param c  选项C
     * @param d  选项D
     * @return  修改反馈信息
     */
    @ResponseBody
    @PostMapping("/show/editsingle")
    public R<String> editSingle(HttpServletResponse resp,
                               @RequestParam("qid") Integer qid,
                               @RequestParam("ques") String ques,
                               @RequestParam("major")String major,
                               @RequestParam("a") String a,
                               @RequestParam("b") String b,
                               @RequestParam("c") String c,
                               @RequestParam("d") String d){
        SingleQuestion singleQuestion=questionService.selectSingleByPrimaryKey(qid);
        singleQuestion.setQuestion(ques);
        singleQuestion.setMajor(major);
        singleQuestion.setAnswerA(a);
        singleQuestion.setAnswerB(b);
        singleQuestion.setAnswerC(c);
        singleQuestion.setAnswerD(d);

        if(questionService.updateByPrimaryKeySelective(singleQuestion)!=0) {
            return R.success("修改单选题成功");
        }
        else return R.error("修改单选题失败");
    }

    /**
     * 修改多选题
     * @param resp  允许跨域通信
     * @param qid  根据多选id查找修改的题目
     * @param ques  题干
     * @param major  专业方向
     * @param a  选项A
     * @param b  选项B
     * @param c  选项C
     * @param d  选项D
     * @return  修改反馈信息
     */
    @ResponseBody
    @PostMapping("/show/editmulti")
    public R<String> editMulti(HttpServletResponse resp,
                              @RequestParam("qid") Integer qid,
                              @RequestParam("ques") String ques,
                              @RequestParam("major")String major,
                              @RequestParam("a") String a,
                              @RequestParam("b") String b,
                              @RequestParam("c") String c,
                              @RequestParam("d") String d){
        MultiQuestion multiQuestion=questionService.selectMultiByPrimaryKey(qid);
        multiQuestion.setQuestion(ques);
        multiQuestion.setMajor(major);
        multiQuestion.setAnswerA(a);
        multiQuestion.setAnswerB(b);
        multiQuestion.setAnswerC(c);
        multiQuestion.setAnswerD(d);

        if(questionService.updateByPrimaryKeySelective(multiQuestion)!=0) {
            return R.success("修改多选题成功");
        }
        else return R.error("修改多选题失败");
    }

    /**
     * 修改判断题
     * @param resp  允许跨域通信
     * @param qid  修改的判断题id
     * @param ques  题干
     * @param major  专业方向
     * @return  录入反馈信息
     */
    @ResponseBody
    @PostMapping("/show/editjudge")
    public R<String> editJudge(HttpServletResponse resp,
                               @RequestParam("qid") Integer qid,
                               @RequestParam("ques") String ques,
                               @RequestParam("major")String major){
        JudgementQuestion judgementQuestion=questionService.selectJudgeByPrimaryKey(qid);
        judgementQuestion.setQuestion(ques);
        judgementQuestion.setMajor(major);

        if(questionService.updateByPrimaryKeySelective(judgementQuestion)!=0) {
            return R.success("修改判断题成功");
        }
        else return R.error("修改判断题失败");
    }

    /**
     * 修改简答题
     * @param resp  允许跨域通信
     * @param ques  题干
     * @param major  专业方向
     * @return  录入反馈信息
     */
    @ResponseBody
    @PostMapping("/show/editsaq")
    public R<String> editSaq(HttpServletResponse resp,
                             @RequestParam("qid") Integer qid,
                             @RequestParam("ques") String ques,
                             @RequestParam("major")String major){
        SaqQuestion saqQuestion=questionService.selectSaqByPrimaryKey(qid);
        saqQuestion.setQuestion(ques);
        saqQuestion.setMajor(major);

        if(questionService.updateByPrimaryKeySelective(saqQuestion)!=0) {
            return R.success("修改简答题成功");
        }
        else return R.error("修改简答题失败");
    }

    /**
     * 删除单选题
     * @param resp 允许跨域通信
     * @param qid  单选题题号
     * @return  删除成功信息
     */
    @ResponseBody
    @PostMapping("/show/removesingle")
    public R<String> removeSingle(HttpServletResponse resp,
                                  @RequestParam Integer qid){
        if(questionService.deleteSingleByPrimaryKey(qid)!=0){
            return R.success("成功删除该单选题");
        }
        else return R.error("删除失败");
    }

    /**
     * 删除多选题
     * @param resp 允许跨域通信
     * @param qid  多选题题号
     * @return  删除成功信息
     */

    @ResponseBody
    @PostMapping("/show/removemulti")
    public R<String> removeMulti(HttpServletResponse resp,
                                 @RequestParam Integer qid){
        if(questionService.deleteMultiByPrimaryKey(qid)!=0){
            return R.success("成功删除该多选题");
        }
        else return R.error("删除失败");
    }

    /**
     * 删除判断题
     * @param resp 允许跨域通信
     * @param qid  判断题题号
     * @return  删除成功信息
     */
    @ResponseBody
    @PostMapping("/show/removejudge")
    public R<String> removeJudge(HttpServletResponse resp,
                                 @RequestParam Integer qid){
        if(questionService.deleteJudgementByPrimaryKey(qid)!=0) {
            return R.success("成功删除该判断题");
        }
        else return R.error("删除失败");
    }

    /**
     * 删除简答题
     * @param resp 允许跨域通信
     * @param qid  简答题题号
     * @return  删除成功信息
     */
    @ResponseBody
    @PostMapping("/show/removesaq")
    public R<String> removeSaq(HttpServletResponse resp,
                               @RequestParam Integer qid){
        if(questionService.deleteSaqByPrimaryKey(qid)!=0) {
            return R.success("成功删除该简答题");
        }
        else return R.error("删除失败");
    }

    /**
     * 返回单选题数据
     * @param resp 允许跨域通信
     * @param major 专业
     * @return 单选题List
     */
    @ResponseBody
    @PostMapping("/show/single")
    public R<List<SingleQuestion>> showSingleQues(HttpServletResponse resp,
                                                  @RequestParam String major){
        List<SingleQuestion>allSingleList=questionService.getAllSingle(major);
        return R.success(allSingleList);
    }

    /**
     * 返回多选题数据
     * @param resp 允许跨域通信
     * @param major 专业
     * @return List
     */
    @ResponseBody
    @PostMapping("/show/multi")
    public R<List<MultiQuestion>> showMultiQues(HttpServletResponse resp,
                                               @RequestParam String major){
        List<MultiQuestion>allMultiList=questionService.getAllMulti(major);
        return R.success(allMultiList);
    }

    /**
     * 返回判断题数据
     * @param resp 允许跨域通信
     * @param major 专业
     * @return List
     */
    @ResponseBody
    @PostMapping("/show/judge")
    public R<List<JudgementQuestion>> showJudgeQues(HttpServletResponse resp,
                                                   @RequestParam String major){
        List<JudgementQuestion>allJudgeList=questionService.getAllJudge(major);
        return R.success(allJudgeList);
    }

    /**
     * 返回简答题数据
     * @param resp 允许跨域通信
     * @param major 专业
     * @return List
     */
    @ResponseBody
    @PostMapping("/show/saq")
    public R<List<SaqQuestion>> showSaqQues(HttpServletResponse resp,
                                           @RequestParam String major){
        List<SaqQuestion>allSaqList=questionService.getAllSaq(major);
        return R.success(allSaqList);
    }

    /**
     * 查找单选题
     * @param resp 允许跨域通信
     * @param info 查找信息
     * @param major 专业
     * @return List
     */
    @ResponseBody
    @PostMapping("/show/single/search")
    public R<List<SingleQuestion>> searchSingle(HttpServletResponse resp,
                                               @RequestParam String info,
                                               @RequestParam String major){
        List<SingleQuestion>searchSingleList=questionService.getAllSingle(major);
        searchSingleList.removeIf(s -> !s.getQuestion().contains(info));
        return R.success(searchSingleList);
    }

    /**
     * 查找多选题
     * @param resp
     * @param info 查找信息
     * @param major 专业
     * @return
     */
    @ResponseBody
    @PostMapping("/show/multi/search")
    public R<List<MultiQuestion>> searchMulti(HttpServletResponse resp,
                                             @RequestParam String info,
                                             @RequestParam String major){
        List<MultiQuestion>searchMultiList=questionService.getAllMulti(major);
        searchMultiList.removeIf(s -> !s.getQuestion().contains(info));
        return R.success(searchMultiList);
    }

    /**
     * 查找判断题
     * @param resp
     * @param info 查找信息
     * @param major 专业
     * @return
     */
    @ResponseBody
    @PostMapping("/show/judge/search")
    public R<List<JudgementQuestion>> searchJudge(HttpServletResponse resp,
                                                 @RequestParam String info,
                                                 @RequestParam String major){
        List<JudgementQuestion>searchJudgeList=questionService.getAllJudge(major);
        searchJudgeList.removeIf(s -> !s.getQuestion().contains(info));
        return R.success(searchJudgeList);
    }

    /**
     * 查找简答题
     * @param resp
     * @param info 查找信息
     * @param major 专业
     * @return
     */
    @ResponseBody
    @PostMapping("/show/saq/search")
    public R<List<SaqQuestion>>searchSaq(HttpServletResponse resp,
                                         @RequestParam String info,
                                         @RequestParam String major){
        List<SaqQuestion>searchSaqList=questionService.getAllSaq(major);
        searchSaqList.removeIf(s -> !s.getQuestion().contains(info));
        return R.success(searchSaqList);
    }
}



