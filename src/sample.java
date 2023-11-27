import java.sql.*;

public class sample {
    public static void main(String[] args) {
        final String dbUrl = "jdbc:mysql://localhost/movietcktbooking?serverTimezone=UTC";
        final String user = "root";
        final String pwdd = "sanju1304";

        Connection avlMvsconn = null;
        Statement stmt = null;

        String avlMoviescount = "";
        try {
            avlMvsconn = DriverManager.getConnection(dbUrl,user,pwdd);
            stmt = avlMvsconn.createStatement();
            String avlMvsQuery = "select count(*) from movies where mlang='Tamil' and mcity = 'Bangalore'";
            ResultSet avlMvsDeets=stmt.executeQuery(avlMvsQuery);

            while (avlMvsDeets.next()) {
                avlMoviescount = avlMvsDeets.getString("count(*)");
            }

            String movienamesQuery = "select mname,mcast from movies where mlang='Tamil' and mcity = 'Bangalore'";
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

            
            for (int j = 0; j < movieNames.length; j++) {
                System.out.println(movieNames[j]+" "+movieCasts[j]);
            }
            

            
        } catch (Exception nm) {
            System.out.println(nm);
        }

        

        
    }
}


// Container c = hp2.getContentPane();

// //setting a hero image
// BufferedImage heroimg = null;
// try {
//     heroimg = ImageIO.read(new File("heroImg_homepage.jpg"));
// } catch (IOException en) {
//     en.printStackTrace();
// }
// JLabel heroImg = new JLabel();
// Image dimg = heroimg.getScaledInstance(1350, 250, Image.SCALE_SMOOTH);
// ImageIcon imageIcon = new ImageIcon(dimg);
// heroImg.setIcon(imageIcon);
// heroImg.setBounds(30, 70, 1350, 250);

// c.add(heroImg);