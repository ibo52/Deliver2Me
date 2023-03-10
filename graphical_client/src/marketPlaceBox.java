/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ibrahim
 */
public class marketPlaceBox extends javax.swing.JPanel {

    /**
     * Creates new form marketPlaceBox
     */
    java.awt.Image marketImage;
    java.awt.Image rateImage;
    
    private int restaurant_id;
    
    
    public marketPlaceBox() {
        initComponents();
        marketImage=new javax.swing.ImageIcon( getClass().getResource("/Images/market/marketSampleImage.jpg")).getImage();
        rateImage=new javax.swing.ImageIcon( getClass().getResource("/Images/emoji/bad.png")).getImage();
        drawImageToPanel();
    }
    
    public int getRestaurantID(){
        return this.restaurant_id;
    }
    public void setRestaurantID(int id){
        this.restaurant_id=id;
    }
    
    public void setNameLabel(String name){
        marketNameLabel.setText(name);
    }
    
    public void setRatingLabel(String rating){
        ratingLabel.setText(rating);
    }
    
    public void setMarketImage(java.awt.Image img){
        marketImage=img;
    }
    
    public void setMarketImage(javax.swing.ImageIcon img){
        marketImage=img.getImage();
    }
    
    public void setEmojiImage(java.awt.Image img){
        rateImage=img;
        reviewEmojiLabel.setIcon(new javax.swing.ImageIcon(rateImage));
    }
    
    public void setEmojiImage(javax.swing.ImageIcon img){
        rateImage=img.getImage();
        reviewEmojiLabel.setIcon(new javax.swing.ImageIcon(rateImage));
    }
    
    public java.awt.Image getMarketImage(){
        return marketImage;
    }
    
    public java.awt.Image getEmojiImage(){
        return rateImage;
    }
    
    public void drawImageToPanel(){

        marketImageLabel.setIcon(new javax.swing.ImageIcon(
                marketImage.getScaledInstance(
                (int)marketImageLabel.getPreferredSize().getWidth(),
                (int)marketImageLabel.getPreferredSize().getHeight(), java.awt.Image.SCALE_SMOOTH) ));
        
        reviewEmojiLabel.setIcon(new javax.swing.ImageIcon(
                rateImage.getScaledInstance(
                (int)reviewEmojiLabel.getPreferredSize().getWidth(),
                (int)reviewEmojiLabel.getPreferredSize().getHeight(), java.awt.Image.SCALE_SMOOTH) ));
        revalidate();
        repaint();
        javax.swing.JFrame root=(javax.swing.JFrame)this.getParent();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        marketInfoPanel = new javax.swing.JPanel();
        ratingLabel = new javax.swing.JLabel();
        marketNameLabel = new javax.swing.JLabel();
        reviewEmojiLabel = new javax.swing.JLabel();
        MarketImagePanel = new javax.swing.JPanel();
        marketImageLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 245, 245));
        setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(220, 220, 220), new java.awt.Color(144, 144, 144)));
        setMaximumSize(new java.awt.Dimension(512, 512));
        setPreferredSize(new java.awt.Dimension(256, 256));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        marketInfoPanel.setBackground(new java.awt.Color(254, 254, 254));
        marketInfoPanel.setPreferredSize(new java.awt.Dimension(256, 76));
        marketInfoPanel.setLayout(new java.awt.BorderLayout());

        ratingLabel.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        ratingLabel.setText("rating");
        ratingLabel.setToolTipText("customer reviews");
        ratingLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        marketInfoPanel.add(ratingLabel, java.awt.BorderLayout.EAST);

        marketNameLabel.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        marketNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        marketNameLabel.setText("Market name");
        marketNameLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        marketInfoPanel.add(marketNameLabel, java.awt.BorderLayout.PAGE_START);

        reviewEmojiLabel.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        reviewEmojiLabel.setMaximumSize(new java.awt.Dimension(480, 480));
        reviewEmojiLabel.setMinimumSize(new java.awt.Dimension(48, 48));
        reviewEmojiLabel.setPreferredSize(new java.awt.Dimension(48, 48));
        reviewEmojiLabel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        marketInfoPanel.add(reviewEmojiLabel, java.awt.BorderLayout.CENTER);

        add(marketInfoPanel, java.awt.BorderLayout.SOUTH);

        MarketImagePanel.setPreferredSize(new java.awt.Dimension(256, 190));

        marketImageLabel.setPreferredSize(new java.awt.Dimension(256, 190));

        javax.swing.GroupLayout MarketImagePanelLayout = new javax.swing.GroupLayout(MarketImagePanel);
        MarketImagePanel.setLayout(MarketImagePanelLayout);
        MarketImagePanelLayout.setHorizontalGroup(
            MarketImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(marketImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
        );
        MarketImagePanelLayout.setVerticalGroup(
            MarketImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(marketImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
        );

        add(MarketImagePanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        window rootWindow = (window) javax.swing.SwingUtilities.getWindowAncestor(this);
        rootWindow.getSelectedMarketPlace(this);
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MarketImagePanel;
    private javax.swing.JLabel marketImageLabel;
    private javax.swing.JPanel marketInfoPanel;
    private javax.swing.JLabel marketNameLabel;
    private javax.swing.JLabel ratingLabel;
    private javax.swing.JLabel reviewEmojiLabel;
    // End of variables declaration//GEN-END:variables
}
