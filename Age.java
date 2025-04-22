import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AgeCalculator extends JFrame {

    private JLabel birthDateLabel;
    private JTextField birthDateTextField;
    private JLabel baseDateLabel;
    private JTextField baseDateTextField;
    private JButton calculateButton;
    private JLabel ageLabel;


// 2025/04/25 UPDATE
    public AgeCalculator() {
        super("年齢計算");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new GridLayout(5, 2));

        birthDateLabel = new JLabel("生年月日 (yyyy/MM/dd):");
        add(birthDateLabel);

        birthDateTextField = new JTextField();
        add(birthDateTextField);

        baseDateLabel = new JLabel("基準日 (yyyy/MM/dd):");
        add(baseDateLabel);

        baseDateTextField = new JTextField();
        add(baseDateTextField);

        calculateButton = new JButton("計算");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAge();
            }
        });
        add(calculateButton);

        ageLabel = new JLabel("");
        add(ageLabel);

        setVisible(true);
    }

    private void calculateAge() {
        String birthDateString = birthDateTextField.getText();
        String baseDateString = baseDateTextField.getText();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        try {
            Date birthDate = dateFormat.parse(birthDateString);
            Date baseDate = dateFormat.parse(baseDateString);

            Calendar birthCalendar = new GregorianCalendar();
            birthCalendar.setTime(birthDate);

            Calendar baseCalendar = new GregorianCalendar();
            baseCalendar.setTime(baseDate);

            int age = baseCalendar.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);

            // 基準日の月日が生年月日の月日より小さい場合は、年齢を1歳減らす
            if (baseCalendar.get(Calendar.MONTH) < birthCalendar.get(Calendar.MONTH) ||
                    (baseCalendar.get(Calendar.MONTH) == birthCalendar.get(Calendar.MONTH) &&
                            baseCalendar.get(Calendar.DAY_OF_MONTH) < birthCalendar.get(Calendar.DAY_OF_MONTH))) {
                age--;
            }

            ageLabel.setText("年齢: " + age + "歳");

        } catch (ParseException ex) {
            ageLabel.setText("日付の形式が不正です。");
        }
    }

    public static void main(String[] args) {
        new AgeCalculator();
    }
}