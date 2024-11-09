package game;

import javax.swing.*;

public class Test {
    public static void main(String[] args) {

        DataAccessMock d = new DataAccessMock();
        PhotoLocationFactory pf = new PhotoLocationFactory(d);
        ImageIcon p = pf.getRandomLocation().getPhoto();
        JFrame f =new JFrame();
        JLabel l = new JLabel(p);
        f.add(l);
        f.setSize(500,500);
        f.setVisible(true);

        System.out.println(DistanceCalculator.calculate(new double[]{43.66742755781882, -79.39177102147445},
                new double[]{43.65984277958618, -79.39718377820866}));
    }
}
