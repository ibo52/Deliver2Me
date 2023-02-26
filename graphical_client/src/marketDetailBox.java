
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ibrahim
 */
public class marketDetailBox extends javax.swing.JPanel {
    java.awt.Image marketImage;
    /**
     * Creates new form marketDetailBox
     */
    public marketDetailBox() {
        initComponents();
        marketImage=new javax.swing.ImageIcon( getClass().getResource("/Images/market/marketSampleImage.jpg")).getImage();
        drawImageToPanel();
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
        marketImageLabel = new javax.swing.JLabel();
        marketNameLabel = new javax.swing.JLabel();
        menuButton = new javax.swing.JButton();
        reviewButton = new javax.swing.JButton();
        menuListPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menuContentPanel = new javax.swing.JPanel();
        menuContent = new javax.swing.JPanel();
        reviewContent = new javax.swing.JPanel();

        setBackground(new java.awt.Color(243, 233, 233));
        setLayout(new java.awt.BorderLayout());

        marketInfoPanel.setBackground(new java.awt.Color(255, 0, 0));
        marketInfoPanel.setPreferredSize(new java.awt.Dimension(500, 120));

        marketImageLabel.setText("sssssssssssssssssssss");
        marketImageLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        marketImageLabel.setPreferredSize(new java.awt.Dimension(100, 100));
        marketImageLabel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        marketNameLabel.setFont(new java.awt.Font("Noto Mono", 1, 16)); // NOI18N
        marketNameLabel.setForeground(new java.awt.Color(254, 254, 254));
        marketNameLabel.setText("market name label");

        menuButton.setBackground(new java.awt.Color(255, 0, 0));
        menuButton.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        menuButton.setForeground(new java.awt.Color(254, 254, 254));
        menuButton.setText("menu");
        menuButton.setFocusable(false);
        menuButton.setPreferredSize(new java.awt.Dimension(96, 35));
        menuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuButtonMouseClicked(evt);
            }
        });

        reviewButton.setBackground(new java.awt.Color(255, 0, 0));
        reviewButton.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        reviewButton.setForeground(new java.awt.Color(254, 254, 254));
        reviewButton.setText("reviews");
        reviewButton.setFocusable(false);
        reviewButton.setPreferredSize(new java.awt.Dimension(96, 35));
        reviewButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reviewButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout marketInfoPanelLayout = new javax.swing.GroupLayout(marketInfoPanel);
        marketInfoPanel.setLayout(marketInfoPanelLayout);
        marketInfoPanelLayout.setHorizontalGroup(
            marketInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(marketInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(marketImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(marketInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(marketNameLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, marketInfoPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(menuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reviewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(172, Short.MAX_VALUE))
        );
        marketInfoPanelLayout.setVerticalGroup(
            marketInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, marketInfoPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(marketInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(marketInfoPanelLayout.createSequentialGroup()
                        .addComponent(marketImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(marketInfoPanelLayout.createSequentialGroup()
                        .addComponent(marketNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(marketInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(reviewButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(menuButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        add(marketInfoPanel, java.awt.BorderLayout.PAGE_START);

        menuListPanel.setBackground(new java.awt.Color(255, 0, 0));
        menuListPanel.setPreferredSize(new java.awt.Dimension(20, 0));

        javax.swing.GroupLayout menuListPanelLayout = new javax.swing.GroupLayout(menuListPanel);
        menuListPanel.setLayout(menuListPanelLayout);
        menuListPanelLayout.setHorizontalGroup(
            menuListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        menuListPanelLayout.setVerticalGroup(
            menuListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        add(menuListPanel, java.awt.BorderLayout.LINE_START);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(400, 240));

        menuContentPanel.setBackground(new java.awt.Color(254, 254, 254));
        menuContentPanel.setLayout(new java.awt.CardLayout());

        menuContent.setLayout(new javax.swing.BoxLayout(menuContent, javax.swing.BoxLayout.PAGE_AXIS));
        menuContentPanel.add(menuContent, "menuCard");

        reviewContent.setLayout(new javax.swing.BoxLayout(reviewContent, javax.swing.BoxLayout.PAGE_AXIS));
        menuContentPanel.add(reviewContent, "reviewCard");

        jScrollPane1.setViewportView(menuContentPanel);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void menuButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuButtonMouseClicked
        // TODO add your handling code here:
        java.awt.CardLayout c=(java.awt.CardLayout)(menuContentPanel.getLayout());
        c.show(menuContentPanel, "menuCard");
    }//GEN-LAST:event_menuButtonMouseClicked

    private void reviewButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reviewButtonMouseClicked
        // TODO add your handling code here:
        java.awt.CardLayout c=(java.awt.CardLayout)(menuContentPanel.getLayout());
        c.show(menuContentPanel, "reviewCard");
    }//GEN-LAST:event_reviewButtonMouseClicked
    public void drawImageToPanel(){

        marketImageLabel.setIcon(new javax.swing.ImageIcon(
                marketImage.getScaledInstance(
                (int)marketImageLabel.getPreferredSize().getWidth(),
                (int)marketImageLabel.getPreferredSize().getHeight(), java.awt.Image.SCALE_SMOOTH) ));
        
        revalidate();
        repaint();
    }
    
    public void appendMenu(String foodName,String price){
        menuItemPanel menu=new menuItemPanel();
        
        menu.setFoodLabel(foodName);
        menu.setFoodPriceLabel(price);
        
        menuContent.add(menu);
    }
    
    public void appendMenu(String foodName,String price,String foodContents, javax.swing.ImageIcon foodImg){
        menuItemPanel menu=new menuItemPanel();
        menu.setContents(foodName, price, foodContents, foodImg);
        
        menuContent.add(menu);
    }
    public void appendMenu(String foodName,String price,String foodContents){
        menuItemPanel menu=new menuItemPanel();
        menu.setContents(foodName, price, foodContents);
        
        menuContent.add(menu);
    }
    
    public void appendReview(String customerName,String reviewStr,String date, String rate){
        restaurantReviewPanel review=new restaurantReviewPanel();
        
        review.setContent(customerName, reviewStr, date, rate);
        reviewContent.add(review);
    }
    
    public void appendReview(List<List<String>> list){
        
        for (int i = 0; i < list.size(); i++) {
            
            appendReview(   list.get(i).get(0),
                            list.get(i).get(1),
                            list.get(i).get(2),
                            list.get(i).get(3) ) ;
        } 
    }
    
    public void setMarketNameLabel(String marketName){
        marketNameLabel.setText(marketName);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel marketImageLabel;
    private javax.swing.JPanel marketInfoPanel;
    private javax.swing.JLabel marketNameLabel;
    private javax.swing.JButton menuButton;
    private javax.swing.JPanel menuContent;
    private javax.swing.JPanel menuContentPanel;
    private javax.swing.JPanel menuListPanel;
    private javax.swing.JButton reviewButton;
    private javax.swing.JPanel reviewContent;
    // End of variables declaration//GEN-END:variables
}
