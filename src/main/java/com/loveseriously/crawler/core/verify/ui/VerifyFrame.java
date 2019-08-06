package com.loveseriously.crawler.core.verify.ui;

import com.loveseriously.crawler.core.verify.VerigyApption;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 验证码 框
 * @author lw
 * @date 2019-08-06
 */
public class VerifyFrame extends JFrame implements VerigyApption {
    JTextField txt = new JTextField();
    JButton okBtn = new JButton("确定");

    public VerifyFrame(){
        init();
        listener();
    }

    private void listener() {
        okBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String text = txt.getText();
                submit();
                System.exit(0);
            }
        });
    }

    private void init() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);   // 设置窗口关闭按钮的默认操作(点击关闭时退出进程)
        this.pack();
        this.setSize(350, 350);
        this.setLocationRelativeTo(null);                               // 把窗口位置设置到屏幕的中心
        this.setVisible(true);

        // 创建内容面板容器
        MyPanel panel = new MyPanel(this);
        panel.setLayout(null);
        panel.setLocation(120, 30);

        this.setContentPane(panel);

        // 设置按钮的坐标
        okBtn.setLocation(200, 180);
        // 设置按钮的宽高
        okBtn.setSize(100, 40);
        panel.add(okBtn);

        // 设置输入框的坐标
        txt.setLocation(50, 180);
        // 设置输入框的宽高
        txt.setSize(150, 40);
        panel.add(txt);
    }

    @Override
    public boolean submit() {
        System.out.println("VerifyFrame.submit");
        return false;
    }

    public static class MyPanel extends JPanel {
        private VerifyFrame frame;
        public MyPanel(VerifyFrame frame) {
            super();
            this.frame = frame;
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawImage(g);
        }

        /**
         * 1. 验证码 图片
         */
        private void drawImage(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            String filepath = "http://www.stats.gov.cn/waf_captcha/";
            URL url = null;
            try {
                url = new URL(filepath);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            Image image = Toolkit.getDefaultToolkit().getImage(url);
            // 绘制图片（如果宽高传的不是图片原本的宽高, 则图片将会适当缩放绘制）
            g2d.drawImage(image, 60, 20, image.getWidth(this), image.getHeight(this), this);
            g2d.dispose();
        }
    }

    public static void main(String[] args) {
        new VerifyFrame();
    }
}
