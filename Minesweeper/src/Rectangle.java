import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class Rectangle extends JPanel {
                JPanel back = new JPanel();
                JPanel front = new JPanel();
                JLabel backLabel = new JLabel();
                JLabel frontLabel = new JLabel();
                int groupID=0;
                CardLayout tntCard = new CardLayout();
                
                Color defaultPanelColor = this.getBackground();
                Color hoverColor = new Color(74, 133, 178);
                
                public Rectangle() {
                               setLayout(tntCard);
                               
                               this.add(back, "back");
                               this.add(front,"front");
                               
                               if(MainClass.rowCount==8){
                                               back.setPreferredSize(new Dimension(55, 55));
                                               backLabel.setFont(new Font("Segoe UI", Font.BOLD, 35));
                                               frontLabel.setFont(new Font("Segoe UI", Font.BOLD, 35));
                               }
                               else if(MainClass.columnCount==16){
                                               back.setPreferredSize(new Dimension(30, 30));
                                               backLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
                                               frontLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
                               }
                               else if(MainClass.rowCount==20){
                                               back.setPreferredSize(new Dimension(25, 25));
                                               backLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
                                               frontLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
                               }
                               
                               back.setBorder(new BevelBorder(0));
                               back.setLayout(new GridBagLayout());
                               back.add(backLabel);
                               
                               front.setPreferredSize(new Dimension(25, 25));
                               front.setBorder(new LineBorder(new Color(168, 168, 168)));
                               front.setLayout(new GridBagLayout());
                               front.add(frontLabel);
                               
                               tntCard.show(this, "back");
                               
                               back.addMouseListener(new MouseAdapter() {
                                               @Override
                                               public void mouseEntered(MouseEvent evt){
                                                               if(!getBackContainerLabel().equals("f") && !getBackContainerLabel().equals("?")){
                                                                               if(back.getBackground()==defaultPanelColor){
                                                                                              back.setBackground(hoverColor);
                                                                               }
                                                               }
                                               }
                                               
                                               @Override
                                               public void mouseExited(MouseEvent evt){
                                                               if(!getBackContainerLabel().equals("f") && !getBackContainerLabel().equals("?")){
                                                                               if(back.getBackground()==hoverColor){
                                                                                              back.setBackground(defaultPanelColor);
                                                                               }
                                                               }
                                               }
                                               
                                               @Override
                                               public void mouseClicked(MouseEvent evt){
                                                               if(!getBackContainerLabel().equals("f") && !getBackContainerLabel().equals("?")){
                                                                               if(SwingUtilities.isLeftMouseButton(evt)){
                                                                                              openCard();
                                                                               }
                                                               }
                                               }
                                               
                                               @Override
                                               public void mousePressed(MouseEvent evt){
                                                               
                                                               if(SwingUtilities.isLeftMouseButton(evt) && !getBackContainerLabel().equals("f") && !getBackContainerLabel().equals("?"))
                                                                               back.setBackground(new Color(191, 192, 193));
                                                               
                                                               else if(SwingUtilities.isRightMouseButton(evt)){
                                                                               if(backLabel.getText().equals("")){
                                                                                              MainClass.flagCount--;
                                                                                              backLabel.setText("f");
                                                                                              backLabel.setForeground(Color.black);
                                                                                              back.setBackground(Color.green);
                                                                                              
                                                                                              if(MainClass.flagCount==0){
                                                                                                              MainClass.control(MainClass.tntMatrix);
                                                                                              }
                                                                               }
                                                                               
                                                                               else if(backLabel.getText().equals("f")){
                                                                                              MainClass.flagCount++;
                                                                                              backLabel.setText("?");
                                                                                              backLabel.setForeground(Color.white);
                                                                                              back.setBackground(Color.BLUE);
                                                                               }
                                                                               
                                                                               else if(backLabel.getText().equals("?")){
                                                                                              backLabel.setText("");
                                                                                              back.setBackground(defaultPanelColor);
                                                                               }
                                                               }
                                               }
                                               
                                               @Override
                                               public void mouseReleased(MouseEvent evt){
                                                               if(!getBackContainerLabel().equals("f") && !getBackContainerLabel().equals("?")){
                                                               if(SwingUtilities.isLeftMouseButton(evt))
                                                                               back.setBackground(defaultPanelColor);
                                               }}
                                               
                               });
                               
                               
                }
                
                public void closeCard(){
                               tntCard.show(this, "back");                        
                }
                
                public void openCard(){
                                               tntCard.show(this, "front");

                                               if(getFrontContainerLabel().equals("")){
                                               frontLabel.setForeground(Color.blue);
                                               front.setBackground(new Color(189, 189, 189));
                                               }
                                               
                                               else if(getFrontContainerLabel().equals("X")){
                                                               frontLabel.setForeground(Color.red);
                                               }
                               
                                               else if(getFrontContainerLabel().equals("1")){
                                               frontLabel.setForeground(Color.blue);
                                               front.setBackground(new Color(189, 189, 189));
                                               }
                               
                                               else if(getFrontContainerLabel().equals("2")){
                                                               frontLabel.setForeground(Color.black);
                                                               front.setBackground(new Color(189, 189, 189));
                                               }
                               
                                               else if(getFrontContainerLabel().equals("3")){
                                                               frontLabel.setForeground(new Color(191, 255, 0));
                                                               front.setBackground(new Color(189, 189, 189));
                                               }
                               
                                               else if(getFrontContainerLabel().equals("4")){
                                                               frontLabel.setForeground(Color.red);
                                                               front.setBackground(new Color(189, 189, 189));
                                               }
                                               
                                               else if(getFrontContainerLabel().equals("5")){
                                                               frontLabel.setForeground(Color.red);
                                                               front.setBackground(new Color(189, 189, 189));
                                               }
                               
                                               else if(getFrontContainerLabel().equals("6")){
                                                               frontLabel.setForeground(Color.red);
                                                               front.setBackground(new Color(189, 189, 189));
                                               }
                               
                                               else if(getFrontContainerLabel().equals("7")){
                                                               frontLabel.setForeground(Color.red);
                                                               front.setBackground(new Color(189, 189, 189));
                                               }
                               
                                               else if(getFrontContainerLabel().equals("8")){
                                                               frontLabel.setForeground(Color.red);
                                                               front.setBackground(new Color(189, 189, 189));
                                               }
                }
                
                public void setBackContainerLabel(String container){
                               backLabel.setText(container);
                }
                
                public void setFrontContainerLabel(String container){
                               frontLabel.setText(container);
                }
                
                public String getFrontContainerLabel(){
                               return frontLabel.getText();
                }
                
                public String getBackContainerLabel(){
                               return backLabel.getText();
                }

}

