import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/**
 * To present a panel that shows calendar to let user view reservation by day
 * @author Chanip Chong
 * @version 1.0 12/8/2018
 */
public class ViewByDayPanel extends JPanel
{
    JPanel top;
    JPanel center;
    LocalDate D;
    JTextArea textArea;
    JButton nextMonth;
    JButton previousMonth;
    LocalDate P;                                //previous month
    LocalDate end;                          // the end of month(date)
    int endday;                             // the end of month(day)
    LocalDate Pend;                         // the end of next month(date)
    int Pendday;                                // the end of next month(day)
    int first;                              // the first day of week in number.
    DateReservationsView view;

    /**
     * a construtor of ViewByDayPanel that initialize the panel
     */
    public ViewByDayPanel()
    {
        top = new JPanel();
        center = new JPanel();
        D = LocalDate.now();
        constructPanel();
        displayMonth();
        this.add(top, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        view = new DateReservationsView();
    }

    /**
     *  to print out the calendar for the month
     */
    public void displayMonth()
    {
        int j = 0;
        for (;j<first%7;j++)
        {
            String label=Integer.toString(Pendday-first+1);
            JTextArea area = new JTextArea("\n   "+label);
            area.setEditable(false);
            center.add(area);
            Pendday++;
        }
        for (int i=1;i<endday+1;i++)
        {
            String label = Integer.toString(i);
            JButton keyButton = new JButton(label);
            int finalI = i;
            keyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    SystemDisplay topFrame = (SystemDisplay) SwingUtilities.getWindowAncestor(getPanel());
                    int dayOfMonth= finalI;
                    int year = D.getYear();
                    int month=D.getMonth().getValue();
                    LocalDate d = LocalDate.of(year,month,dayOfMonth);
                    topFrame.PrintReservation(d, view);
                }
            });
            center.add(keyButton);
        }
        for (int k=0;k<42-endday-j;k++)
        {
            String label = Integer.toString(k+1);
            JTextArea area = new JTextArea("\n   "+label);
            area.setEditable(false);
            center.add(area);
        }
    }

    /**
     *  to organize the panel
     */
    public void constructPanel()
    {
        top = new JPanel();
        center = new JPanel();
        nextMonth = new JButton(">");
        nextMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                D = D.plusMonths(1);
                removeAll();
                constructPanel();
                displayMonth();
                revalidate();
            }
        });
        previousMonth = new JButton("<");
        previousMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                D = D.minusMonths(1);
                removeAll();
                constructPanel();
                displayMonth();
                revalidate();
            }
        });
        P = D.minusMonths(1);
        String Month = D.getMonth().toString();
        String year=Integer.toString(D.getYear());
        textArea = new JTextArea(year+"  "+Month);
        textArea.setEditable(false);
        top.setBackground(Color.white);
        center.setBackground(Color.white);
        first = D.getDayOfWeek().getValue();
        end = D.withDayOfMonth(D.lengthOfMonth());
        endday = end.getDayOfMonth();
        Pend = P.withDayOfMonth(P.lengthOfMonth());
        Pendday = Pend.getDayOfMonth();
        setLayout(new BorderLayout());
        top.add(textArea);
        top.add(previousMonth);
        top.add(nextMonth);
        center.setLayout(new GridLayout(7, 7));
        center.add(new JTextArea("\n    S"));
        center.add(new JTextArea("\n    M"));
        center.add(new JTextArea("\n    T"));
        center.add(new JTextArea("\n    W"));
        center.add(new JTextArea("\n    T"));
        center.add(new JTextArea("\n    F"));
        center.add(new JTextArea("\n    S"));
        this.add(top, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
    }

    /** a function that return the ViewByDayPanel
     * @return ViewByDayPanel
     */
    private ViewByDayPanel getPanel(){
        return this;
    }
}