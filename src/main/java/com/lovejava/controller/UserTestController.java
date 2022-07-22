package com.lovejava.controller;

import com.lovejava.common.R;
import com.lovejava.pojo.*;
import com.lovejava.pojo.Record;
import com.lovejava.service.ExamService;
import com.lovejava.service.PaperService;
import com.lovejava.service.QuestionService;
import com.lovejava.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 学生考试相关功能
 */
@Controller
@RequestMapping("/user")
public class  UserTestController {

    @Autowired
    private ExamService examService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private PaperService paperService;

    @Autowired
    private RecordService recordService;

    /**
     * 根据用户选择的专业生成随机题目信息
     * @param major1 专业1
     * @param major2 专业2
     * @param resp    response
     * @param req     request
     * @return      是否成功
     */
    @ResponseBody
    @PostMapping("/settest")
    public R<String> attendTest(@RequestParam("major1")String major1,
                                @RequestParam("major2")String major2,
                                HttpServletResponse resp,
                                HttpServletRequest req){
        HttpSession session = req.getSession();
        Paper mustPaper = paperService.selectPaperByMajor("必选类");
        Integer numberSingle = mustPaper.getSingleNumber();
        Integer numberMulti = mustPaper.getMultiNumber();
        Integer numberJudge = mustPaper.getJudgeNumber();
        Integer numberSaq = mustPaper.getSaqNumber();
        Paper major1Paper = paperService.selectPaperByMajor(major1);
        Paper major2Paper = paperService.selectPaperByMajor(major2);

        Integer major1SingleQuestion=major1Paper.getSingleNumber();
        Integer major1MultiQuestion = major1Paper.getMultiNumber();
        Integer major1JudgeQuestion = major1Paper.getJudgeNumber();
        Integer major1SaqQuestion = major1Paper.getSaqNumber();;
        List<SingleQuestion>  mustSingleQuestionList= getQuestionList(numberSingle, "必选类", 1);
        session.setAttribute("test",mustSingleQuestionList);
        List<MultiQuestion>   mustMultiQuestionList = getQuestionList(numberMulti, "必选类", 2);

        List<JudgementQuestion> mustJudgeQuestionList= getQuestionList(numberJudge, "必选类", 3);

        List<SaqQuestion>   mustSaqQuestionList = getQuestionList(numberSaq, "必选类", 4);
        //返回Map
        Map<String, Object> map = new HashMap<>();
        List<SingleQuestion> major1SingleQuestionList =getQuestionList(major1SingleQuestion,major1,1);
        List<MultiQuestion> major1MultiQuestionList = getQuestionList(major1MultiQuestion,major1,2);
        List<JudgementQuestion> major1JudgementQuestionList = getQuestionList(major1JudgeQuestion,major1,3);
        List<SaqQuestion> major1SaQuestionList = getQuestionList(major1SaqQuestion,major1,4);

        Integer major2SingleQuestion = major2Paper.getSingleNumber();
        Integer major2MultiQuestion = major2Paper.getMultiNumber();
        Integer major2JudgeQuestion = major2Paper.getJudgeNumber();
        Integer major2SaqQuestion = major2Paper.getSaqNumber();
        List<SingleQuestion> major2SingleQuestionList =getQuestionList(major2SingleQuestion,major2,1);
        List<MultiQuestion> major2MultiQuestionList = getQuestionList(major2MultiQuestion,major2,2);
        List<JudgementQuestion> major2JudgementQuestionList = getQuestionList(major2JudgeQuestion,major2,3);
        List<SaqQuestion> major2SaqQuestionList = getQuestionList(major2SaqQuestion,major2,4);
        //必选题部分
        session.setAttribute("mustSingleQuestionList",mustSingleQuestionList);
        session.setAttribute("mustMultiQuestionList",mustMultiQuestionList);
        session.setAttribute("mustJudgeQuestionList",mustJudgeQuestionList);
        session.setAttribute("mustSaqQuestionList",mustSaqQuestionList);
        session.setAttribute("mustSingleQuestionListNumber",numberSingle);
        session.setAttribute("mustMultiQuestionListNumber",numberMulti);
        session.setAttribute("mustJudgeQuestionListNumber",numberJudge);
        session.setAttribute("mustSaqQuestionListNumber",numberSaq);
        //专业方向1
        session.setAttribute("major1SingleQuestionList",major1SingleQuestionList);
        session.setAttribute("major1MultiQuestionList",major1MultiQuestionList);
        session.setAttribute("major1JudgementQuestionList",major1JudgementQuestionList);
        session.setAttribute("major1SaqQuestionList",major1SaQuestionList);
        session.setAttribute("major1SingleQuestionListNumber",major1SingleQuestionList.size());
        session.setAttribute("major1MultiQuestionListNumber",major1MultiQuestionList.size());
        session.setAttribute("major1JudgementQuestionListNumber",major1JudgementQuestionList.size());
        session.setAttribute("major1SaqQuestionListNumber",major1SaQuestionList.size());
        //专业方向2
        session.setAttribute("major2SingleQuestionList",major2SingleQuestionList);
        session.setAttribute("major2MultiQuestionList",major2MultiQuestionList);
        session.setAttribute("major2JudgementQuestionList",major2JudgementQuestionList);
        session.setAttribute("major2SaqQuestionList",major2SaqQuestionList);
        session.setAttribute("major2SingleQuestionListNumber",major2SingleQuestionList.size());
        session.setAttribute("major2MultiQuestionListNumber",major2MultiQuestionList.size());
        session.setAttribute("major2JudgementQuestionListNumber",major2JudgementQuestionList.size());
        session.setAttribute("major2SaqQuestionListNumber",major2SaqQuestionList.size());

        session.setAttribute("major1",major1);
        session.setAttribute("major2",major2);
        Date startTime = new Date();
        req.getSession().setAttribute("startTime",startTime);
        return R.success("查取题目成功");
    }

