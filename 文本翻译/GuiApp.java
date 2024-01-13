package 文本翻译;

import javax.swing.*;

public class GuiApp extends JFrame
{

    private JLabel inputLan;
    private JLabel outLan;
    private JLabel inputLabel;
    private JLabel outputLabel;
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JComboBox<String> languageSelectorInput;
    private JComboBox<String> languageSelectorOutput;

    public GuiApp()
    {
        super("语言翻译程序");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 450);
        // 使用绝对布局
        setLayout(null);

        // 创建语言选择下拉框
        String[] languages1 = {"en", "zh", "auto"};
        String[] languages2 = {"zh", "en"};

        inputLan = new JLabel("源语言");
        inputLan.setBounds(80, 20, 40, 30);
        languageSelectorInput = new JComboBox<>(languages1);
        languageSelectorInput.setBounds(130, 20, 100, 30);

        outLan = new JLabel("目标语言");
        outLan.setBounds(250, 20, 80, 30);
        languageSelectorOutput = new JComboBox<>(languages2);
        languageSelectorOutput.setBounds(300, 20, 100, 30);

        // 创建输入文本框
        inputLabel = new JLabel("请在左边的框中输入要翻译的文本");
        inputLabel.setBounds(70, 60, 200, 30);
        inputTextArea = new JTextArea();
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane1 = new JScrollPane(inputTextArea);
        // 始终显示垂直滚动条
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // 不显示水平滚动条
        scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane1.setBounds(50, 90, 250, 300);


        // 创建显示翻译结果的文本区域
        outputLabel = new JLabel("翻译结果");
        outputLabel.setBounds(450, 60, 200, 30);
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane2 = new JScrollPane(outputTextArea);
        // 始终显示垂直滚动条
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // 不显示水平滚动条
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane2.setBounds(350, 90, 250, 300);

        // 创建翻译按钮
        JButton translateButton = new JButton("翻译");
        translateButton.setBounds(420, 20, 80, 30);
        translateButton.addActionListener(e -> translateText());

        // 添加组件到窗口
        add(inputLan);
        add(outLan);
        add(inputLabel);
        add(outputLabel);
        add(languageSelectorInput);
        add(languageSelectorOutput);
        add(scrollPane1);
        add(scrollPane2);
        add(translateButton);
    }

    private void translateText()
    {
        // 获取输入的文本
        String q = inputTextArea.getText();
        // 获取选择的输入和输出语言
        String from = (String) languageSelectorInput.getSelectedItem();
        String to = (String) languageSelectorOutput.getSelectedItem();
        //调用翻译方法
        String result = JsonGet.textTrans(from, to, q);
        //将结果添加到右侧框体
        outputTextArea.setText(result);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new GuiApp().setVisible(true);
            }
        });
    }
}
