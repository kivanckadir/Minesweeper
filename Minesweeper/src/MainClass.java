
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class MainClass {
    static int bombCount = 50;
    static int flagCount = bombCount;
    static int rowCount = 20;
    static int columnCount = 20;
    static GridLayout tablePanel_GridLayout = new GridLayout(rowCount, columnCount);
    static Rectangle tntMatrix[][];

    public static void main(String[] args) {
        initCardPanel1();
    }

    public static void initCardPanel1() {
        JFrame jFrame = new JFrame();
        JPanel cardPanel1 = new JPanel();
        JPanel cardPanel2 = new JPanel();

        JPanel newGamePanel = new JPanel();
        JPanel tntTitlePanel = new JPanel();
        JLabel tntTitleLabel = new JLabel("Minesweeper");
        JLabel newGameLabel = new JLabel("New Game");
        CardLayout cl = new CardLayout();
        GridLayout cardPanel1_GridLayout = new GridLayout();

        cardPanel1_GridLayout.setColumns(1);
        cardPanel1_GridLayout.setRows(2);

        jFrame.setTitle("Minesweeper");
        jFrame.setLocationByPlatform(true);
        jFrame.setMinimumSize(new Dimension(550, 600));
        jFrame.setLayout(cl);

        cardPanel1.setLayout(cardPanel1_GridLayout);
        cardPanel1.add(tntTitlePanel);
        cardPanel1.add(newGamePanel);

        tntTitlePanel.setLayout(new GridBagLayout());
        tntTitlePanel.setBackground(Color.white);
        tntTitlePanel.add(tntTitleLabel);

        tntTitleLabel.setFont(new Font("Segoe UI", 1, 64));

        newGamePanel.setLayout(new GridBagLayout());
        newGamePanel.setBackground(Color.white);
        newGamePanel.add(newGameLabel);

        newGameLabel.setFont(new Font("Segoe UI", 0, 30));

        jFrame.add(cardPanel1, "1");
        jFrame.add(cardPanel2, "2");

        cl.show(jFrame.getContentPane(), "1");

        jFrame.setVisible(true);

        newGamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                newGamePanel.setBackground(new Color(240, 240, 240));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                newGamePanel.setBackground(Color.WHITE);
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
                if (SwingUtilities.isLeftMouseButton(evt)) {
                    initCardPanel2(cardPanel2);
                }
                cl.show(jFrame.getContentPane(), "2");

            }
        });
    }

    public static void initCardPanel2(JPanel cardPanel2) {
        JMenuBar menuBar = new JMenuBar();

        JMenu game = new JMenu("Game");
        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem restart = new JMenuItem("Restart");
        JMenu settings = new JMenu("Settings");
        JMenuItem easy = new JMenuItem("Easy");
        JMenuItem medium = new JMenuItem("Medium");
        JMenuItem hard = new JMenuItem("Hard");
        JMenu about = new JMenu("About");

        Color defaultMenuItemBackgroundColor = game.getBackground();
        Color defaultMenuItemForegroundColor = game.getForeground();
        Color hoverMenuItemBackgroundColor = new Color(150, 150, 150);
        Color hoverMenuItemForegroundColor = Color.white;

        JPanel topBar = new JPanel();
        JPanel containerPanel = new JPanel();
        JPanel tablePanel = new JPanel();

        tntMatrix = new Rectangle[rowCount][columnCount];
        Rectangle rectangle = new Rectangle();

        BorderLayout topBar_BorderLayout = new BorderLayout();
        BorderLayout cardPanel2_BorderLayout = new BorderLayout();

        topBar.setPreferredSize(new Dimension(5, 5));

        cardPanel2.setLayout(cardPanel2_BorderLayout);
        cardPanel2.add(menuBar, BorderLayout.PAGE_START);

        cardPanel2.add(containerPanel);

        newGame.setBackground(Color.WHITE);
        restart.setBackground(Color.WHITE);
        easy.setBackground(Color.WHITE);
        medium.setBackground(Color.WHITE);
        hard.setBackground(Color.WHITE);

        game.add(newGame);
        game.add(restart);

        settings.add(easy);
        settings.add(medium);
        settings.add(hard);

        menuBar.setBackground(Color.white);
        menuBar.add(game);
        menuBar.add(settings);
        menuBar.add(about);

        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            menuBar.getMenu(i).setFont(new Font("Segoe UI", 0, 16));
        }

        containerPanel.setBackground(Color.WHITE);
        containerPanel.setLayout(new GridBagLayout());
        containerPanel.add(tablePanel);

        createRectangleTable(tntMatrix, tablePanel);
        putBombs(tntMatrix, tablePanel);
        giveIDtoAreas(tntMatrix);

        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                newGame(tntMatrix, tablePanel);
            }
        });

        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                restart(tntMatrix);
            }

        });

        easy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bombCount = 5;
                flagCount = bombCount;
                rowCount = 8;
                columnCount = 8;
                newGame(tntMatrix, tablePanel);
            }
        });

        medium.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bombCount = 50;
                flagCount = bombCount;
                rowCount = 16;
                columnCount = 16;
                newGame(tntMatrix, tablePanel);
            }
        });

        hard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bombCount = 99;
                flagCount = bombCount;
                rowCount = 20;
                columnCount = 20;
                newGame(tntMatrix, tablePanel);
            }
        });

        about.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JDialog aboutFrame = new JDialog();
                JPanel container = new JPanel();
                GridBagLayout container_GridBagLayout = new GridBagLayout();
                GridBagConstraints container_GridBagLayoutConstraints = new GridBagConstraints();

                container.setLayout(container_GridBagLayout);
                container.add(new JLabel("This game was developed by Kadir K�van�"));

                aboutFrame.setMinimumSize(new Dimension(300, 200));
                aboutFrame.setLayout(new CardLayout());
                aboutFrame.setTitle("About");
                aboutFrame.setResizable(false);
                aboutFrame.setLocationRelativeTo(cardPanel2.getParent());
                aboutFrame.setModal(true);
                aboutFrame.add(container);
                aboutFrame.setVisible(true);
            }
        });
    }

    public static void newGame(Rectangle tntMatrix[][], JPanel tablePanel) {
        tablePanel.removeAll();
        createRectangleTable(tntMatrix, tablePanel);
        putBombs(tntMatrix, tablePanel);
        giveIDtoAreas(tntMatrix);
    }
    public static void restart(Rectangle tntMatrix[][]) {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                tntMatrix[i][j].setBackContainerLabel("");
                tntMatrix[i][j].back.setBackground(tntMatrix[1][1].defaultPanelColor);
                CardLayout cl = (CardLayout) tntMatrix[i][j].getLayout();
                cl.show(tntMatrix[i][j], "back");

                if (tntMatrix[i][j].getBackContainerLabel().equals("f")) {
                    flagCount++;
                }
            }
        }
    }
    public static void putBombs(Rectangle tntMatrix[][], JPanel tablePanel) {
        //Bombaları işaretle
        for (int k = 0; k < bombCount; k++) {
            Random randi = new Random();
            Random randj = new Random();

            int i = randi.nextInt(rowCount);
            int j = randj.nextInt(columnCount);

            if (tntMatrix[i][j].getFrontContainerLabel().equals("X")) {
                k--;
                continue;
            } else {
                tntMatrix[i][j].setFrontContainerLabel("X");
                tntMatrix[i][j].groupID = -1;
                //Sayıları işaretle
                for (int x = -1; x < 2; x++) {
                    for (int y = -1; y < 2; y++) {
                        //i,j = kendisi (atla)
                        if (i + x >= 0 && j + y >= 0 && i + x < rowCount && j + y < columnCount && !(x == 0 && y == 0)) {
                            int n = 0;
                            if (tntMatrix[i + x][j + y].getFrontContainerLabel().equals("")) {
                                n++;
                                tntMatrix[i + x][j + y].setFrontContainerLabel(Integer.toString(n));
                            } else if (tntMatrix[i + x][j + y].getFrontContainerLabel().equals("X")) {
                                continue;
                            } else if (Integer.parseInt(tntMatrix[i + x][j + y].getFrontContainerLabel()) >= 1) {
                                n = Integer.parseInt(tntMatrix[i + x][j + y].getFrontContainerLabel());
                                n++;
                                tntMatrix[i + x][j + y].setFrontContainerLabel(Integer.toString(n));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void createRectangleTable(Rectangle tntMatrix[][], JPanel tablePanel) {

        tablePanel.setBorder(new LineBorder(Color.black));
        tablePanel_GridLayout.setRows(rowCount);
        tablePanel_GridLayout.setColumns(columnCount);
        tablePanel_GridLayout.setHgap(0);
        tablePanel_GridLayout.setVgap(0);
        tablePanel.setLayout(tablePanel_GridLayout);
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(new LineBorder(new Color(198, 198, 198)));

        //Rectangle kutularını ekle
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {

                int ii = i;
                int jj = j;

                tntMatrix[i][j] = new Rectangle();
                tablePanel.add(tntMatrix[i][j]);

                tntMatrix[i][j].back.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        if (SwingUtilities.isLeftMouseButton(evt)) {
                            if (!tntMatrix[ii][jj].getBackContainerLabel().equals("f") && !tntMatrix[ii][jj].getBackContainerLabel().equals("?")) {
                                if (tntMatrix[ii][jj].getFrontContainerLabel().equals("X")) {
                                    for (int k = 0; k < rowCount; k++) {
                                        for (int l = 0; l < columnCount; l++) {
                                            if (tntMatrix[k][l].groupID == -1) {
                                                tntMatrix[k][l].openCard();
                                                tntMatrix[k][l].front.setBackground(new Color(189, 189, 189));
                                            }
                                        }
                                    }
                                    tntMatrix[ii][jj].front.setBackground(Color.red);
                                    tntMatrix[ii][jj].frontLabel.setForeground(Color.black);
                                    gameOver(tntMatrix);

                                }
                                if (tntMatrix[ii][jj].getFrontContainerLabel().equals("")) {
                                    openArea(tntMatrix, ii, jj);
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    public static void giveIDtoAreas(Rectangle tntMatrix[][]) {
        int areaCount = 0;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (tntMatrix[i][j].getFrontContainerLabel().equals("")) {
                    boolean zero = true;
                    for (int x = -1; x < 2; x++) {
                        for (int y = -1; y < 2; y++) {
                            if (i + x >= 0 && j + y >= 0 && i + x < rowCount && j + y < columnCount && !(x == 0 && y == 0)) {
                                if (tntMatrix[i + x][j + y].groupID != 0) {
                                    zero = false;}}}
                    }
                    //komşuların hepsi bölgesiz
                    if (zero == true) {
                        areaCount++;
                        for (int x = -1; x < 2; x++) {
                            for (int y = -1; y < 2; y++) {
                                if (i + x >= 0 && j + y >= 0 && i + x < rowCount && j + y < columnCount) {
                                    tntMatrix[i + x][j + y].groupID = areaCount;}}}
                    } else if (!zero) {
                        int tut[][] = new int[3][3];
                        for (int x = -1; x < 2; x++) {
                            for (int y = -1; y < 2; y++) {
                                if (i + x >= 0 && j + y >= 0 && i + x < rowCount && j + y < columnCount) {
                                    tut[x + 1][y + 1] = tntMatrix[i + x][j + y].groupID;}}
                        }
                        boolean even = false;
                        int kucuk = 0;
                        int buyuk = 0;
                        for (int a = 0; a < 3; a++) {
                            for (int b = 0; b < 3; b++) {
                                if (tut[a][b] > 0) {
                                    for (int c = 0; c < 3; c++) {
                                        for (int d = 0; d < 3; d++) {
                                            if (tut[c][d] > 0) {
                                                if (tut[a][b] != tut[c][d]) {
                                                    even = true;
                                                    if (tut[a][b] < tut[c][d]) {
                                                        kucuk = tut[a][b];
                                                        buyuk = tut[c][d];
                                                    } else if (tut[a][b] > tut[c][d]) {
                                                        kucuk = tut[c][d];
                                                        buyuk = tut[a][b];
                                                    }
                                                    break;}}}}}}
                        }

                        if (even) {
                            for (int ii = 0; ii < rowCount; ii++) {
                                for (int jj = 0; jj < columnCount; jj++) {
                                    if (tntMatrix[ii][jj].groupID == buyuk) {
                                        tntMatrix[ii][jj].groupID = kucuk;
                                    }
                                }
                            }
                        } else if (!even) {
                            int tek = 0;
                            for (int a = 0; a < 3; a++) {
                                for (int b = 0; b < 3; b++) {
                                    if (tut[a][b] > 0) {
                                        tek = tut[a][b];
                                    }
                                }
                            }

                            for (int x = -1; x < 2; x++) {
                                for (int y = -1; y < 2; y++) {
                                    if (i + x >= 0 && j + y >= 0 && i + x < rowCount && j + y < columnCount) {
                                        tntMatrix[i + x][j + y].groupID = tek;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void gameOver(Rectangle tntMatrix[][]) {
        JDialog aboutFrame = new JDialog();
        JPanel container = new JPanel();
        GridBagLayout container_GridBagLayout = new GridBagLayout();
        GridBagConstraints container_GridBagLayoutConstraints = new GridBagConstraints();

        container.setLayout(container_GridBagLayout);
        container.add(new JLabel("Game Over :("));

        aboutFrame.setMinimumSize(new Dimension(300, 200));
        aboutFrame.setLayout(new CardLayout());
        aboutFrame.setTitle("Game Over :(");
        aboutFrame.setResizable(false);
        aboutFrame.setModal(true);
        aboutFrame.add(container);
        aboutFrame.setVisible(true);
    }

    public static void control(Rectangle tntMatrix[][]) {
        int count = 0;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (tntMatrix[i][j].getBackContainerLabel().equals("f") && tntMatrix[i][j].getFrontContainerLabel().equals("X")) {
                    count++;
                }
            }
        }

        if (count == bombCount) {
            JDialog congratulation = new JDialog();
            JPanel container = new JPanel();
            GridBagLayout container_GridBagLayout = new GridBagLayout();
            GridBagConstraints container_GridBagLayoutConstraints = new GridBagConstraints();

            container.setLayout(container_GridBagLayout);
            container.add(new JLabel("Congratulations!"));

            congratulation.setMinimumSize(new Dimension(300, 200));
            congratulation.setLayout(new CardLayout());
            congratulation.setTitle("Congratulations!");
            congratulation.setResizable(false);

            congratulation.setModal(true);
            congratulation.add(container);
            congratulation.setVisible(true);
        }
    }

    public static void openArea(Rectangle tntMatrix[][], int a, int b) {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (tntMatrix[a][b].groupID == tntMatrix[i][j].groupID) {
                    if (tntMatrix[i][j].getBackContainerLabel().equals("f") || tntMatrix[i][j].getBackContainerLabel().equals("?")) {
                        continue;
                    } else {
                        tntMatrix[i][j].openCard();
                    }
                }
            }
        }
    }
}