    /**
     *用于抽取题目
     * @param number 题目数量
     * @param major 题目专业方向
     * @param flag  标志：题目类型
     * @return 对应的题目集合
     */
    private<T> List<T> getQuestionList(Integer number,String major,int flag){
        if(flag==1){
            return (List<T>)examService.getSingleQuestionByType(number,major);
        }
        else if(flag==2) return (List<T>)examService.getMultiQuestionByType(number, major);
        else if(flag==3) return (List<T>)examService.getJudgementQuestionByType(number, major);
        else return (List<T>)examService.getSaqQuestionByType(number, major);
    }

    /**
     * 用于获取答题界面题目信息
     * @param resp 处理浏览器拦截问题
     * @return  R<Map>,存放试题信息
     */
    @ResponseBody
    @PostMapping("/attendtest")
    public R<Map<String,List>> attendtest(HttpServletResponse resp,HttpServletRequest req){

        resp.setHeader("Access-Control-Allow-Origin","*");
        HttpSession session = req.getSession();


        //必选题部分
        Map<String,List> questionMap=new HashMap<>();
        Map<String,Integer> numberMap=new HashMap<>();
        questionMap.put("mustSingleQuestionList", (List) session.getAttribute("mustSingleQuestionList"));
        questionMap.put("mustMultiQuestionList", (List) session.getAttribute("mustMultiQuestionList"));
        questionMap.put("mustJudgeQuestionList", (List) session.getAttribute("mustJudgeQuestionList"));
        questionMap.put("mustSaqQuestionList", (List) session.getAttribute("mustSaqQuestionList"));
        numberMap.put("mustSingleQuestionListNumber", (Integer) session.getAttribute("mustSingleQuestionListNumber"));
        numberMap.put("mustMultiQuestionListNumber",(Integer) session.getAttribute("mustMultiQuestionListNumber"));
        numberMap.put("mustJudgeQuestionListNumber",(Integer) session.getAttribute("mustJudgeQuestionListNumber"));
        numberMap.put("mustSaqQuestionListNumber",(Integer) session.getAttribute("mustSaqQuestionListNumber"));

        //专业方向1
        questionMap.put("major1SingleQuestionList", (List) session.getAttribute("major1SingleQuestionList"));
        questionMap.put("major1MultiQuestionList", (List) session.getAttribute("major1MultiQuestionList"));
        questionMap.put("major1JudgementQuestionList", (List) session.getAttribute("major1JudgementQuestionList"));
        questionMap.put("major1SaqQuestionList", (List) session.getAttribute("major1SaqQuestionList"));
        numberMap.put("major1SingleQuestionListNumber",((List) session.getAttribute("major1SingleQuestionList")).size());
        numberMap.put("major1MultiQuestionListNumber",((List) session.getAttribute("major1MultiQuestionList")).size());
        numberMap.put("major1JudgementQuestionListNumber",((List) session.getAttribute("major1JudgementQuestionList")).size());
        numberMap.put("major1SaqQuestionListNumber",((List) session.getAttribute("major1SaqQuestionList")).size());

        //专业方向2
        questionMap.put("major2SingleQuestionList", (List) session.getAttribute("major2SingleQuestionList"));
        questionMap.put("major2MultiQuestionList", (List) session.getAttribute("major2MultiQuestionList"));
        questionMap.put("major2JudgementQuestionList", (List) session.getAttribute("major2JudgementQuestionList"));
        questionMap.put("major2SaqQuestionList", (List) session.getAttribute("major2SaqQuestionList"));
        numberMap.put("major2SingleQuestionListNumber",((List) session.getAttribute("major2SingleQuestionList")).size());
        numberMap.put("major2MultiQuestionListNumber",((List) session.getAttribute("major2MultiQuestionList")).size());
        numberMap.put("major2JudgementQuestionListNumber",((List) session.getAttribute("major2JudgementQuestionList")).size());
        numberMap.put("major2SaqQuestionListNumber",((List) session.getAttribute("major2SaqQuestionList")).size());

        //返回Map
        R<Map<String, List>> mapR = new R<>();
        mapR.setMap(numberMap);
        mapR.setCode(1);
        mapR.setData(questionMap);


        return mapR;
    }

