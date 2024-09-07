//Template.java

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

class ClockLabel extends JLabel implements ActionListener
{
    String type;
    SimpleDateFormat sdf;

    public ClockLabel(String type)
    {
        this.type = type;
        setForeground(Color.green);

        switch(type)
        {
            case "date" : sdf = new SimpleDateFormat(" MMMM dd yyyy");
                setFont(new Font("sans-serif", Font.PLAIN,12));
                setHorizontalAlignment(SwingConstants.LEFT);
                break;
            case "time" : sdf = new SimpleDateFormat("hh:mm:ss a");
                setFont(new Font("sans-serif",Font.PLAIN,40));
                setHorizontalAlignment(SwingConstants.CENTER);
                break;
            case "day" : sdf = new SimpleDateFormat("EEEE ");
                setFont(new Font("sans-serif",Font.PLAIN,16));
                setHorizontalAlignment(SwingConstants.RIGHT);
                break;
            default :sdf = new SimpleDateFormat();
                break;
        }

        Timer t = new Timer(1000,this);
        t.start();
    }

    public void actionPerformed(ActionEvent ae)
    {
        Date d = new Date();
        setText(sdf.format(d));
    }
}

class Template extends JFrame implements Serializable ,ActionListener
{
    JPanel header;
    JPanel content;
    JPanel top;

    ClockLabel dayLable;
    ClockLabel timeLable;
    ClockLabel dateLable;

    JButton minimize , exit;

    public Template()
    {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        GridBagLayout grid = new GridBagLayout();
        setLayout(grid);

        top = new JPanel();
        top.setBackground(Color.LIGHT_GRAY);

        top.setLayout(null);
            getContentPane().add(top,new GridBagConstraints(0,0,1,1,1,5,GridBagConstraints.BASELINE,GridBagConstraints.BOTH,new Insets(0,0,0,0),0,0));

        header = new JPanel();
        header.setLayout(null);

        header.setBackground(Color.white);
            getContentPane().add(header,new GridBagConstraints(0,1,1,1,1,20,GridBagConstraints.BASELINE,GridBagConstraints.BOTH,new Insets(0,0,0,0),0,0));

        content = new JPanel();
        content.setLayout(null);
        content.setBackground(new Color(0,50,120));
        JScrollPane jsp = new JScrollPane(content,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        getContentPane().add(jsp,new GridBagConstraints(0,2,1,1,1,75,GridBagConstraints.BASELINE,GridBagConstraints.BOTH,new Insets(0,0,0,0),0,0));
        setTitle("Marvellous Packer-Unpacker");

        //Clock();
        CloseAndMin();
    }

    public void CloseAndMin()
    {
        minimize = new JButton("-");
        minimize.setBackground(Color.LIGHT_GRAY);
        minimize.setBounds(MAXIMIZED_HORIZ,0,45,20);

        exit = new JButton("X");
        exit.setHorizontalAlignment(SwingConstants.CENTER);
        exit.setBackground(Color.LIGHT_GRAY);
        exit.setHorizontalTextPosition(0);
        exit.setBounds(MAXIMIZED_HORIZ+45,0,45,20);
        
        top.add(minimize);
        top.add(exit);

        exit.addActionListener(this);
        minimize.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==exit)
        {
            this.setVisible(false);
            System.exit(0);
        }

        if(ae.getSource()== minimize)
        {
            setState(JFrame.ICONIFIED);
        }
    }

/*    public void Clock()
    {
        dateLable = new ClockLabel("date");
        timeLable = new ClockLabel("time");
        dayLable = new ClockLabel("day");

        dateLable.setForeground(Color.blue);
        timeLable.setForeground(Color.blue);
        dayLable.setForeground(Color.blue);

        dayLable.setFont(new Font("Century",Font.BOLD,15));

        dayLable.setBounds(70,10,200,100);

        dateLable.setFont(new Font("Century",Font.BOLD,15));

        dateLable.setBounds(80,-40,200,100);

        timeLable.setFont(new Font("Century",Font.BOLD,15));

        timeLable.setBounds(70,-15,200,100);

        header.add(dateLable);
        header.add(timeLable);
        header.add(dayLable);
    }
*/
    public void ClockHome()
    {
        dateLable = new ClockLabel("date");
        timeLable = new ClockLabel("time");
        dayLable = new ClockLabel("day");

        dateLable.setForeground(Color.blue);
        timeLable.setForeground(Color.blue);
        dayLable.setForeground(Color.blue);
        dayLable.setFont(new Font("Century",Font.BOLD,15));
        dayLable.setBounds(200,20,200,100);
        dateLable.setFont(new Font("Century",Font.BOLD,15));
        dateLable.setBounds(300,-40,200,100);

        timeLable.setFont(new Font("Century",Font.BOLD,15));
        timeLable.setBounds(260,-10,200,100);

        header.add(dateLable);
        header.add(timeLable);
        header.add(dayLable);
    }
}