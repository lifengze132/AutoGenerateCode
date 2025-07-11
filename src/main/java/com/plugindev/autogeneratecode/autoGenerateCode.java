package com.plugindev.autogeneratecode;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.codehaus.plexus.util.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class autoGenerateCode {
    JPanel JPanel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JLabel Label1;
    private JLabel Label2;
    private JLabel Label3;
    private JLabel Label4;
    private JLabel Label5;
    private JLabel Label6;
    private JLabel Label7;
    private JLabel Label8;
    private JLabel Label9;
    private JLabel Label10;

    private JTextArea textArea1;
    private JButton executeButton;
    private JButton connectDatabaseTestButton;

    private JRadioButton unionSelectSqlRadioButton;
    private JRadioButton tableRadioButton;
    private JTextPane textPane1;




    private JTextArea resultTextArea;



    public autoGenerateCode() {

        // 主面板使用BorderLayout分为左右两部分
        JPanel1 = new JPanel(new BorderLayout(10, 10));
        JPanel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 左侧面板（表单区域）
        JPanel leftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // 右侧面板（SQL输入区域）与左侧表单区域顶部对齐
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("SQL Input Area"));
        rightPanel.setPreferredSize(new Dimension(400, 300));

        // 初始化字段
        Label1 = new JLabel("Database URL");
        textField1 = new JTextField(25);
        Label2 = new JLabel("Database Username");
        textField2 = new JTextField(25);
        Label3 = new JLabel("Database Password");
        textField3 = new JTextField(25);
        Label4 = new JLabel("DAO Layer Path");
        textField4 = new JTextField(25);
        Label5 = new JLabel("Entity Layer Path");
        textField5 = new JTextField(25);
        Label6 = new JLabel("Package Path");
        textField6 = new JTextField(25);
        Label7 = new JLabel("Table Name");
        textField7 = new JTextField(25);


        // 初始化 Label10 和 textField8
        Label10 = new JLabel("File Name");
        textField8 = new JTextField(20);

        // 创建一个新的 JPanel 来容纳 Label10 和 textField8
        JPanel rightTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // 左对齐
        rightTopPanel.add(Label10);
        rightTopPanel.add(textField8);
        // 设置固定高度（可选）
        rightTopPanel.setPreferredSize(new Dimension(rightPanel.getWidth(), 30));
        // 将新面板添加到右侧面板的顶部
        rightPanel.add(rightTopPanel, BorderLayout.NORTH);

        // 初始化右侧的SQL输入区域（与Database URL对齐）
        textPane1 = new JTextPane();
        textPane1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane sqlScrollPane = new JScrollPane(textPane1);
        rightPanel.add(sqlScrollPane, BorderLayout.CENTER);

        // 其他组件初始化
        textArea1 = new JTextArea(3, 25);
        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);

        connectDatabaseTestButton = new JButton("Connect DB");
        executeButton = new JButton("Generate Code");

        tableRadioButton = new JRadioButton("Table Mode");
        unionSelectSqlRadioButton = new JRadioButton("Union SQL Mode");

        // 设置固定标签宽度
        int labelWidth = 130;
        setFixedLabelWidth(Label1, labelWidth);
        setFixedLabelWidth(Label2, labelWidth);
        setFixedLabelWidth(Label3, labelWidth);
        setFixedLabelWidth(Label4, labelWidth);
        setFixedLabelWidth(Label5, labelWidth);
        setFixedLabelWidth(Label6, labelWidth);
        setFixedLabelWidth(Label7, labelWidth);

        // 添加组件到左侧面板
        addPairWithGbc(Label1, textField1, leftPanel, gbc, 0);
        addPairWithGbc(Label2, textField2, leftPanel, gbc, 1);
        addPairWithGbc(Label3, textField3, leftPanel, gbc, 2);
        addPairWithGbc(Label4, textField4, leftPanel, gbc, 3);
        addPairWithGbc(Label5, textField5, leftPanel, gbc, 4);
        addPairWithGbc(Label6, textField6, leftPanel, gbc, 5);
        addPairWithGbc(Label7, textField7, leftPanel, gbc, 6);

        // 添加单选按钮
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioPanel.add(tableRadioButton);
        radioPanel.add(unionSelectSqlRadioButton);
        leftPanel.add(radioPanel, gbc);

        // 创建按钮组并添加两个单选按钮
        ButtonGroup modeButtonGroup = new ButtonGroup();
        tableRadioButton.setSelected(true);
        modeButtonGroup.add(tableRadioButton);
        modeButtonGroup.add(unionSelectSqlRadioButton);

        // 添加Label9
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Label9 = new JLabel("Result Output");
        leftPanel.add(Label9, gbc);

        // 添加结果文本区域
        gbc.gridy = 9;
        resultTextArea = new JTextArea(5, 25);
        resultTextArea.setEditable(false);
        JScrollPane resultScrollPane = new JScrollPane(resultTextArea);
        leftPanel.add(resultScrollPane, gbc);

        // 添加按钮
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(connectDatabaseTestButton);
        buttonPanel.add(executeButton);
        leftPanel.add(buttonPanel, gbc);

        // 将左右面板添加到主面板（确保顶部对齐）
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(leftPanel, BorderLayout.CENTER);
        contentPanel.add(rightPanel, BorderLayout.EAST);
        JPanel1.add(contentPanel, BorderLayout.NORTH); // 使用NORTH确保顶部对齐

        // 添加按钮事件监听
        connectDatabaseTestButton.addActionListener(this::handleConnectDB);
        executeButton.addActionListener(this::handleGenerateCode);
    }

    private void setFixedLabelWidth(JLabel label, int width) {
        label.setPreferredSize(new Dimension(width, label.getPreferredSize().height));
    }

    private void addPairWithGbc(JLabel label, JTextField field, JPanel panel, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        panel.add(field, gbc);
    }


    // 处理代码生成
    private void handleGenerateCode(ActionEvent e) {
        // 获取所有输入框内容
        String url = textField1.getText().trim();
        String username = textField2.getText().trim();
        String password = textField3.getText().trim();
        String daoPath = textField4.getText().trim();
        String entityPath = textField5.getText().trim();
        String packagePath = textField6.getText().trim();
        String tableName = textField7.getText().trim();
        String fileName = textField8.getText().trim();
        String sql = textPane1.getText().trim();

//        url="jdbc:mysql://127.0.0.1:3306/generatecode?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
//        username="root";
//        password="root";
//        daoPath="E:\\generateCode";
//        entityPath="E:\\generateCode";
//        tableName="user_consumption";

        // 验证输入
        if (url.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(JPanel1, "please fill completed database link information", "error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 根据选择的模式验证
        if (tableRadioButton.isSelected() && tableName.isEmpty()) {
            JOptionPane.showMessageDialog(JPanel1, "must fill table name under form pattern", "error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (unionSelectSqlRadioButton.isSelected() && sql.isEmpty()) {
            JOptionPane.showMessageDialog(JPanel1, "must fill sql statement under sql pattern", "error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 显示获取到的值
        resultTextArea.append("start generate code...\n");
        resultTextArea.append("database URL: " + url + "\n");
        resultTextArea.append("username: " + username + "\n");
        resultTextArea.append("DAO path: " + daoPath + "\n");
        resultTextArea.append("Entity path: " + entityPath + "\n");
        resultTextArea.append("package path: " + packagePath + "\n");

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            if (tableRadioButton.isSelected()) {
                // 表模式
                resultTextArea.append("检查表是否存在: " + tableName + "\n");

                // 检查表是否存在
                DatabaseMetaData metaData = conn.getMetaData();
                ResultSet tables = metaData.getTables(null, null, tableName, new String[]{"TABLE"});

                if (!tables.next()) {
                    resultTextArea.append("错误: 表 " + tableName + " 在数据库中不存在\n");
                    return;
                }

                resultTextArea.append("表存在。获取列信息...\n");

                // 获取列信息
                ResultSet columns = metaData.getColumns(null, null, tableName, null);
                ArrayList<ColumnInfo> columnList = new ArrayList<>();

                while (columns.next()) {
                    ColumnInfo column = new ColumnInfo();
                    HashMap<String, Object> columnMap = new HashMap<>();
                    column.setName(columns.getString("COLUMN_NAME"));
                    String sqlType = columns.getString("TYPE_NAME");
                    column.setType(SqlTypeToJavaTypeUtils.convertSqlTypeToJavaType(sqlType));
                    column.setSize(columns.getInt("COLUMN_SIZE"));
                    column.setNullable(columns.getInt("NULLABLE") == DatabaseMetaData.columnNullable);
                    column.setRemarks(columns.getString("REMARKS"));
                    column.setPlaceHolderName(columns.getString("COLUMN_NAME"));
                    columnList.add(column);
                }

                resultTextArea.append("在表 " + tableName + " 中找到 " + columnList.size() + " 个列\n");

                // 准备模板数据模型
                Map<String, Object> dataModel = new HashMap<>();
                dataModel.put("className", convertToClassName(tableName));
                dataModel.put("table", tableName);
                dataModel.put("mapperName", convertToClassName(tableName)+"Mapper");

                dataModel.put("entity", convertToClassName(tableName));
                dataModel.put("entityLower", convertToClassName(tableName).toLowerCase());
                dataModel.put("columns", columnList);
                dataModel.put("id", "#{id}");




                // 使用模板生成文件
                generateFromTemplate("Entity.ftl", entityPath, dataModel);
                generateFromTemplate("Mapper.ftl", daoPath, dataModel);
                generateFromTemplate("xmlMapper.ftl", daoPath, dataModel);

                resultTextArea.append("代码生成成功完成!\n");

            } else {
                // 表模式
                resultTextArea.append("检查sql是否合法: " + sql + "\n");

                //检查sql是否合法
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                // 获取结果集的元数据
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                ArrayList<ColumnInfo> columnList = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    ColumnInfo column = new ColumnInfo();
                    column.setName(rsmd.getColumnName(i));
                    column.setType(SqlTypeToJavaTypeUtils.convertSqlTypeToJavaType(rsmd.getColumnTypeName(i)));
                    column.setSize(rsmd.getColumnDisplaySize(i));
                    column.setNullable(rsmd.isNullable(i) == DatabaseMetaData.columnNullable);
                    column.setPlaceHolderName(rsmd.getColumnName(i));
                    columnList.add(column);
                }

                resultTextArea.append("在sql中找到 " + columnList.size() + " 个列\n");

                // 准备模板数据模型
                Map<String, Object> dataModel = new HashMap<>();
                dataModel.put("className", convertToClassName(fileName));
                dataModel.put("table", fileName);
                dataModel.put("mapperName", fileName+"Mapper");

                dataModel.put("entity", fileName);
                dataModel.put("entityLower", convertToClassName(fileName).toLowerCase());
                dataModel.put("columns", columnList);
                dataModel.put("id", "#{id}");
                dataModel.put("unionSql", sql);


                // 使用模板生成文件
                generateFromTemplate("Entity.ftl", entityPath, dataModel);
                generateFromTemplate("MapperUnionSelect.ftl", daoPath, dataModel);
                generateFromTemplate("xmlUnionSqlMapper.ftl", daoPath, dataModel);

                resultTextArea.append("代码生成成功完成!\n");
            }
        } catch (SQLException ex) {
            resultTextArea.append("数据库错误: " + ex.getMessage() + "\n");
        } catch (Exception ex) {
            resultTextArea.append("生成代码时出错: " + ex.getMessage() + "\n");
        }

        resultTextArea.append("generate code finish!\n");
    }

    // 辅助方法：将表名转换为类名(下划线转驼峰)
    private String convertToClassName(String tableName) {
        String[] parts = tableName.split("_");
        StringBuilder className = new StringBuilder();
        for (String part : parts) {
            if (!part.isEmpty()) {
                className.append(part.substring(0, 1).toUpperCase())
                        .append(part.substring(1).toLowerCase());
            }
        }
        return className.toString();
    }

    // 辅助方法：使用FreeMarker模板生成文件
    private void generateFromTemplate(String templateName, String outputPath, Map<String, Object> dataModel)
            throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setClassForTemplateLoading(this.getClass(), "/templates");
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate(templateName);

        // 创建输出目录(如果不存在)
        File outputDir = new File(outputPath);
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        // 生成文件名(例如: UserEntity.java)
        String className = (String) dataModel.get("className");
        String suffix= "";
        switch (templateName){
            case "Entity.ftl":
                suffix = "Entity.java";
                break;
            case "Mapper.ftl":
                suffix = "Mapper.java";
                break;
            case "MapperUnionSelect.ftl":
                suffix = "Mapper.java";
                break;
            case "xmlMapper.ftl":
                suffix = "Mapper.xml";
                break;
            case "xmlUnionSqlMapper.ftl":
                suffix = "Mapper.xml";
                break;
            default:

        }

        String fileName = className + suffix;

        try (Writer out = new FileWriter(new File(outputDir, fileName))) {
            template.process(dataModel, out);
            resultTextArea.append("生成文件: " + fileName + "\n");
        }


    }


    // 处理数据库连接测试
    private void handleConnectDB(ActionEvent e) {
        // 获取输入框内容
        String url = textField1.getText().trim();
        String username = textField2.getText().trim();
        String password = textField3.getText().trim();

        // 验证输入
        if (url.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(JPanel1, "请填写完整的数据库连接信息", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 测试数据库连接
        resultTextArea.append("正在测试数据库连接...\n");
        try {
            // 加载 MySQL JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 建立数据库连接
            Connection conn = DriverManager.getConnection(url, username, password);

            if (conn != null && !conn.isClosed()) {
                resultTextArea.append("数据库连接成功！\n");
                conn.close(); // 关闭连接
            }
        } catch (ClassNotFoundException ex) {
            resultTextArea.append("找不到JDBC驱动: " + ex.getMessage() + "\n");
        } catch (SQLException ex) {
            resultTextArea.append("数据库连接失败: " + ex.getMessage() + "\n");
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("autoGenerateCode");
        frame.setContentPane(new autoGenerateCode().JPanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
