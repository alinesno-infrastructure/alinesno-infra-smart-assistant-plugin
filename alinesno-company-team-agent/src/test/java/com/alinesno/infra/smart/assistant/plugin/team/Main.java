package com.alinesno.infra.smart.assistant.plugin.team;

import com.alinesno.infra.smart.assistant.role.utils.YAMLMapper;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {

        QuestionBean question1 = new QuestionBean();
        question1.setTitle("世界上最高的山峰是哪座？");
        question1.setDesc("这里考的是地理知识");
        question1.setType("single_choice");
        question1.setScore(10);
        question1.setAnswers(Arrays.asList("A: 珠穆朗玛峰", "B: 喜马拉雅山", "C: 阿尔卑斯山", "D: 安第斯山"));
        question1.setRightAnswer("B");

        QuestionBean question2 = new QuestionBean();
        question2.setTitle("以下哪些是动物？");
        question2.setDesc("这里考的是生物知识");
        question2.setType("multiple_choice");
        question2.setScore(10);
        question2.setAnswers(Arrays.asList("A: 苹果", "B: 狗", "C: 桌子", "D: 猫"));
        question2.setRightAnswer("B|D");

        QuestionBean question3 = new QuestionBean();
        question3.setTitle("中国的首都是哪个城市？");
        question3.setDesc("这里考的是地理知识");
        question3.setType("fill_in_the_blank");
        question3.setScore(10);
        question3.setAnswers(Arrays.asList("北京"));

        QuestionBean question4 = new QuestionBean();
        question4.setTitle("以下哪个是世界上最大的洲？");
        question4.setDesc("这里考的是地理知识");
        question4.setType("single_choice");
        question4.setScore(10);
        question4.setAnswers(Arrays.asList("A: 非洲", "B: 亚洲", "C: 欧洲", "D: 北美洲"));
        question4.setRightAnswer("B");

        QuestionBean question5 = new QuestionBean();
        question5.setTitle("以下哪些是水的三态？");
        question5.setDesc("这里考的是物理知识");
        question5.setType("multiple_choice");
        question5.setScore(10);
        question5.setAnswers(Arrays.asList("A: 气态", "B: 固态", "C: 液态", "D: 火态"));
        question5.setRightAnswer("A|B|C");

        QuestionBean question6 = new QuestionBean();
        question6.setTitle("1加1等于几？");
        question6.setDesc("这里考的是数学知识");
        question6.setType("fill_in_the_blank");
        question6.setScore(10);
        question6.setAnswers(Arrays.asList("2"));

        QuestionBean question7 = new QuestionBean();
        question7.setTitle("以下哪个是中国的四大发明？");
        question7.setDesc("这里考的是历史知识");
        question7.setType("single_choice");
        question7.setScore(10);
        question7.setAnswers(Arrays.asList("A: 火药", "B: 电视", "C: 电话", "D: 纸张"));
        question7.setRightAnswer("A");

        QuestionBean question8 = new QuestionBean();
        question8.setTitle("以下哪些是颜色？");
        question8.setDesc("这里考的是常识知识");
        question8.setType("multiple_choice");
        question8.setScore(10);
        question8.setAnswers(Arrays.asList("A: 香蕉", "B: 红", "C: 橙", "D: 绿"));
        question8.setRightAnswer("B|C|D");

        QuestionBean question9 = new QuestionBean();
        question9.setTitle("中国的国旗是什么颜色？");
        question9.setDesc("这里考的是国旗知识");
        question9.setType("fill_in_the_blank");
        question9.setScore(10);
        question9.setAnswers(Arrays.asList("红色"));

        QuestionBean question10 = new QuestionBean();
        question10.setTitle("以下哪个是世界上最长的河流？");
        question10.setDesc("这里考的是地理知识");
        question10.setType("single_choice");
        question10.setScore(10);
        question10.setAnswers(Arrays.asList("A: 长江", "B: 尼罗河", "C: 亚马逊河", "D: 黄河"));
        question10.setRightAnswer("B") ;
        
        List<QuestionBean> questionList = Arrays.asList(
                question1, question2, question3, question4, question5,
                question6, question7, question8, question9, question10
        );

        System.out.println(YAMLMapper.toYAML(questionList));

        List<TeamTrainingToExamSpecialist.QuestionBean> questionBeans = YAMLMapper.listFromYAML(YAMLMapper.toYAML(questionList) , TeamTrainingToExamSpecialist.QuestionBean.class) ;
        System.out.println(questionBeans);
    }

    // 问题记录实体
    @Data
    public static class QuestionBean {
        private String title;
        private String desc;
        private String type;
        private int score;
        private List<String> answers;
        private String rightAnswer;
    }
}