    /**
     * 判断用户是否有做题记录，如果有返回一个list，否者返回错误信息
     * @param request 发送的请求
     * @return  返回试卷记录
     */
    @ResponseBody
    @PostMapping("/testrecords")
    public R<List<Record>> scoreRecords(HttpServletRequest request){

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer uid = user.getUid();
        List<Record> records=recordService.selectRecordByUid(uid);
        return R.success(records);
    }

    /**
     * 查看某一条具体的考试记录
     * @param rid   记录试卷号
     * @param request 获取用户信息
     * @return 试卷信息
     */
    @ResponseBody
    @PostMapping("/testrecord")
    public R<Map<String,List<Question>>> testRecord(@RequestParam("rid")Integer rid,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        Integer uid = user.getUid();
        Record record = recordService.selectRecordByUidAndRid(uid, rid);

        String[] singleArr = record.getSingleList().trim().split(" ");

        String[] multiArr = record.getMultiList().trim().split(" ");

        String[] judgeArr = record.getJudgeList().trim().split(" ");

        String[] saqArr = record.getSaqList().trim().split(" ");
        List<Question> singleQuestions = new ArrayList<>();
        List<Question> multiQuestions = new ArrayList<>();
        List<Question> judgeQuestions = new ArrayList<>();
        List<Question> saqQuestions = new ArrayList<>();
        Arrays.asList(singleArr).forEach(q->{
            singleQuestions.add(questionService.selectSingleByPrimaryKey(Integer.parseInt(q)));
                });
        Arrays.asList(multiArr).forEach(q->{
            multiQuestions.add(questionService.selectMultiByPrimaryKey(Integer.parseInt(q)));
        });
        Arrays.asList(judgeArr).forEach(q->{
            judgeQuestions.add(questionService.selectJudgeByPrimaryKey(Integer.parseInt(q)));
        });
        Arrays.asList(saqArr).forEach(q->{
            saqQuestions.add(questionService.selectSaqByPrimaryKey(Integer.parseInt(q)));
        });
        Map<String, List<Question>> questionMap = new HashMap<>();
        questionMap.put("singleQuestionList",singleQuestions);
        questionMap.put("multiQuestionList",multiQuestions);
        questionMap.put("judgeQuestionList",judgeQuestions);
        questionMap.put("saqQuestionList",saqQuestions);
        R<Map<String, List<Question>>> mapR = new R<>();
        mapR.setData(questionMap);
        Map<String, Integer> numberMap = new HashMap<>();
        numberMap.put("singleQuestionNumber",singleQuestions.size());
        numberMap.put("multiQuestionNumber",multiQuestions.size());
        numberMap.put("judgeQuestionNumber",judgeQuestions.size());
        numberMap.put("saqQuestionNumber",saqQuestions.size());
        mapR.setMap(numberMap);
        mapR.setCode(1);
        return mapR;

    }

