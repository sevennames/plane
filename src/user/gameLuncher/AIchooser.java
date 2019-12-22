package user.gameLuncher;

import user.OfflineWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AIchooser extends JFrame {
    NotifyBox messagebox;
    JButton yes;
    JButton cancel;
    JRadioButton goodai1=new JRadioButton("老实人");
    JRadioButton goodai2=new JRadioButton("老实人");
    JRadioButton goodai3=new JRadioButton("老实人");
    JRadioButton goodmyai=new JRadioButton("老实人");
    JRadioButton badai1=new JRadioButton("缺德佬");
    JRadioButton badai2=new JRadioButton("缺德佬");
    JRadioButton badai3=new JRadioButton("缺德佬");
    JRadioButton badmyai=new JRadioButton("缺德佬");
    JComboBox<String> colorChooser;
    public AIchooser(){
        setTitle("飞行棋");
        setBounds(600, 250,0 , 0);
        setPreferredSize(new Dimension(700,500));
        Container containPane=getContentPane();
        containPane.setLayout(new GridLayout(9,1));
        GridBagConstraints c=new GridBagConstraints();

        messagebox=new NotifyBox();

        JLabel title=new JLabel("请选择敌人难度与托管AI行为",JLabel.CENTER);
        JLabel introduce=new JLabel("",JLabel.CENTER);
        JLabel detial=new JLabel("",JLabel.CENTER);
        title.setForeground(Color.blue);
        c.gridwidth=GridBagConstraints.HORIZONTAL;
        containPane.add(title);
        containPane.add(introduce,c);
        containPane.add(detial,c);


        ButtonGroup bg1=new ButtonGroup();
        ButtonGroup bg2=new ButtonGroup();
        ButtonGroup bg3=new ButtonGroup();
        ButtonGroup bg4=new ButtonGroup();
        JPanel p1=new JPanel();
        p1.setBorder(BorderFactory.createTitledBorder("AI1"));
        JPanel p2=new JPanel();
        p2.setBorder(BorderFactory.createTitledBorder("AI2"));
        JPanel p3=new JPanel();
        p3.setBorder(BorderFactory.createTitledBorder("AI3"));
        JPanel p4=new JPanel();
        p4.setBorder(BorderFactory.createTitledBorder("托管AI"));
        bg1.add(goodai1);
        bg1.add(badai1);
        goodai1.setSelected(true);
        p1.add(goodai1);
        p1.add(badai1);
        goodai1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                introduce.setText("温和的AI,不会主动攻击敌人");
                detial.setText("如果移动某个棋子会击毁别人的飞机，则会尽量选择移动别的棋子");
            }
        });
        badai1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                introduce.setText("缺德的AI，一切以缺德为目的");
                detial.setText("即使只有缺德的可能性也会想尽办法击毁别人的飞机");
            }
        });

        bg2.add(goodai2);
        bg2.add(badai2);
        goodai2.setSelected(true);
        p2.add(goodai2);
        p2.add(badai2);
        goodai2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                introduce.setText("温和的AI,不会主动攻击敌人");
                detial.setText("如果移动某个棋子会击毁别人的飞机，则会尽量选择移动别的棋子");
            }
        });
        badai2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                introduce.setText("缺德的AI，一切以缺德为目的");
                detial.setText("即使只有缺德的可能性也会想尽办法击毁别人的飞机");
            }
        });

        bg3.add(goodai3);
        bg3.add(badai3);
        goodai3.setSelected(true);
        p3.add(goodai3);
        p3.add(badai3);
        goodai3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                introduce.setText("温和的AI,不会主动攻击敌人");
                detial.setText("如果移动某个棋子会击毁别人的飞机，则会尽量选择移动别的棋子");
            }
        });
        badai3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                introduce.setText("缺德的AI，一切以缺德为目的");
                detial.setText("即使只有缺德的可能性也会想尽办法击毁别人的飞机");
            }
        });

        bg4.add(goodmyai);
        bg4.add(badmyai);
        goodmyai.setSelected(true);
        p4.add(goodmyai);
        p4.add(badmyai);
        goodmyai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                introduce.setText("温和的AI,不会主动攻击敌人");
                detial.setText("如果移动某个棋子会击毁别人的飞机，则会尽量选择移动别的棋子");
            }
        });
        badmyai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                introduce.setText("缺德的AI，一切以缺德为目的");
                detial.setText("即使只有缺德的可能性也会想尽办法击毁别人的飞机");
            }
        });

        c.gridwidth=GridBagConstraints.HORIZONTAL;
        containPane.add(p4,c);
        containPane.add(p1,c);
        containPane.add(p2,c);
        containPane.add(p3,c);

        colorChooser=new JComboBox<>();
        colorChooser.addItem("蓝色");
        colorChooser.addItem("红色");
        colorChooser.addItem("黄色");
        colorChooser.addItem("绿色");
        containPane.add(colorChooser);


        JPanel select=new JPanel();
        JButton sure=new JButton("确定");
        JButton cancel=new JButton("取消");
        sure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String AI1;
                String AI2;
                String AI3;
                String myAI;
                if(goodai1.isSelected()){
                    AI1="goodAI";
                }else{
                    AI1="badAI";
                }
                if(goodai2.isSelected()){
                    AI2="goodAI";
                }else{
                    AI2="badAI";
                }
                if(goodai3.isSelected()){
                    AI3="goodAI";
                }else{
                    AI3="badAI";
                }
                if(goodmyai.isSelected()){
                    myAI="goodAI";
                }else{
                    myAI="badAI";
                }
                new OfflineWindow(colorChooser.getItemAt(colorChooser.getSelectedIndex()),AI1,AI2,AI3,myAI);
                setVisible(false);
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messagebox.setOrder("AIchooser:Be Visible");
                messagebox.notifyObservers();
                dispose();
            }
        });
        select.add(sure);
        select.add(cancel);
        containPane.add(select);




        pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String args[]){
        new AIchooser();
    }

}
