//for graphical user interface
import javax.imageio.ImageIO;
import javax.swing.*;

//for calender
//import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.Locale;
import java.util.Date;

import java.awt.event.*;
import java.io.File;
import java.awt.*;

//import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener;
//import java.io.File;
import java.io.IOException;

//mysql library
import java.sql.*;

//regex for pwd validation
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) throws Exception {
        final String dbUrl = "jdbc:mysql://localhost/movietcktbooking?serverTimezone=UTC";
        final String user = "root";
        final String pwdd = "sanju1304";
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame f=new JFrame("Movie Ticket Booking System");//creating instance of JFrame

        JLabel loginbackground = new JLabel(new ImageIcon("loginBgImg.jpeg"));
        loginbackground.setBounds(0, 0, 100, 1000);

        JLabel heading,userid,pwd,noAcc;
        heading=new JLabel("Login");
        heading.setBounds(330,30, 100,30);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Poppins", Font.BOLD, 20));

        userid=new JLabel("Email: ");  
        userid.setBounds(240,100, 100,30);
        userid.setFont(new Font("Poppins", Font.BOLD, 16));

        JTextField useridEntry;  
        useridEntry=new JTextField();  
        useridEntry.setBounds(320,100, 170,30);
        useridEntry.setFont(new Font("Poppins", Font.BOLD, 16));

        pwd=new JLabel("Password: ");  
        pwd.setBounds(230,150, 100,30);
        pwd.setFont(new Font("Poppins", Font.BOLD, 16));

        JPasswordField pwdentry = new JPasswordField();
        pwdentry.setBounds(320,150,170,30);  
        pwdentry.setFont(new Font("Poppins", Font.BOLD, 16));
        
        JButton loginBtn=new JButton("Login");//creating instance of JButton  
        loginBtn.setBounds(300,200,100, 40);   
        loginBtn.setBackground(Color.BLACK);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setOpaque(true);
        loginBtn.setBorderPainted(false);
        

        noAcc=new JLabel("Don't have an account? ");  
        noAcc.setBounds(330,250, 200,30);  
        noAcc.setForeground(Color.WHITE);

        JButton regBtn=new JButton("Register");//creating instance of JButton  
        regBtn.setBounds(325,280,100, 35);  
        regBtn.setBackground(Color.BLACK);
        regBtn.setForeground(Color.WHITE);
        regBtn.setOpaque(true);
        regBtn.setBorderPainted(false); 

        
        loginBtn.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
                String loginmailVal = useridEntry.getText();
                String loginpwdVal = new String(pwdentry.getPassword());

                Connection loginconn = null;
                Statement stmt = null;

                try {
                    loginconn = DriverManager.getConnection(dbUrl,user,pwdd);
                    stmt = loginconn.createStatement();

                    String loginQuery = "select sno,email,name,mobno,usertype,password from authentication where email='" + loginmailVal + "' ";
                    ResultSet loginDeets=stmt.executeQuery(loginQuery);

                    while (loginDeets.next()) {
                        if (loginDeets.getString("email").equals(loginmailVal) && loginDeets.getString("password").equals(loginpwdVal)) {
                            //JOptionPane.showMessageDialog(f, "LOGIN SUCCESSFULL", "Message", JOptionPane.INFORMATION_MESSAGE);
                            f.dispose();

                            
                            String nameVal = loginDeets.getString("name");
                            String userTypeval = loginDeets.getString("usertype");

                            if (userTypeval.equals("User")) {
                                JFrame hp1 = new JFrame("Login Successfull !");

                                BufferedImage img = null;
                                try {
                                    img = ImageIO.read(new File("cityBgImg.jpeg"));
                                } catch (IOException en) {
                                    en.printStackTrace();
                                }
                                Image dimg = img.getScaledInstance(700, 250, Image.SCALE_SMOOTH);
                                ImageIcon imageIcon = new ImageIcon(dimg);
                                hp1.setContentPane(new JLabel(imageIcon));

                                JLabel head = new JLabel("Welcome, "+nameVal);
                                head.setBounds(10, 10, 200, 50);
                                head.setFont(new Font("Poppins", Font.BOLD, 20));

                                JLabel subhead = new JLabel("");
                                subhead.setBounds(10, 50, 400, 50);
                                subhead.setFont(new Font("Poppins", Font.BOLD, 16));
                                subhead.setText("Choose City and Language for browsing Movies");
                            
                                JLabel cityLbl = new JLabel("City: ");
                                cityLbl.setBounds(30, 90, 50, 50);
                                cityLbl.setFont(new Font("Poppins", Font.BOLD, 16));

                                String cities[]={"Chennai","Bangalore","Hyderabad","Mumbai"};        
                                final JComboBox cityEntry = new JComboBox(cities);
                                cityEntry.setBounds(100,100, 200,30);

                                JLabel langLbl = new JLabel("Language: ");
                                langLbl.setBounds(350, 90, 100, 50);
                                langLbl.setFont(new Font("Poppins", Font.BOLD, 16));

                                String langs[]={"Tamil","Malayalam","Telugu","Hindi"};
                                final JComboBox langEntry = new JComboBox(langs);
                                langEntry.setBounds(470,100, 200,30);

                                JButton searchBtn = new JButton("SEARCH");
                                searchBtn.setBounds(270, 170, 150, 40);
                                searchBtn.setBackground(Color.decode("#0097A7"));
                                searchBtn.setFont(new Font("Poppins", Font.BOLD, 16));
                                searchBtn.setForeground(Color.WHITE);
                                searchBtn.setOpaque(true);
                                searchBtn.setBorderPainted(false);

                                searchBtn.addActionListener(new ActionListener() {  
                                    public void actionPerformed(ActionEvent e) {
                                        hp1.dispose();
                                        String cityVal =  ""+cityEntry.getItemAt(cityEntry.getSelectedIndex());
                                        String langVal = ""+langEntry.getItemAt(langEntry.getSelectedIndex());
                                        
                                                JFrame hp2 = new JFrame("MOVIE TICKET BOOKING APPLICATION");

                                                JLabel head = new JLabel("BOOK YOUR TICKETS NOW !");
                                                head.setBounds(550, 5, 350, 50);
                                                head.setFont(new Font("Poppins", Font.BOLD, 22));
                                                head.setForeground(Color.WHITE);

                                                JButton logout = new JButton("Log Out");
                                                logout.setBounds(1220, 5, 130, 35);
                                                logout.setFont(new Font("Poppins", Font.BOLD, 18));
                                                logout.setForeground(Color.WHITE);
                                                logout.setBackground(Color.BLACK);
                                                logout.setOpaque(true);
                                                logout.setBorderPainted(false);
                                                logout.addActionListener(new ActionListener() {  
                                                    public void actionPerformed(ActionEvent e) {
                                                        hp2.dispose();
                                                        JOptionPane.showMessageDialog(hp2, "LOGOUT SUCCESSFULL", "Logout", JOptionPane.INFORMATION_MESSAGE);
                                                    }
                                                });

                                                JButton bukHstryBtn = new JButton("Booking History");
                                                bukHstryBtn.setBounds(1000, 5, 200, 35);
                                                bukHstryBtn.setFont(new Font("Poppins", Font.BOLD, 15));
                                                bukHstryBtn.setForeground(Color.WHITE);
                                                bukHstryBtn.setBackground(Color.BLACK);
                                                bukHstryBtn.setOpaque(true);
                                                bukHstryBtn.setBorderPainted(false);
                                                hp2.add(bukHstryBtn);
                                                bukHstryBtn.addActionListener(new ActionListener() {  
                                                    public void actionPerformed(ActionEvent e) {

                                                        JFrame hisFrame = new JFrame("BOOKING HISTORY");

                                                        Connection historycon = null;
                                                        Statement stmt = null;
                                                        String bookingDetails[] = new String[10];
                                                        hisFrame.getContentPane().setBackground(Color.decode("#5A1D02"));
                                                        try {
                                                            historycon = DriverManager.getConnection(dbUrl,user,pwdd);
                                                            stmt = historycon.createStatement();

                                                            String historyQuery = "select bid, movie,amountpaid,date,theatre from bookinghistory where cus_email='abc' "; //" + loginmailVal + "
                                                            ResultSet historyDeets=stmt.executeQuery(historyQuery);

                                                            
                                                            int bukPos = 0;
                                                            while (historyDeets.next()) {
                                                                bookingDetails[bukPos] = historyDeets.getString("date")+": ,Booking id: "+historyDeets.getString("bid")+",Movie: "+historyDeets.getString("movie")+",Amount Paid: "+historyDeets.getString("amountpaid")+",Theatre: "+historyDeets.getString("theatre");
                                                                bukPos++;
                                                            }
                                                            int deetYaxixs = -40;
                                                            if (bookingDetails[0]!=null) {
                                                                
                                                            
                                                            for (int i = 0; i < bookingDetails.length; i++) {
                                                                String eachDetail[] = bookingDetails[i].split(",");

                                                                JLabel eachDetailLbl = new JLabel();
                                                                String detailResult = "";
                                                                for (int j = 0; j < eachDetail.length; j++) {
                                                                    detailResult = detailResult + eachDetail[j] + "\n";
                                                                }
                                                                eachDetailLbl.setText(detailResult);
                                                                eachDetailLbl.setBounds(5, 5+deetYaxixs, 750, 200);
                                                                eachDetailLbl.setForeground(Color.WHITE);
                                                                eachDetailLbl.setFont(new Font("Poppins", Font.BOLD, 16));

                                                                hisFrame.add(eachDetailLbl);
                                                                deetYaxixs+=40;
                                                            }
                                                            }

                                                        } catch (Exception b) {
                                                            System.out.println(b);
                                                        }
                                                        
                                                        hisFrame.setSize(800,350); 
                                                        hisFrame.setLayout(null);
                                                        hisFrame.setVisible(true);
                                                        hisFrame.setLocationRelativeTo(null);
                                                    }
                                                });

                                                hp2.getContentPane().setBackground(Color.decode("#5A1D02"));

                                                Container c = hp2.getContentPane();

                                                //setting a hero image
                                                BufferedImage heroimg = null;
                                                try {
                                                    heroimg = ImageIO.read(new File("heroImg_homepage.jpg"));
                                                } catch (IOException en) {
                                                    en.printStackTrace();
                                                }
                                                JLabel heroImg = new JLabel();
                                                Image dimg = heroimg.getScaledInstance(1350, 250, Image.SCALE_SMOOTH);
                                                ImageIcon imageIcon = new ImageIcon(dimg);
                                                heroImg.setIcon(imageIcon);
                                                heroImg.setBounds(30, 55, 1350, 250);

                                                c.add(heroImg);

                                                Connection avlMvsconn = null;
                                                Statement stmt = null;

                                                String avlMoviescount = "";
                                                
                                                try {
                                                    avlMvsconn = DriverManager.getConnection(dbUrl,user,pwdd);
                                                    stmt = avlMvsconn.createStatement();
                                                    String avlMvsQuery = "select count(*) from movies where mlang='"+langVal+"' and mcity = '"+cityVal+"'";
                                                    ResultSet avlMvsDeets=stmt.executeQuery(avlMvsQuery);

                                                    while (avlMvsDeets.next()) {
                                                        avlMoviescount = avlMvsDeets.getString("count(*)");
                                                    }

                                                    String movienamesQuery = "select mname,mcast from movies where mlang='"+langVal+"' and mcity = '"+cityVal+"'";
                                                    ResultSet movienameDeets = stmt.executeQuery(movienamesQuery);

                                                    int movieCnt = Integer.parseInt(avlMoviescount);

                                                    String movieNames[] = new String[movieCnt];
                                                    String movieCasts[] = new String[movieCnt];
                                                    int i1=0;
                                                    while (movienameDeets.next()) {
                                                        movieNames[i1] = movienameDeets.getString("mname");
                                                        movieCasts[i1] = movienameDeets.getString("mcast");
                                                        i1++;
                                                    }

                                                    int xaxis=0;
                                                    for (int k = 0; k < movieCnt; k++) {

                                                        //adding movie poster
                                                        Container c1 = hp2.getContentPane();

                                                        BufferedImage movieposter = null;
                                                        try {
                                                            String filename = "moviepics/"+movieNames[k].replaceAll("\\s", "") + ".jpg";
                                                            movieposter = ImageIO.read(new File(filename)); //moviepics/12thFail.jpg
                                                        } catch (IOException en) {
                                                            en.printStackTrace();
                                                        }

                                                        JLabel moviePoster = new JLabel();
                                                        Image dimg1 = movieposter.getScaledInstance(300, 200, Image.SCALE_SMOOTH);
                                                        ImageIcon imageIcon1 = new ImageIcon(dimg1);
                                                        moviePoster.setIcon(imageIcon1);
                                                        moviePoster.setBounds(30+xaxis, 370, 300, 200);
                                                        c1.add(moviePoster);

                                                        JLabel movNameLbl = new JLabel("");
                                                        movNameLbl.setText(movieNames[k]);
                                                        movNameLbl.setForeground(Color.WHITE);
                                                        movNameLbl.setBounds(30+xaxis,580,300,40);
                                                        movNameLbl.setFont(new Font("Poppins", Font.BOLD, 18));
                                                        hp2.add(movNameLbl);

                                                        JLabel movCastLbl = new JLabel("");
                                                        movCastLbl.setForeground(Color.WHITE);
                                                        String castText = "Cast:\n"+movieCasts[k];
                                                        movCastLbl.setText(castText);
                                                        movCastLbl.setBounds(30+xaxis,610,300,30);
                                                        movCastLbl.setFont(new Font("Poppins", Font.BOLD, 15));
                                                        hp2.add(movCastLbl);

                                                        JButton bookNow = new JButton("BOOK NOW");
                                                        bookNow.setBounds(30+xaxis,650,280,50);
                                                        bookNow.setFont(new Font("Poppins", Font.BOLD, 16));
                                                        bookNow.setForeground(Color.WHITE);
                                                        bookNow.setBackground(Color.decode("#872D04"));
                                                        bookNow.setOpaque(true);
                                                        bookNow.setBorderPainted(false);
                                                        hp2.add(bookNow);

                                                        xaxis+=330;

                                                        String currMovie = movieNames[k];
                                                        bookNow.addActionListener(new ActionListener() {  
                                                            public void actionPerformed(ActionEvent e) {
                                                                hp2.dispose();
                                                                JFrame tht = new JFrame("MOVIE TICKET BOOKING APPLICATION");

                                                                JLabel head = new JLabel("AVAILABLE THEATRES AND SHOW TIMES");
                                                                head.setBounds(450, 5, 470, 50);
                                                                head.setFont(new Font("Poppins", Font.BOLD, 22));
                                                                head.setForeground(Color.WHITE);

                                                                JLabel subhead = new JLabel("");
                                                                subhead.setText("Movie: "+currMovie);
                                                                subhead.setBounds(50, 55, 350, 50);
                                                                subhead.setFont(new Font("Poppins", Font.BOLD, 30));
                                                                subhead.setForeground(Color.WHITE);

                                                                JButton logout = new JButton("Log Out");
                                                                logout.setBounds(1220, 5, 130, 35);
                                                                logout.setFont(new Font("Poppins", Font.BOLD, 18));
                                                                logout.setForeground(Color.WHITE);
                                                                logout.setBackground(Color.BLACK);
                                                                logout.setOpaque(true);
                                                                logout.setBorderPainted(false);
                                                                logout.addActionListener(new ActionListener() {  
                                                                    public void actionPerformed(ActionEvent e) {
                                                                        tht.dispose();
                                                                        JOptionPane.showMessageDialog(tht, "LOGOUT SUCCESSFULL", "Logout", JOptionPane.INFORMATION_MESSAGE);

                                                                    }
                                                                });

                                                                
                                                                tht.getContentPane().setBackground(Color.decode("#5A1D02"));

                                                                
                                                                JDateChooser chooser = new JDateChooser();
                                                                chooser.setLocale(Locale.US);

                                                                JLabel dtlabel = new JLabel("Select date of Booking:");
                                                                dtlabel.setLayout(new BorderLayout());
                                                                dtlabel.add(chooser, BorderLayout.EAST);
                                                                dtlabel.setBounds(40,100,300,40);
                    

                                                                tht.add(dtlabel);
                                                                dtlabel.setForeground(Color.WHITE);


                                                                tht.add(head);tht.add(subhead);tht.add(logout);

                                                                String avail_threatre_count = "";
                                                                int avail_threatreCount = 0;
                                                                try {
                                                                    Connection thtConn = DriverManager.getConnection(dbUrl,user,pwdd);
                                                                    Statement stmt = null;
                                                                    stmt = thtConn.createStatement();
                                                                    
                                                                    
                                                                    String noOftheatres = "select count(*) from theatres where tcity = '"+cityVal+"'";
                                                                    ResultSet noOftheatreDeets = stmt.executeQuery(noOftheatres);

                                                                    while (noOftheatreDeets.next()) {
                                                                        avail_threatre_count = noOftheatreDeets.getString("count(*)");
                                                                    }

                                                                    String theatreQuery = "select tname,tcity,avail_movies,all_shows,avl_shows from theatres where tcity = '"+cityVal+"'" ;
                                                                    ResultSet theatreDeets = stmt.executeQuery(theatreQuery);

                                                                    avail_threatreCount = Integer.parseInt(avail_threatre_count);

                                                                    String theatreNames[] = new String[avail_threatreCount];
                                                                    String avl_shows[] = new String[avail_threatreCount];
                                                                    String avl_movies[] = new String[avail_threatreCount];

                                                                    int pos=0;
                                                                    while (theatreDeets.next()) {
                                                                        theatreNames[pos] = theatreDeets.getString("tname");
                                                                        avl_shows[pos] = theatreDeets.getString("avl_shows");
                                                                        avl_movies[pos] = theatreDeets.getString("avail_movies");
                                                                        pos++;
                                                                    }
                                                                    
                                                                    int avlThtForMovie = 0;
                                                                    int thtIndexes[] =  new int[avail_threatreCount];
                                                                    int index=0;
                                                                    for (int i = 0; i < avail_threatreCount; i++) {
                                                                        if (avl_movies[i].contains(currMovie)) {
                                                                            avlThtForMovie++;
                                                                            thtIndexes[index] = i;
                                                                            index++;
                                                                        }
                                                                    }
                                                                    String updtheatreNames[] = new String[avlThtForMovie];
                                                                    String updavl_shows[] = new String[avlThtForMovie];
                                                                    

                                                                    for (int i = 0; i < avlThtForMovie; i++) {
                                                                        updtheatreNames[i] = theatreNames[thtIndexes[i]];
                                                                        updavl_shows[i] = avl_shows[thtIndexes[i]];
                                                                    }

                                                                    int yaxis_tname =0;
                                                                    for (int i = 0; i < avlThtForMovie; i++) {
                                                                        JLabel tname = new JLabel();
                                                                        tname.setText(updtheatreNames[i]);
                                                                        tname.setBounds(50, 190+yaxis_tname, 300, 40);
                                                                        tname.setForeground(Color.WHITE);
                                                                        tname.setFont(new Font("Poppins", Font.BOLD, 20));
                                                                        tht.add(tname);

                                                                        String each_avl_show[] = updavl_shows[i].split(",");
                                                                        int ShowBtnxAxis = 0;
                                                                        
                                                                        for (int j = 0; j < each_avl_show.length; j++) {
                                                                            JButton showTimeBtn = new JButton();
                                                                            showTimeBtn.setText(each_avl_show[j]);
                                                                            showTimeBtn.setBounds(350+ShowBtnxAxis, 190+yaxis_tname, 150, 50);
                                                                            ShowBtnxAxis+=160;
                                                                            tht.add(showTimeBtn);

                                                                            String chosenShowTime = each_avl_show[j];
                                                                            
                                                                            String chosenTheatre = updtheatreNames[i];
                                                                            showTimeBtn.addActionListener(new ActionListener() {  
                                                                                public void actionPerformed(ActionEvent e) {
                                                                                    //getting the chosen show time
                                                                                    String finalChosenShowTime = chosenShowTime;
                                                                                    //String lll = formattedDate;

                                                                                    //getting the chosen date
                                                                                    Date selectedDate = chooser.getDate();
                                                                                    String formattedDate = "";
                                                                                    if (selectedDate != null) {
                                                                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                                                                        formattedDate = sdf.format(selectedDate);

                                                                                        // Perform actions with the selected date
                                                                                        
                                                                                    }
                                                                                    //getting the theatre name
                                                                                    String finalChosenTheatre = chosenTheatre;

                                                                                    JFrame seatsPageFrame = new JFrame("MOVIE TICKT BOOKING APPLICATION");

                                                                                    seatsPageFrame.getContentPane().setBackground(Color.decode("#5A1D02"));

                                                                                    JLabel head = new JLabel("Please fill in the below details: ");
                                                                                    head.setBounds(5, 5, 350, 40);
                                                                                    head.setFont(new Font("Poppins", Font.BOLD, 19));
                                                                                    head.setForeground(Color.WHITE);

                                                                                    JLabel seatsLbl = new JLabel("Enter the number of seats: ");
                                                                                    seatsLbl.setBounds(15, 45, 300, 40);
                                                                                    seatsLbl.setFont(new Font("Poppins", Font.BOLD, 16));
                                                                                    seatsLbl.setForeground(Color.WHITE);

                                                                                    JTextField seatsEntry = new JTextField();
                                                                                    seatsEntry.setBounds(280,45,100,40);
                                                                                    seatsEntry.setFont(new Font("Poppins", Font.BOLD, 16));
                                                                                    

                                                                                    JLabel seatTypeLbl = new JLabel("Select Seat Type: ");
                                                                                    seatTypeLbl.setBounds(15, 100, 300, 40);
                                                                                    seatTypeLbl.setFont(new Font("Poppins", Font.BOLD, 16));
                                                                                    seatTypeLbl.setForeground(Color.WHITE);

                                                                                    JRadioButton silverBtn = new JRadioButton("SILVER (Rs. 100)");
                                                                                    JRadioButton goldBtn = new JRadioButton("GOLD (Rs. 160)");
                                                                                    JRadioButton platinumBtn = new JRadioButton("PLATINUM (Rs. 220)");

                                                                                    silverBtn.setBounds(280, 100, 200, 40);
                                                                                    goldBtn.setBounds(280, 135, 200, 40);
                                                                                    platinumBtn.setBounds(280, 165, 200, 40);

                                                                                    silverBtn.setFont(new Font("Poppins", Font.BOLD, 16));
                                                                                    goldBtn.setFont(new Font("Poppins", Font.BOLD, 16));
                                                                                    platinumBtn.setFont(new Font("Poppins", Font.BOLD, 16));
                                                                                    silverBtn.setForeground(Color.WHITE);
                                                                                    goldBtn.setForeground(Color.WHITE);
                                                                                    platinumBtn.setForeground(Color.WHITE);

                                                                                    ButtonGroup bg=new ButtonGroup();
                                                                                    bg.add(silverBtn);bg.add(goldBtn);bg.add(platinumBtn);

                                                                                    JButton proceedBtn = new JButton("Proceed");
                                                                                    proceedBtn.setBounds(150, 250, 100, 50);
                                                                                    proceedBtn.setFont(new Font("Poppins", Font.BOLD, 18));

                                                                                    seatsPageFrame.add(proceedBtn);
                                                                                    seatsPageFrame.add(seatsLbl);seatsPageFrame.add(seatsEntry);seatsPageFrame.add(seatTypeLbl);seatsPageFrame.add(head);
                                                                                    seatsPageFrame.add(silverBtn);seatsPageFrame.add(goldBtn);seatsPageFrame.add(platinumBtn);

                                                                                    String finalDate = formattedDate;
                                                                                    
                                                                                    proceedBtn.addActionListener(new ActionListener() {  
                                                                                        public void actionPerformed(ActionEvent e) {
                                                                                            seatsPageFrame.dispose();
                                                                                            JFrame summaryFrame = new JFrame("BOOKING SUMMARY");

                                                                                            summaryFrame.getContentPane().setBackground(Color.decode("#5A1D02"));

                                                                                            JLabel head = new JLabel("Please review the details: ");
                                                                                            head.setBounds(5,5,300,40);
                                                                                            head.setForeground(Color.WHITE);
                                                                                            head.setFont(new Font("Poppins", Font.BOLD, 18));

                                                                                            JLabel timeDisp = new JLabel("Show Time: ");
                                                                                            timeDisp.setBounds(10,45,150,50);
                                                                                            timeDisp.setForeground(Color.WHITE);
                                                                                            timeDisp.setFont(new Font("Poppins", Font.BOLD, 16));

                                                                                            JLabel timeLbl = new JLabel();
                                                                                            timeLbl.setText(finalChosenShowTime);
                                                                                            timeLbl.setBounds(170,45,100,50);
                                                                                            timeLbl.setForeground(Color.WHITE);
                                                                                            timeLbl.setFont(new Font("Poppins", Font.BOLD, 16));

                                                                                            JLabel dateDisp = new JLabel("Date: ");
                                                                                            dateDisp.setBounds(10,105,100,50);
                                                                                            dateDisp.setForeground(Color.WHITE);
                                                                                            dateDisp.setFont(new Font("Poppins", Font.BOLD, 16));

                                                                                            JLabel dateLbl = new JLabel();
                                                                                            dateLbl.setText(finalDate);
                                                                                            dateLbl.setBounds(170,105,200,50);
                                                                                            dateLbl.setForeground(Color.WHITE);
                                                                                            dateLbl.setFont(new Font("Poppins", Font.BOLD, 16));


                                                                                            JLabel movieDisp = new JLabel("Movie name: ");                          
                                                                                            movieDisp.setBounds(10,165,200,50);
                                                                                            movieDisp.setForeground(Color.WHITE);
                                                                                            movieDisp.setFont(new Font("Poppins", Font.BOLD, 16));

                                                                                            JLabel movielbl = new JLabel();
                                                                                            movielbl.setText(currMovie);
                                                                                            movielbl.setBounds(170,165,200,50);
                                                                                            movielbl.setForeground(Color.WHITE);
                                                                                            movielbl.setFont(new Font("Poppins", Font.BOLD, 16));


                                                                                            JLabel seatsDisp = new JLabel("Seats: ");
                                                                                            seatsDisp.setBounds(10,225,200,50);
                                                                                            seatsDisp.setForeground(Color.WHITE);
                                                                                            seatsDisp.setFont(new Font("Poppins", Font.BOLD, 16));

                                                                                            JLabel seatsLbl = new JLabel();
                                                                                            seatsLbl.setText(seatsEntry.getText());
                                                                                            seatsLbl.setBounds(170,225,200,50);
                                                                                            seatsLbl.setForeground(Color.WHITE);
                                                                                            seatsLbl.setFont(new Font("Poppins", Font.BOLD, 16));

                                                                                            JLabel seatsTypeDisp = new JLabel("Seat Type: ");
                                                                                            seatsTypeDisp.setBounds(10,285,200,50);
                                                                                            seatsTypeDisp.setForeground(Color.WHITE);
                                                                                            seatsTypeDisp.setFont(new Font("Poppins", Font.BOLD, 16));

                                                                                            JLabel seatTypeLbl = new JLabel();
                                                                                            String chosenSeatType = "";
                                                                                            int price = 0;
                                                                                            if (silverBtn.isSelected()) {
                                                                                                chosenSeatType = "SILVER";
                                                                                                price = 100;
                                                                                            }
                                                                                            else if (goldBtn.isSelected()) {
                                                                                                chosenSeatType = "GOLD";
                                                                                                price = 160;
                                                                                            }
                                                                                            else{
                                                                                                chosenSeatType = "PLATINUM";
                                                                                                price = 220;
                                                                                            }
                                                                                            seatTypeLbl.setText(chosenSeatType);
                                                                                            seatTypeLbl.setBounds(170,285,200,50);
                                                                                            seatTypeLbl.setForeground(Color.WHITE);
                                                                                            seatTypeLbl.setFont(new Font("Poppins", Font.BOLD, 16));

                                                                                            JLabel theatreNameDisp = new JLabel("Theatre: ");
                                                                                            theatreNameDisp.setBounds(10,345,200,50);
                                                                                            theatreNameDisp.setForeground(Color.WHITE);
                                                                                            theatreNameDisp.setFont(new Font("Poppins", Font.BOLD, 16));

                                                                                            JLabel theatreNameLbl = new JLabel();
                                                                                            theatreNameLbl.setText(finalChosenTheatre);
                                                                                            theatreNameLbl.setBounds(170,345,200,50);
                                                                                            theatreNameLbl.setForeground(Color.WHITE);
                                                                                            theatreNameLbl.setFont(new Font("Poppins", Font.BOLD, 16));

                                                                                            JLabel amountLbl = new JLabel("Amount To Pay: ");
                                                                                            amountLbl.setBounds(10,405,300,50);
                                                                                            amountLbl.setForeground(Color.WHITE);
                                                                                            amountLbl.setFont(new Font("Poppins", Font.BOLD, 19));

                                                                                            int total_amount = Integer.parseInt(seatsEntry.getText())*price;

                                                                                            JLabel amountValLbl = new JLabel();
                                                                                            amountValLbl.setText(""+total_amount);
                                                                                            amountValLbl.setBounds(170,405,200,50);
                                                                                            amountValLbl.setForeground(Color.WHITE);
                                                                                            amountValLbl.setFont(new Font("Poppins", Font.BOLD, 19));

                                                                                            JLabel proceedLbl = new JLabel("If correct, proceed with the payment:");
                                                                                            proceedLbl.setBounds(10,465,200,50);
                                                                                            proceedLbl.setForeground(Color.WHITE);
                                                                                            proceedLbl.setFont(new Font("Poppins", Font.BOLD, 14));

                                                                                            JRadioButton r1=new JRadioButton("Paytm");    
                                                                                            JRadioButton r2=new JRadioButton("GooglePay");
                                                                                            JRadioButton r3=new JRadioButton("PhonePe");
                                                                                            JRadioButton r4=new JRadioButton("Debit Card");
                                                                                            JRadioButton r5=new JRadioButton("Credit Card");

                                                                                            r1.setForeground(Color.WHITE);
                                                                                            r2.setForeground(Color.WHITE);
                                                                                            r3.setForeground(Color.WHITE);
                                                                                            r4.setForeground(Color.WHITE);
                                                                                            r5.setForeground(Color.WHITE);

                                                                                            r1.setBounds(10,500,100,30);
                                                                                            r2.setBounds(10,530,100,30); 
                                                                                            r3.setBounds(10,560,100,30);
                                                                                            r4.setBounds(10,590,100,30);
                                                                                            r5.setBounds(10,620,100,30);

                                                                                            ButtonGroup bg=new ButtonGroup();    
                                                                                            bg.add(r1);bg.add(r2); bg.add(r3);bg.add(r4); bg.add(r5);
                                                                                            summaryFrame.add(r1);summaryFrame.add(r2);summaryFrame.add(r3);summaryFrame.add(r4);summaryFrame.add(r5);



                                                                                            JButton proceedToPayButton = new JButton("BOOK");
                                                                                            proceedToPayButton.setBounds(140,665,200,50);


                                                                                            proceedToPayButton.addActionListener(new ActionListener() {  
                                                                                                public void actionPerformed(ActionEvent e) {
                                                                                                    try {
                                                                                                        Connection bookingconn = DriverManager.getConnection(dbUrl,user,pwdd);
                                                                                                        Statement stmt = null;
                                                                                                        stmt = bookingconn.createStatement();

                                                                                                        String historyQuery = "insert into bookinghistory(cus_email,cus_name,movie,amountpaid,date,theatre) values('" + loginmailVal + "' , '" + nameVal + "','" +currMovie+ "','" +total_amount + "','" + finalDate + "','"+finalChosenTheatre+"')";
                                                                                                        stmt.executeUpdate(historyQuery);
                                                                                                        
                                                                                                        JOptionPane.showMessageDialog(summaryFrame, "BOOKING SUCCESSFUL YOU CAN REFER YOUR BOOKING DETAILS IN YOUR 'BOOKING HISTORY' OPTION! ", "Message", JOptionPane.WARNING_MESSAGE);
                                        
                                                                                                    } catch (Exception p) {
                                                                                                        System.out.println(p);
                                                                                                    }
                                                                                                }
                                                                                            });
                                                                                                                                    
                                                                                            summaryFrame.add(timeDisp);summaryFrame.add(dateDisp);summaryFrame.add(movieDisp);summaryFrame.add(seatsDisp);summaryFrame.add(seatsTypeDisp);summaryFrame.add(theatreNameDisp);
                                                                                            summaryFrame.add(dateLbl);summaryFrame.add(timeLbl);summaryFrame.add(theatreNameLbl);summaryFrame.add(seatTypeLbl);summaryFrame.add(seatsLbl);summaryFrame.add(movielbl);summaryFrame.add(proceedLbl);
                                                                                            summaryFrame.add(amountValLbl);summaryFrame.add(amountLbl);summaryFrame.add(proceedToPayButton);
                                                                                            summaryFrame.add(head);

                                                                                            summaryFrame.setSize(500,750); 
                                                                                            summaryFrame.setLayout(null);
                                                                                            summaryFrame.setVisible(true);
                                                                                            summaryFrame.setLocationRelativeTo(null);
                                                                                            summaryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                                                                                        }
                                                                                    });



                                                                                    seatsPageFrame.setSize(500,400); 
                                                                                    seatsPageFrame.setLayout(null);
                                                                                    seatsPageFrame.setVisible(true);
                                                                                    seatsPageFrame.setLocationRelativeTo(null);
                                                                                    seatsPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                                                                                }
                                                                            });

                                                                            
                                                                        }
                                                                        yaxis_tname+=100;
                                                                    }

                                                                } catch (Exception t) {
                                                                    System.out.println(t);
                                                                }
                                                                
                                                                tht.setSize(1400,1000); 
                                                                tht.setLayout(null);
                                                                tht.setVisible(true);
                                                                tht.setLocationRelativeTo(null);
                                                                tht.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                                                            }
                                                        });                                          
                                                    }    
                                                } catch (Exception nm) {
                                                    System.out.println(nm);
                                                }
                                                
                                                String subheadVal = "List of "+langVal+" movies in "+cityVal +":  "+ avlMoviescount;
                                                JLabel subhead = new JLabel(subheadVal);
                                                subhead.setBounds(30, 320, 400, 50);
                                                subhead.setFont(new Font("Poppins", Font.BOLD, 20));
                                                subhead.setForeground(Color.WHITE);

                                                hp2.add(head);hp2.add(logout);hp2.add(subhead);
                                                
                                                hp2.setSize(1400,1000); 
                                                hp2.setLayout(null);
                                                hp2.setVisible(true);
                                                hp2.setLocationRelativeTo(null);
                                                hp2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                            

                                    }
                                });


                                hp1.add(head);hp1.add(subhead);hp1.add(cityLbl);hp1.add(langLbl);
                                hp1.add(langEntry);hp1.add(cityEntry);hp1.add(searchBtn);

                                hp1.setSize(700,250); 
                                hp1.setLayout(null);
                                hp1.setVisible(true);
                                hp1.setLocationRelativeTo(null);
                                hp1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            }

                            else if (userTypeval.equals("Admin")) {
                                
                                JFrame admHp = new JFrame("MOVIE TICKET BOOKING - ADMIN PANEL");

                                admHp.getContentPane().setBackground(Color.decode("#1E0158"));

                                JLabel hlo = new JLabel();
                                String welcomeMsg = "Hello "+nameVal+", \nWhat do you want to do?";
                                hlo.setText(welcomeMsg);
                                hlo.setBounds(5, 5, 400,30);
                                hlo.setFont(new Font("Poppins", Font.BOLD, 19));
                                hlo.setForeground(Color.WHITE);
                                admHp.add(hlo);

                                //feature 1
                                JLabel searchMovLbl = new JLabel("Search for Movies:");
                                searchMovLbl.setBounds(5, 45, 300,30);
                                searchMovLbl.setFont(new Font("Poppins", Font.BOLD, 16));
                                searchMovLbl.setForeground(Color.WHITE);
                                admHp.add(searchMovLbl);

                                JLabel cityLbl = new JLabel("City:");
                                cityLbl.setBounds(5, 70, 150,30);
                                cityLbl.setFont(new Font("Poppins", Font.BOLD, 16));
                                cityLbl.setForeground(Color.WHITE);
                                admHp.add(cityLbl);

                                JTextField cityEntry = new JTextField();
                                cityEntry.setBounds(5, 100, 150,30);
                                cityEntry.setFont(new Font("Poppins", Font.BOLD, 16));
                                
                                admHp.add(cityEntry);

                                JLabel langLbl = new JLabel("Language:");
                                langLbl.setBounds(165, 70, 150,40);
                                langLbl.setFont(new Font("Poppins", Font.BOLD, 16));
                                langLbl.setForeground(Color.WHITE);
                                admHp.add(langLbl);

                                JTextField langEntry = new JTextField();
                                langEntry.setBounds(165, 100, 150,30);
                                langEntry.setFont(new Font("Poppins", Font.BOLD, 16));
                                admHp.add(langEntry);


                                JButton viewMoviesBtn = new JButton("Search"); //view movies using city and lang
                                viewMoviesBtn.setBounds(320, 100, 100,27);
                                viewMoviesBtn.setFont(new Font("Poppins", Font.BOLD, 16));
                                admHp.add(viewMoviesBtn);

                                viewMoviesBtn.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e){   
                                        JFrame movieDispFrame = new JFrame("MOVIE TICKET BOOKING - LIST OF MOVIES");

                                        movieDispFrame.getContentPane().setBackground(Color.decode("#1E0158"));
                                        
                                        JLabel head = new JLabel("List of "+langEntry.getText()+ " Movies in "+cityEntry.getText());
                                        head.setBounds(5, 5, 300, 30);
                                        head.setFont(new Font("Poppins", Font.BOLD, 16));
                                        head.setForeground(Color.WHITE);

                                        movieDispFrame.add(head);

                                        try {
                                            Connection admconn1 = DriverManager.getConnection(dbUrl,user,pwdd);
                                            Statement stmt = null;
                                            stmt = admconn1.createStatement();

                                            String moviesQuery = "select mname,mcast from movies where mlang='"+langEntry.getText()+"' and mcity = '"+cityEntry.getText()+"'";
                                            ResultSet moviesDeets = stmt.executeQuery(moviesQuery);

                                            int mNameYaxis = 0;
                                            while (moviesDeets.next()) {
                                                JLabel movieNameLbl = new JLabel();
                                                String movieName = moviesDeets.getString("mname")+" - "+moviesDeets.getString("mcast");
                                                movieNameLbl.setText(movieName);
                                                movieNameLbl.setBounds(10, 55+mNameYaxis, 400, 30);
                                                movieNameLbl.setFont(new Font("Poppins", Font.BOLD, 16));
                                                movieNameLbl.setForeground(Color.WHITE);
                                                movieDispFrame.add(movieNameLbl);
                                                mNameYaxis+=30;
                                            }
                                        
                                            
                                        } catch (Exception q) {
                                            System.out.println(q);
                                        }
                                        movieDispFrame.setSize(450,400); 
                                        movieDispFrame.setLayout(null);
                                        movieDispFrame.setVisible(true);
                                        movieDispFrame.setLocationRelativeTo(null);
                                        
                                    }
                                });

                                //feature 2
                                JButton viewUsers = new JButton("View Users");
                                viewUsers.setBounds(225, 290, 200,40);
                                viewUsers.setFont(new Font("Poppins", Font.BOLD, 18));
                                admHp.add(viewUsers);

                                viewUsers.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e){
                                        JFrame viewUsersFrame = new JFrame("MOVIE TICKET BOOKING - LIST OF USERS");

                                        viewUsersFrame.getContentPane().setBackground(Color.decode("#1E0158"));
                                        
                                        JLabel head = new JLabel("List of Users");
                                        head.setBounds(5, 5, 300, 30);
                                        head.setFont(new Font("Poppins", Font.BOLD, 16));
                                        head.setForeground(Color.WHITE);
                                        viewUsersFrame.add(head);


                                        try {
                                            Connection admconn1 = DriverManager.getConnection(dbUrl,user,pwdd);
                                            Statement stmt = null;
                                            stmt = admconn1.createStatement();

                                            String usersQuery = "select name,email from authentication where usertype='User'";
                                            ResultSet userDeets = stmt.executeQuery(usersQuery);

                                            int mNameYaxis = 0;
                                            while (userDeets.next()) {
                                                JLabel movieNameLbl = new JLabel();
                                                String movieName = userDeets.getString("name")+" - "+userDeets.getString("email");
                                                movieNameLbl.setText(movieName);
                                                movieNameLbl.setBounds(10, 55+mNameYaxis, 400, 30);
                                                movieNameLbl.setFont(new Font("Poppins", Font.BOLD, 16));
                                                movieNameLbl.setForeground(Color.WHITE);
                                                viewUsersFrame.add(movieNameLbl);
                                                mNameYaxis+=30;
                                            }

                                        } catch (Exception z) {
                                            System.out.println(z);
                                        }


                                        viewUsersFrame.setSize(450,400); 
                                        viewUsersFrame.setLayout(null);
                                        viewUsersFrame.setVisible(true);
                                        viewUsersFrame.setLocationRelativeTo(null);
                                    }
                                });
         
                                //feature 3
                                JButton addMovie = new JButton("Add a Movie");
                                admHp.add(addMovie);
                                addMovie.setBounds(15, 190, 200,40);
                                addMovie.setFont(new Font("Poppins", Font.BOLD, 18));

                                addMovie.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e){
                                        JFrame viewUsersFrame = new JFrame("MOVIE TICKET BOOKING - ADD A MOVIE");

                                        viewUsersFrame.getContentPane().setBackground(Color.decode("#1E0158"));
                                        
                                        JLabel head = new JLabel("Enter the following details");
                                        head.setBounds(5, 5, 300, 30);
                                        head.setFont(new Font("Poppins", Font.BOLD, 16));
                                        head.setForeground(Color.WHITE);
                                        viewUsersFrame.add(head);

                                        JLabel nameLbl,castLbl,langLbl,cityLbl;
                                        nameLbl=new JLabel("Name: ");  
                                        nameLbl.setBounds(50,70, 100,30);
                                        nameLbl.setFont(new Font("Poppins", Font.BOLD, 16));
                                        nameLbl.setForeground(Color.WHITE);

                                        langLbl=new JLabel("Language: ");
                                        langLbl.setBounds(50,140, 100,30);
                                        langLbl.setFont(new Font("Poppins", Font.BOLD, 16));
                                        langLbl.setForeground(Color.WHITE);

                                        castLbl=new JLabel("Cast: ");
                                        castLbl.setBounds(50,210, 200,30); 
                                        castLbl.setFont(new Font("Poppins", Font.BOLD, 16));
                                        castLbl.setForeground(Color.WHITE);

                                        cityLbl=new JLabel("City: ");
                                        cityLbl.setBounds(50,280, 200,30); 
                                        cityLbl.setFont(new Font("Poppins", Font.BOLD, 16));
                                        cityLbl.setForeground(Color.WHITE);

                                        JTextField nameEntry,langEntry,castEntry,cityEntry;

                                        nameEntry=new JTextField();  
                                        nameEntry.setBounds(230,70, 200,30);

                                        langEntry=new JTextField();  
                                        langEntry.setBounds(230,140, 200,30);

                                        castEntry=new JTextField();  
                                        castEntry.setBounds(230,210, 200,30);

                                        cityEntry=new JTextField();  
                                        cityEntry.setBounds(230,280, 200,30);

                                        JButton addMov = new JButton("ADD");
                                        addMov.setBounds(150,350, 100,30);

                                        viewUsersFrame.add(nameEntry);viewUsersFrame.add(langEntry);viewUsersFrame.add(castEntry);viewUsersFrame.add(cityEntry);viewUsersFrame.add(nameLbl);viewUsersFrame.add(castLbl);viewUsersFrame.add(langLbl);viewUsersFrame.add(cityLbl);
                                        viewUsersFrame.add(addMov);

                                        addMov.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e){
                                                try {
                                                    Connection admconn1 = DriverManager.getConnection(dbUrl,user,pwdd);
                                                    Statement stmt = null;
                                                    stmt = admconn1.createStatement();

                                                    String mname = nameEntry.getText();
                                                    String mlang = langEntry.getText();
                                                    String mcity = cityEntry.getText();
                                                    String mcast = castEntry.getText();

                                                    String usersQuery = "insert into movies(mname,mlang,mcity,mcast) values('" + mname + "' , '" + mlang + "','" +mcity+ "','" +mcast + "')";
                                                    stmt.executeUpdate(usersQuery);
                                                    

                                                    JOptionPane.showMessageDialog(viewUsersFrame, "MOVIE ADDED SUCCESSFULLY", "Message", JOptionPane.INFORMATION_MESSAGE);
                        



                                                } catch (Exception aa) {
                                                   System.out.println(aa);
                                                }

                                            }
                                        });

                                        viewUsersFrame.setSize(450,450); 
                                        viewUsersFrame.setLayout(null);
                                        viewUsersFrame.setVisible(true);
                                        viewUsersFrame.setLocationRelativeTo(null);


                                    }
                                });
                                

                                //feature 4
                                JButton delMovie = new JButton("Remove a Movie");
                                admHp.add(delMovie);
                                delMovie.setBounds(225, 190, 200,40);
                                delMovie.setFont(new Font("Poppins", Font.BOLD, 18));

                                delMovie.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e){
                                        JFrame viewUsersFrame = new JFrame("MOVIE TICKET BOOKING - REMOVE A MOVIE");

                                        viewUsersFrame.getContentPane().setBackground(Color.decode("#1E0158"));
                                        
                                        JLabel head = new JLabel("Enter the following details");
                                        head.setBounds(5, 5, 300, 30);
                                        head.setFont(new Font("Poppins", Font.BOLD, 16));
                                        head.setForeground(Color.WHITE);
                                        viewUsersFrame.add(head);

                                        JLabel nameLbl,langLbl,cityLbl;
                                        nameLbl=new JLabel("Name: ");  
                                        nameLbl.setBounds(50,70, 100,30);
                                        nameLbl.setFont(new Font("Poppins", Font.BOLD, 16));
                                        nameLbl.setForeground(Color.WHITE);

                                        langLbl=new JLabel("Language: ");
                                        langLbl.setBounds(50,140, 100,30);
                                        langLbl.setFont(new Font("Poppins", Font.BOLD, 16));
                                        langLbl.setForeground(Color.WHITE);

                                        cityLbl=new JLabel("City: ");
                                        cityLbl.setBounds(50,210, 200,30); 
                                        cityLbl.setFont(new Font("Poppins", Font.BOLD, 16));
                                        cityLbl.setForeground(Color.WHITE);

                                        JTextField nameEntry,langEntry,cityEntry;

                                        nameEntry=new JTextField();  
                                        nameEntry.setBounds(230,70, 200,30);

                                        langEntry=new JTextField();  
                                        langEntry.setBounds(230,140, 200,30);

                                        cityEntry=new JTextField();  
                                        cityEntry.setBounds(230,210, 200,30);

                                        JButton remMov = new JButton("REMOVE");
                                        remMov.setBounds(150,250, 100,30);

                                        viewUsersFrame.add(nameEntry);viewUsersFrame.add(langEntry);viewUsersFrame.add(cityEntry);viewUsersFrame.add(nameLbl);viewUsersFrame.add(langLbl);viewUsersFrame.add(cityLbl);
                                        viewUsersFrame.add(remMov);

                                        remMov.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e){
                                                try {
                                                    Connection admconn1 = DriverManager.getConnection(dbUrl,user,pwdd);
                                                    Statement stmt = null;
                                                    stmt = admconn1.createStatement();

                                                    String mname = nameEntry.getText();
                                                    String mlang = langEntry.getText();
                                                    String mcity = cityEntry.getText();

                                                    String usersQuery = "delete from movies where mname='" + mname + "' and mlang='" + mlang + "' and mcity='" +mcity+ "'";
                                                    stmt.executeUpdate(usersQuery);
                                                    

                                                    JOptionPane.showMessageDialog(viewUsersFrame, "MOVIE REMOVED SUCCESSFULLY", "Message", JOptionPane.INFORMATION_MESSAGE);
                        



                                                } catch (Exception aa) {
                                                   System.out.println(aa);
                                                }

                                            }
                                        });

                                        viewUsersFrame.setSize(450,350); 
                                        viewUsersFrame.setLayout(null);
                                        viewUsersFrame.setVisible(true);
                                        viewUsersFrame.setLocationRelativeTo(null);


                                    }
                                });
                                
                                //feature 5
                                JButton addTht = new JButton("Add a Theatre");
                                admHp.add(addTht);
                                addTht.setBounds(15, 240, 200,40);
                                addTht.setFont(new Font("Poppins", Font.BOLD, 18));

                                addTht.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e){
                                        JFrame viewUsersFrame = new JFrame("MOVIE TICKET BOOKING - ADD A THEATRE");

                                        viewUsersFrame.getContentPane().setBackground(Color.decode("#1E0158"));
                                        
                                        JLabel head = new JLabel("Enter the following details");
                                        head.setBounds(5, 5, 300, 30);
                                        head.setFont(new Font("Poppins", Font.BOLD, 16));
                                        head.setForeground(Color.WHITE);
                                        viewUsersFrame.add(head);

                                        JLabel nameLbl,movLbl,timeLbl,cityLbl;
                                        nameLbl=new JLabel("Name: ");
                                        nameLbl.setBounds(50,70, 100,30);
                                        nameLbl.setFont(new Font("Poppins", Font.BOLD, 16));
                                        nameLbl.setForeground(Color.WHITE);

                                        movLbl=new JLabel("Available Movies: ");
                                        movLbl.setBounds(50,140, 100,30);
                                        movLbl.setFont(new Font("Poppins", Font.BOLD, 16));
                                        movLbl.setForeground(Color.WHITE);

                                        timeLbl=new JLabel("Show Timings: ");
                                        timeLbl.setBounds(50,210, 200,30); 
                                        timeLbl.setFont(new Font("Poppins", Font.BOLD, 16));
                                        timeLbl.setForeground(Color.WHITE);

                                        cityLbl=new JLabel("City: ");
                                        cityLbl.setBounds(50,280, 200,30); 
                                        cityLbl.setFont(new Font("Poppins", Font.BOLD, 16));
                                        cityLbl.setForeground(Color.WHITE);

                                        JTextField nameEntry,movEntry,timeEntry,cityEntry;

                                        nameEntry=new JTextField();  
                                        nameEntry.setBounds(230,70, 200,30);

                                        movEntry=new JTextField();  
                                        movEntry.setBounds(230,140, 200,30);

                                        timeEntry=new JTextField();  
                                        timeEntry.setBounds(230,210, 200,30);

                                        cityEntry=new JTextField();  
                                        cityEntry.setBounds(230,280, 200,30);

                                        JButton addTht = new JButton("ADD");
                                        addTht.setBounds(150,350, 100,30);

                                        viewUsersFrame.add(nameEntry);viewUsersFrame.add(movEntry);viewUsersFrame.add(timeEntry);viewUsersFrame.add(cityEntry);viewUsersFrame.add(nameLbl);viewUsersFrame.add(movLbl);viewUsersFrame.add(timeLbl);viewUsersFrame.add(cityLbl);
                                        viewUsersFrame.add(addTht);

                                        addTht.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e){
                                                try {
                                                    Connection admconn1 = DriverManager.getConnection(dbUrl,user,pwdd);
                                                    Statement stmt = null;
                                                    stmt = admconn1.createStatement();

                                                    String tname = nameEntry.getText();
                                                    String tmov = movEntry.getText();
                                                    String ttime = timeEntry.getText();
                                                    String tcity = cityEntry.getText();

                                                    String usersQuery = "insert into theatres(tname,tcity,avail_movies,all_shows,avl_shows) values('" + tname + "' , '" + tcity + "','" +tmov+ "','" +ttime + "','" +ttime + "')";
                                                    stmt.executeUpdate(usersQuery);
                                                    

                                                    JOptionPane.showMessageDialog(viewUsersFrame, "THEATRE ADDED SUCCESSFULLY", "Message", JOptionPane.INFORMATION_MESSAGE);
                        



                                                } catch (Exception aa) {
                                                   System.out.println(aa);
                                                }

                                            }
                                        });

                                        viewUsersFrame.setSize(450,450); 
                                        viewUsersFrame.setLayout(null);
                                        viewUsersFrame.setVisible(true);
                                        viewUsersFrame.setLocationRelativeTo(null);


                                    }
                                });
                                
                                //feature 6
                                JButton delTht = new JButton("Remove a Theatre");
                                admHp.add(delTht);
                                delTht.setBounds(225, 240, 200,40);
                                delTht.setFont(new Font("Poppins", Font.BOLD, 18));

                                
                                 delTht.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e){
                                        JFrame viewUsersFrame = new JFrame("MOVIE TICKET BOOKING - REMOVE A THEATRE");

                                        viewUsersFrame.getContentPane().setBackground(Color.decode("#1E0158"));
                                        
                                        JLabel head = new JLabel("Enter the following details");
                                        head.setBounds(5, 5, 300, 30);
                                        head.setFont(new Font("Poppins", Font.BOLD, 16));
                                        head.setForeground(Color.WHITE);
                                        viewUsersFrame.add(head);

                                        JLabel nameLbl,cityLbl;
                                        nameLbl=new JLabel("Name: ");  
                                        nameLbl.setBounds(50,70, 100,30);
                                        nameLbl.setFont(new Font("Poppins", Font.BOLD, 16));
                                        nameLbl.setForeground(Color.WHITE);

                                        cityLbl=new JLabel("City: ");
                                        cityLbl.setBounds(50,140, 200,30); 
                                        cityLbl.setFont(new Font("Poppins", Font.BOLD, 16));
                                        cityLbl.setForeground(Color.WHITE);

                                        JTextField nameEntry,cityEntry;

                                        nameEntry=new JTextField();  
                                        nameEntry.setBounds(230,70, 200,30);

                                        cityEntry=new JTextField();  
                                        cityEntry.setBounds(230,140, 200,30);

                                        JButton remTht = new JButton("REMOVE");
                                        remTht.setBounds(150,210, 100,30);

                                        viewUsersFrame.add(nameEntry);viewUsersFrame.add(cityEntry);viewUsersFrame.add(nameLbl);viewUsersFrame.add(cityLbl);
                                        viewUsersFrame.add(remTht);

                                        remTht.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e){
                                                try {
                                                    Connection admconn1 = DriverManager.getConnection(dbUrl,user,pwdd);
                                                    Statement stmt = null;
                                                    stmt = admconn1.createStatement();

                                                    String mname = nameEntry.getText();
                                                    String mcity = cityEntry.getText();

                                                    String usersQuery = "delete from theatres where tname='" + mname + "' and tcity='" +mcity+ "'";
                                                    stmt.executeUpdate(usersQuery);
                                                    

                                                    JOptionPane.showMessageDialog(viewUsersFrame, "THEATRE REMOVED SUCCESSFULLY", "Message", JOptionPane.INFORMATION_MESSAGE);
                                                } catch (Exception aa) {
                                                   System.out.println(aa);
                                                }

                                            }
                                        });

                                        viewUsersFrame.setSize(450,300); 
                                        viewUsersFrame.setLayout(null);
                                        viewUsersFrame.setVisible(true);
                                        viewUsersFrame.setLocationRelativeTo(null);


                                    }
                                });
                                

                                //feature 7
                                JButton updTht = new JButton("Update a Theatre");
                                admHp.add(updTht);
                                updTht.setBounds(15, 290, 200,40);
                                updTht.setFont(new Font("Poppins", Font.BOLD, 18));

                                updTht.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e){
                                        JFrame viewUsersFrame = new JFrame("MOVIE TICKET BOOKING - UPDATE A THEATRE");

                                        viewUsersFrame.getContentPane().setBackground(Color.decode("#1E0158"));
                                        
                                        JLabel head = new JLabel("Enter the theatre details to update");
                                        head.setBounds(5, 5, 300, 30);
                                        head.setFont(new Font("Poppins", Font.BOLD, 16));
                                        head.setForeground(Color.WHITE);
                                        viewUsersFrame.add(head);

                                        JLabel nameLbl,cityLbl;
                                        nameLbl=new JLabel("Name: ");
                                        nameLbl.setBounds(50,70, 100,30);
                                        nameLbl.setFont(new Font("Poppins", Font.BOLD, 16));
                                        nameLbl.setForeground(Color.WHITE);

                                        cityLbl=new JLabel("City: ");
                                        cityLbl.setBounds(50,140, 200,30); 
                                        cityLbl.setFont(new Font("Poppins", Font.BOLD, 16));
                                        cityLbl.setForeground(Color.WHITE);
                                        viewUsersFrame.add(nameLbl);viewUsersFrame.add(cityLbl);

                                        JTextField nameEntry,cityEntry;
                                        nameEntry=new JTextField();  
                                        nameEntry.setBounds(230,70, 200,30);

                                        cityEntry=new JTextField();  
                                        cityEntry.setBounds(230,140, 200,30);
                                        viewUsersFrame.add(nameEntry);viewUsersFrame.add(cityEntry);

                                        JLabel subhead = new JLabel("Fill any entry to update: ");
                                        subhead.setBounds(5, 220, 300, 30);
                                        subhead.setFont(new Font("Poppins", Font.BOLD, 16));
                                        subhead.setForeground(Color.WHITE);
                                        viewUsersFrame.add(subhead);

                                        JLabel movLbl,showLbl;
                                        movLbl=new JLabel("Available Movies: ");  
                                        movLbl.setBounds(50,290, 200,30);
                                        movLbl.setFont(new Font("Poppins", Font.BOLD, 15));
                                        movLbl.setForeground(Color.WHITE);

                                        showLbl=new JLabel("Show Timings: ");
                                        showLbl.setBounds(50,360, 200,30); 
                                        showLbl.setFont(new Font("Poppins", Font.BOLD, 16));
                                        showLbl.setForeground(Color.WHITE);
                                        viewUsersFrame.add(movLbl);viewUsersFrame.add(showLbl);


                                        JTextField movEntry,showEntry;
                                        movEntry=new JTextField();  
                                        movEntry.setBounds(250,290, 200,30);

                                        JButton updMov = new JButton("UPDATE");
                                        updMov.setBounds(440,290, 100,30);

                                        showEntry=new JTextField();  
                                        showEntry.setBounds(250,360, 200,30);
                                        viewUsersFrame.add(showEntry);viewUsersFrame.add(movEntry);

                                        JButton updShow = new JButton("UPDATE");
                                        updShow.setBounds(440,350, 100,30);
      
                                        
                                        viewUsersFrame.add(updShow);viewUsersFrame.add(updMov);

                                        updMov.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e){
                                                try {
                                                    Connection admconn1 = DriverManager.getConnection(dbUrl,user,pwdd);
                                                    Statement stmt = null;
                                                    stmt = admconn1.createStatement();

                                                    String updMovies = movEntry.getText();
                                                    String tname = nameEntry.getText();
                                                    String tcity = cityEntry.getText();
                                                    

                                                    String usersQuery = "update theatres set avail_movies='" + updMovies + "' where tname='"+tname+"' and tcity='"+tcity+"' ";
                                                    stmt.executeUpdate(usersQuery);
                                                    

                                                    JOptionPane.showMessageDialog(viewUsersFrame, "THEATRE'S MOVIE DETAILS UPDATED SUCCESSFULLY", "Message", JOptionPane.INFORMATION_MESSAGE);
                        



                                                } catch (Exception aa) {
                                                   System.out.println(aa);
                                                }

                                            }
                                        });

                                        updShow.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e){
                                                try {
                                                    Connection admconn1 = DriverManager.getConnection(dbUrl,user,pwdd);
                                                    Statement stmt = null;
                                                    stmt = admconn1.createStatement();

                                                    String updShows = showEntry.getText();
                                                    String tname = nameEntry.getText();
                                                    String tcity = cityEntry.getText();
                                                    

                                                    String usersQuery = "update theatres set avl_shows='" + updShows + "' where tname='"+tname+"' and tcity='"+tcity+"' ";
                                                    stmt.executeUpdate(usersQuery);
                                                    

                                                    JOptionPane.showMessageDialog(viewUsersFrame, "THEATRE'S SHOW DETAILS UPDATED SUCCESSFULLY", "Message", JOptionPane.INFORMATION_MESSAGE);
                        



                                                } catch (Exception aa) {
                                                   System.out.println(aa);
                                                }

                                            }
                                        });

                                        viewUsersFrame.setSize(650,450); 
                                        viewUsersFrame.setLayout(null);
                                        viewUsersFrame.setVisible(true);
                                        viewUsersFrame.setLocationRelativeTo(null);


                                    }
                                });
                                
                                admHp.setSize(450,450); 
                                admHp.setLayout(null);
                                admHp.setVisible(true);
                                admHp.setLocationRelativeTo(null);
                                admHp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(f, "LOGIN FAILED!\nINVALID EMAIL/PASSWORD", "Message", JOptionPane.WARNING_MESSAGE);
                        
                        }
                    }
                    
                } catch (Exception logExcp) {
                    System.out.println(logExcp);
                }

            }
        });

        regBtn.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                JFrame r = new JFrame("Registration Form");

                BufferedImage img = null;
                try {
                    img = ImageIO.read(new File("regBgImg.jpeg"));
                } catch (IOException en) {
                    en.printStackTrace();
                }
                Image dimg = img.getScaledInstance(450, 700, Image.SCALE_SMOOTH);
                ImageIcon imageIcon = new ImageIcon(dimg);
                r.setContentPane(new JLabel(imageIcon));

                //Labels
                JLabel nameLbl,emailLbl,mobno,usertype,setPwd,cnfrmPwd;
                nameLbl=new JLabel("Name: ");  
                nameLbl.setBounds(50,70, 100,30);
                nameLbl.setFont(new Font("Poppins", Font.BOLD, 16));
                nameLbl.setForeground(Color.WHITE);

                emailLbl=new JLabel("Email: ");
                emailLbl.setBounds(50,140, 100,30);
                emailLbl.setFont(new Font("Poppins", Font.BOLD, 16));
                emailLbl.setForeground(Color.WHITE);

                mobno=new JLabel("Mobile No.: ");
                mobno.setBounds(50,210, 200,30); 
                mobno.setFont(new Font("Poppins", Font.BOLD, 16));
                mobno.setForeground(Color.WHITE);

                usertype = new JLabel("User Type: ");
                usertype.setBounds(50,280, 200,30); 
                usertype.setFont(new Font("Poppins", Font.BOLD, 16));
                usertype.setForeground(Color.WHITE);

                setPwd=new JLabel("Set password: ");  
                setPwd.setBounds(50,350, 170,30);
                setPwd.setFont(new Font("Poppins", Font.BOLD, 16));
                setPwd.setForeground(Color.WHITE);

                cnfrmPwd = new JLabel("Confirm password: ");  
                cnfrmPwd.setBounds(50,420, 170,30);
                cnfrmPwd.setFont(new Font("Poppins", Font.BOLD, 16));
                cnfrmPwd.setForeground(Color.WHITE);

                //Entries
                JTextField nameEntry,emailEntry,mobEntry;

                nameEntry=new JTextField();  
                nameEntry.setBounds(230,70, 200,30);

                JLabel nameValid=new JLabel("");
                nameValid.setBounds(230,90, 200,30);
                nameValid.setFont(new Font("Poppins", Font.PLAIN, 12));
                nameValid.setForeground(Color.RED);

                emailEntry = new JTextField();
                emailEntry.setBounds(230,140, 200,30);

                JLabel emailValid=new JLabel("");
                emailValid.setBounds(230,160, 200,30); 
                emailValid.setFont(new Font("Poppins", Font.PLAIN, 12));
                emailValid.setForeground(Color.RED);

                mobEntry = new JTextField();
                mobEntry.setBounds(230,210, 200,30);

                JLabel mobValid=new JLabel("");
                mobValid.setBounds(230,230, 200,30); 
                mobValid.setFont(new Font("Poppins", Font.PLAIN, 12));
                mobValid.setForeground(Color.RED);

                String usertypes[]={"User","Admin"};        
                final JComboBox usertypeEntry = new JComboBox(usertypes);
                usertypeEntry.setBounds(230,280, 200,30);
                
                JPasswordField setPwdEntry = new JPasswordField();
                setPwdEntry.setBounds(230,350,200,30); 

                JPasswordField cnfrmPwdEntry = new JPasswordField();
                cnfrmPwdEntry.setBounds(230,420,200,30);

                JLabel pwdValid=new JLabel("");
                pwdValid.setBounds(230,440, 200,30); 
                pwdValid.setFont(new Font("Poppins", Font.PLAIN, 12));
                pwdValid.setForeground(Color.RED);

                //Submit button
                JButton subBtn=new JButton("Submit");
                subBtn.setBounds(130,490,100, 40);
                subBtn.setBackground(Color.BLACK);
                subBtn.setForeground(Color.WHITE);
                subBtn.setOpaque(true);
                subBtn.setBorderPainted(false); 

                subBtn.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){

                        //getting all the values from entries
                        String nameval = nameEntry.getText();
                        String emailval = emailEntry.getText();
                        String mobVal = mobEntry.getText();
                        String usertypeval =  ""+usertypeEntry.getItemAt(usertypeEntry.getSelectedIndex());  
                        String pwdval = new String(setPwdEntry.getPassword());
                        String cnfrmPwdval = new String(cnfrmPwdEntry.getPassword());

                        //INPUT validations

                        //for name
                        boolean isnameValid;
                        if (nameval.length()>=1) {
                            isnameValid = true;
                        }
                        else{
                            isnameValid = false;
                        }
                        if (!isnameValid) {
                            nameEntry.setBorder(BorderFactory.createLineBorder(Color.RED));
                            nameValid.setText("Name Box should be filled");
                        }
                        if (isnameValid) {
                            nameEntry.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                            nameValid.setText("");
                        }
                        
                        
                        //for mobile number
                        String mob_regex = "(0/91)?[7-9][0-9]{9}";
                        Pattern mobpattern = Pattern.compile(mob_regex);
                        Matcher mobmatcher = mobpattern.matcher(mobVal);

                        boolean ismobValid = mobmatcher.matches();
                        if (!ismobValid) {
                            mobEntry.setBorder(BorderFactory.createLineBorder(Color.RED));
                            mobValid.setText("Invalid Mobile Number");
                        }
                        if (ismobValid) {
                            mobEntry.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                            mobValid.setText("");
                        }

                        //for email
                        String email_regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+[a-zA-Z]$";
                        Pattern emailpattern = Pattern.compile(email_regex);
                        Matcher emailmatcher = emailpattern.matcher(emailval);

                        boolean isemailValid = emailmatcher.matches();
                        if (!isemailValid) {
                            emailEntry.setBorder(BorderFactory.createLineBorder(Color.RED));
                            emailValid.setText("Invalid Email id");
                        }
                        if (isemailValid) {
                            emailEntry.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                            emailValid.setText("");
                        }

                        //for pwd LHS RHS verfication
                        boolean ispwdLen = false;
                        if (pwdval.length()>1) {
                            ispwdLen = true;
                        }
                        boolean ispwdValid = pwdval.equals(cnfrmPwdval) && ispwdLen;
                        if (!ispwdValid) {
                            cnfrmPwdEntry.setBorder(BorderFactory.createLineBorder(Color.RED));
                            pwdValid.setText("Passwords do not Match");
                        }
                        if (ispwdValid) {
                            cnfrmPwdEntry.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                            pwdValid.setText("");
                        }

                        //Registering in the system Database
                        if (ispwdValid && isemailValid && ismobValid && isnameValid) {
                            try{
                                Connection regconn = DriverManager.getConnection(dbUrl,user,pwdd);
                                Statement stmt = null;
                                stmt = regconn.createStatement();
                                
                                String registrationQuery = "insert into authentication(email,name,mobno,usertype,password)"+ "values('" + emailval + "' , '" + nameval + "','" +mobVal+ "','" +usertypeval + "','" + pwdval + "')" ;
                                stmt.executeUpdate(registrationQuery);

                                String msg = "Registration Successfull :)";

                                JOptionPane.showMessageDialog(r, msg, "Message", JOptionPane.INFORMATION_MESSAGE);
                            
                            }
                            catch(Exception regExcp){
                                System.out.println(regExcp);
                            }
                        }
                    }
                });

                r.add(nameLbl); r.add(emailLbl); r.add(mobno);r.add(usertype);r.add(cnfrmPwd); r.add(setPwd); 
                r.add(subBtn); r.add(nameEntry); r.add(setPwdEntry);r.add(mobEntry);r.add(emailEntry);r.add(usertypeEntry);
                r.add(cnfrmPwdEntry);
                r.add(nameValid);r.add(emailValid);r.add(mobValid);r.add(pwdValid);

                //regbackground.setLayout(null);

                //r.setContentPane(regbackground);
                r.setSize(450,600);
                r.setLayout(null); 
                r.setVisible(true);
                r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }

        });

        loginbackground.add(userid); loginbackground.add(pwd);  loginbackground.add(heading); loginbackground.add(noAcc);
        loginbackground.add(loginBtn);loginbackground.add(regBtn);loginbackground.add(useridEntry); loginbackground.add(pwdentry);
      
        loginbackground.setLayout(null);

        f.setContentPane(loginbackground);

        f.setSize(500,380); 
        f.setLayout(null);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
