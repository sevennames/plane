package gameLuncher;

import user.OnlineWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player extends JFrame implements ActionListener {
    JTextArea inputname;
    JTextArea inputcode;
    JButton submit;
    JButton register;
    JButton offline;
    RegisterWindow re;
    String name;
    public Player(){
        new Player("在此输入用户名","在此输入密码");
    }
    public Player(String defaultname,String defaultcode){
        re=new RegisterWindow();
        re.submit.addActionListener(this);
        setTitle("飞行棋");
        setBounds(650, 300, 650, 300);
        setPreferredSize(new Dimension(650,300));
        Container containPane=getContentPane();
        containPane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();//建立窗口

        JLabel title=new JLabel();
        title.setText("登录");
        c.gridx=5;
        c.gridy=0;
        c.gridheight=2;
        c.gridwidth=GridBagConstraints.HORIZONTAL;
        containPane.add(title,c);

        JLabel name=new JLabel();
        JLabel code=new JLabel();
        name.setText("用户名：");
        code.setText("密码：");
        inputname=new JTextArea();
        inputcode=new JTextArea();
        inputname.setText(defaultname);
        inputname.setLineWrap(true);
        inputcode.setText(defaultcode);
        inputcode.setLineWrap(true);
        c.gridx=0;
        c.gridy=2;
        c.gridwidth=3;
        containPane.add(name,c);
        c.gridx=3;
        c.gridwidth=GridBagConstraints.REMAINDER;
        containPane.add(inputname,c);
        c.gridx=0;
        c.gridy=4;
        c.gridwidth=3;
        c.insets=(new Insets(1,0,0,0));
        containPane.add(code,c);
        c.gridx=3;
        c.gridwidth=GridBagConstraints.REMAINDER;
        containPane.add(inputcode,c);

        submit=new JButton("登录");
        register=new JButton("注册");
        c.gridx=0;
        c.gridy=7;
        c.gridwidth=3;
        c.gridheight=2;
        containPane.add(submit,c);
        c.gridx=8;
        containPane.add(register,c);
        register.addActionListener(this);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //加入对于服务器端的应答
                new OnlineWindow();
            }
        });

        offline=new JButton("离线模式");
        c.gridx=0;
        c.gridy=9;
        c.gridwidth=GridBagConstraints.REMAINDER;
        c.gridheight=1;
        containPane.add(offline,c);


        pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==re.submit){
            setVisible(true);
            inputname.setText(re.inputname.getText());
            inputcode.setText(re.inputcode.getText());
            re.setVisible(false);
            re.inputname.setText("在此输入用户名");
            re.inputcode.setText("在此输入密码");
        }
        if(e.getSource()==register){
            setVisible(false);
            re.setVisible(true);
            //加入提交注册信息的代码
        }
    }

    public static void main(String args[]){
        new Player();
    }
}