    /**
     * 获取考试考试信息
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/testinfo")
    public R<Map<String,Object>> getTestInfo(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object major1 = session.getAttribute("major1");
        Object major2 = session.getAttribute("major2");
        Map<String, Object> map = new HashMap<>();
        map.put("major1",major1);
        map.put("major2",major2);

        map.put("major1SingleQuestionListNumber",session.getAttribute("major1SingleQuestionListNumber"));
        map.put("major2SingleQuestionListNumber",session.getAttribute("major2SingleQuestionListNumber"));
        map.put("major1MultiQuestionListNumber",session.getAttribute("major1MultiQuestionListNumber"));
        map.put("major2MultiQuestionListNumber",session.getAttribute("major2MultiQuestionListNumber"));
        map.put("major1JudgementQuestionListNumber",session.getAttribute("major1JudgementQuestionListNumber"));
        map.put("major2JudgementQuestionListNumber",session.getAttribute("major2JudgementQuestionListNumber"));
        map.put("major1SaqQuestionListNumber",session.getAttribute("major1SaqQuestionListNumber"));
        map.put("major2SaqQuestionListNumber",session.getAttribute("major2SaqQuestionListNumber"));
        return R.success(map);
    }

    /*
     * 完成考试后将考试记录插入数据库
     * @param request
     * @return 是否成功
     */
    @ResponseBody
    @RequestMapping("/finishtest")
    public R<String> finishTest(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer uid = user.getUid();

        StringBuilder singleBuilder = new StringBuilder();
        StringBuilder multiBuilder = new StringBuilder();
        StringBuilder judgeBulider = new StringBuilder();
        StringBuilder saqBulider = new StringBuilder();
        ((List<Question>) session.getAttribute("mustSingleQuestionList")).forEach(p->{
            singleBuilder.append(p.getQid().toString()+" ");
        });

        ((List<Question>) session.getAttribute("major1SingleQuestionList")).forEach(p->{
            singleBuilder.append(p.getQid().toString()+" ");
        });

        ((List<Question>) session.getAttribute("major2SingleQuestionList")).forEach(p->{
            singleBuilder.append(p.getQid().toString()+" ");
        });

        ((List<Question>) session.getAttribute("mustMultiQuestionList")).forEach(p->{
            multiBuilder.append(p.getQid().toString()+" ");
        });
        ((List<Question>) session.getAttribute("major1MultiQuestionList")).forEach(p->{
            multiBuilder.append(p.getQid().toString()+" ");
        });
        ((List<Question>) session.getAttribute("major2MultiQuestionList")).forEach(p->{
            multiBuilder.append(p.getQid().toString()+" ");
        });
        ((List<Question>) session.getAttribute("mustJudgeQuestionList")).forEach(p->{
            judgeBulider.append(p.getQid().toString()+" ");
        });
        ((List<Question>) session.getAttribute("major1JudgementQuestionList")).forEach(p->{
            judgeBulider.append(p.getQid().toString()+" ");
        });
        ((List<Question>) session.getAttribute("major2JudgementQuestionList")).forEach(p->{
            judgeBulider.append(p.getQid().toString()+" ");
        });
        ((List<Question>) session.getAttribute("mustSaqQuestionList")).forEach(p->{
            saqBulider.append(p.getQid().toString()+" ");
        });
        ((List<Question>) session.getAttribute("major1SaqQuestionList")).forEach(p->{
            saqBulider.append(p.getQid().toString()+" ");
        });
        ((List<Question>) session.getAttribute("major2SaqQuestionList")).forEach(p->{
            saqBulider.append(p.getQid().toString()+" ");
        });
        //考试记录插入数据库
        Record record = new Record();
        record.setUid(uid);
        record.setSingleList(singleBuilder.toString());
        record.setMultiList(multiBuilder.toString());
        record.setJudgeList(judgeBulider.toString());
        record.setSaqList(saqBulider.toString());
        record.setStartTime((Date) session.getAttribute("startTime"));
        record.setEndTime(new Date());
        recordService.insertOneRecord(record);
        return R.success("插入记录成功");
    }
}
