package user.gameLuncher;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterWindow extends JFrame implements Observable,ActionListener{
    String myname;
    String mycode;
    public JTextArea inputname;
    public JTextArea inputcode;
    JButton submit;
    public  RegisterWindow(){
        this.inputname=inputname;
        this.inputcode=inputcode;

        setTitle("注册");
        setBounds(650,300,0,0);
        setPreferredSize(new Dimension(300,300));
        Container container=getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();

        JLabel title=new JLabel();
        title.setText("注册");
        c.gridx=5;
        c.gridy=0;
        c.gridheight=2;
        c.gridwidth=GridBagConstraints.HORIZONTAL;
        container.add(title,c);

        JLabel name=new JLabel();
        JLabel code=new JLabel();
        name.setText("用户名：");
        code.setText("密码：");
        inputname=new JTextArea();
        inputcode=new JTextArea();
        inputname.setText("在此输入用户名");
        inputname.setLineWrap(true);
        inputcode.setText("在此输入密码");
        inputcode.setLineWrap(true);
        c.gridx=0;
        c.gridy=2;
        c.gridwidth=3;
        container.add(name,c);
        c.gridx=3;
        c.gridwidth=GridBagConstraints.REMAINDER;
        container.add(inputname,c);
        c.gridx=0;
        c.gridy=4;
        c.gridwidth=3;
        c.insets=(new Insets(1,0,0,0));
        container.add(code,c);
        c.gridx=3;
        c.gridwidth=GridBagConstraints.REMAINDER;
        container.add(inputcode,c);

        submit=new JButton("提交");
        c.gridx=5;
        c.gridy=7;
        c.gridwidth=3;
        c.gridheight=2;
        container.add(submit,c);

        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(false);
    }
    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
