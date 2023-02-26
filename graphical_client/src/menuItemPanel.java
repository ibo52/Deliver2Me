
import java.awt.Color;







/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ibrahim
 */
public class menuItemPanel extends javax.swing.JPanel {

    /**
     * Creates new form menuItemPanel
     */
    public boolean setButtonAsRemove=false;
    
    java.awt.Image foodImage;
    public menuItemPanel(int command) {
        initComponents();
        foodImage=new javax.swing.ImageIcon( getClass().getResource("/Images/food/SampleFood.jpg")).getImage();
        
        if (command==-1) {
            this.appendButton.setBackground(Color.red);
            this.appendButton.setForeground(Color.black);
            this.appendButton.setText("Remove");
            setButtonAsRemove=true;
        }
        drawImageToPanel();
    }
    public menuItemPanel() {
        initComponents();
        foodImage=new javax.swing.ImageIcon( getClass().getResource("/Images/food/SampleFood.jpg")).getImage();
        drawImageToPanel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public void drawImageToPanel(){

        foodImageLabel.setIcon(new javax.swing.ImageIcon(
                foodImage.getScaledInstance(
                (int)foodImageLabel.getPreferredSize().getWidth(),
                (int)foodImageLabel.getPreferredSize().getHeight(), java.awt.Image.SCALE_SMOOTH) ));
        
        revalidate();
        repaint();
    }
    
    public void setFoodPriceLabel(String text){
        this.priceLabel.setText(text);
    }
    public void setFoodLabel(String text){
        this.foodNameLabel.setText(text);
    }
    public String getFoodLabel(){
        return this.foodNameLabel.getText();
    }
    public void setFoodContentLabel(String text){
        this.foodContentsLabel.setText(text);
    }
    public void setFoodImage(javax.swing.ImageIcon img){
        foodImage=img.getImage();
        drawImageToPanel();
        
    }
    public void setContents(String foodName,String price,String foodContents, javax.swing.ImageIcon foodImg){
        setFoodLabel(foodName);
        setFoodPriceLabel(price);
        setFoodContentLabel(foodContents);
        setFoodImage(foodImg);
    }
    public void setContents(String[] contents){
        setFoodLabel(contents[0]);
        setFoodPriceLabel(contents[1]);
        setFoodContentLabel(contents[2]);
    }
    public void setContents(String foodName,String price,String foodContents){
        setFoodLabel(foodName);
        setFoodPriceLabel(price);
        setFoodContentLabel(foodContents);
    }
    public String[] getContents(){
        String[] l={this.foodNameLabel.getText(),
            this.priceLabel.getText(),
            this.foodContentsLabel.getText()};
        
        return l;
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        foodImageLabel = new javax.swing.JLabel();
        foodNameLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        foodContentsLabel = new javax.swing.JLabel();
        appendButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(250, 250, 250));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(228, 228, 228)));
        setPreferredSize(new java.awt.Dimension(360, 100));

        foodImageLabel.setText("food image");
        foodImageLabel.setPreferredSize(new java.awt.Dimension(76, 76));

        foodNameLabel.setForeground(new java.awt.Color(1, 1, 1));
        foodNameLabel.setText("food name");

        priceLabel.setForeground(new java.awt.Color(1, 1, 1));
        priceLabel.setText("price");
        priceLabel.setPreferredSize(new java.awt.Dimension(64, 19));

        foodContentsLabel.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        foodContentsLabel.setForeground(new java.awt.Color(128, 128, 128));
        foodContentsLabel.setText("food contents or anything");

        appendButton.setText("Append");
        appendButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                appendButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(foodImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(foodNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(foodContentsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(priceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(appendButton))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(foodNameLabel)
                            .addComponent(priceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(appendButton))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(foodImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(foodContentsLabel)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void appendButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appendButtonMouseClicked
        // TODO add your handling code here:
        window root=(window)javax.swing.SwingUtilities.getWindowAncestor(this);

        if (setButtonAsRemove) {
            root.removeItemFromCart(this);
        }else{
            menuItemPanel mip=new menuItemPanel(-1);
            
            mip.setContents(this.getContents());
            root.addItemToCart(mip);
        }
        
    }//GEN-LAST:event_appendButtonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton appendButton;
    private javax.swing.JLabel foodContentsLabel;
    private javax.swing.JLabel foodImageLabel;
    private javax.swing.JLabel foodNameLabel;
    private javax.swing.JLabel priceLabel;
    // End of variables declaration//GEN-END:variables
}
