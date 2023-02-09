import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class FortuneTellerFrame extends JFrame {
    private JButton fortuneButton = new JButton("Read My Fortune!");
    private JButton quitButton = new JButton("Quit");
    int preFortune;


    public FortuneTellerFrame (){
        super("Fortune Teller");

        ArrayList<String> fortune= new ArrayList<String>();

        fortune.add("You shale receive a sword from the lady of the lake and become ruler of the land of Camelot.");
        fortune.add("Michigan will reclaim the Toledo Strip");
        fortune.add("Cincinnati will seceded from Ohio forming the Queen City State");
        fortune.add("Florida will rise up against the Despot Desantis. The revolution will be funded and based out of Disney World.");
        fortune.add("Michigan will be forced to defend the great lakes from short sighted states who want to steal the water");
        fortune.add("Truth will come out of her deep well, she ain’t suffering lies no more.");
        fortune.add("The Lords, gonna come and she ain’t pleased, she ain’t suffering fools no more.");
        fortune.add("The Republican Party will rebrand as the Facist Party, it will be more factual at least");
        fortune.add("Mr.Putin will declare victory is assured, as Ukraine liberates Moscow.");
        fortune.add("The EU will be restructured into the European Federation Of States");
        fortune.add("The UK will be dissolved freeing Scotland and Wales to rejoin the EU.");
        fortune.add("Northern Ireland will be absorbed by Ireland");
        BufferedImage teller = null;
        try {
            teller = ImageIO.read(new File("Hecate.jpg"));
        } catch (IOException e) {
        }

        this.preFortune = -1;

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;


        // center frame in screen

        setSize(screenWidth / 2, screenHeight / 2);
        setLocation(screenWidth / 4, screenHeight / 4);
        Image atom = teller.getScaledInstance(330/3,480/3,Image.SCALE_DEFAULT);


        JLabel hecate = new JLabel(new ImageIcon(atom));
        hecate.setSize(1,1);
        JLabel nyx = new JLabel("The Fortune Teller");
        Font titleF=new Font("Monospaced",Font.ITALIC, 36);
        Font buttonF=new Font("Serif",Font.BOLD, 16);
        Font displayF=new Font("Dialog",Font.CENTER_BASELINE, 12);

        nyx.setFont(titleF);
        nyx.setHorizontalAlignment(SwingConstants.CENTER);
        JTextArea ta= new JTextArea("", 5, 50); // Text area
        ta.setLineWrap(true);
        JScrollPane sbrText = new JScrollPane(ta); // Scroll pane for text area


        sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        ta.setFont(displayF);

        setIconImage(teller);
        JPanel topPanel= new JPanel();
        JPanel midPanel= new JPanel();
        JPanel bottomPanel= new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        midPanel.setLayout(new BorderLayout());
        topPanel.setLayout(new BorderLayout());
        topPanel.add(hecate,BorderLayout.SOUTH);

        topPanel.add(nyx, BorderLayout.NORTH);
        midPanel.add(sbrText,BorderLayout.CENTER);
        fortuneButton.setFont(buttonF);
        quitButton.setFont(buttonF);
        bottomPanel.add(fortuneButton,BorderLayout.NORTH);
        bottomPanel.add(quitButton,BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(bottomPanel,BorderLayout.SOUTH);
        add(topPanel,BorderLayout.NORTH);
        add(midPanel,BorderLayout.CENTER);
        quitButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                }
        );
        fortuneButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(preFortune==-1){
                            ta.setText(getFortune(fortune));
                        }else{
                            ta.setText(ta.getText()+"\n"+getFortune(fortune));
                        }




                    }
                }
        );


        this.show();




    }
    public String getFortune(ArrayList<String> fList){
        String outVal;
        int select;
        if (this.preFortune==-1){
            select = getRandomWithExclusion(fList.size()-1,this.preFortune);
        }else{
           select = getRandomWithExclusion(fList.size()-1,this.preFortune);
        }
        outVal = fList.get(select);
        return outVal;

    }
    public int getRandomWithExclusion( int end, int... exclude) {
        int start = 0;
        Random rnd = new Random();
        int random = start + rnd.nextInt(end - start + 1 - exclude.length);
        for (int ex : exclude) {
            if (random < ex) {
                break;
            }
            random++;
        }
        this.preFortune=random;
        return random;
    }

}
