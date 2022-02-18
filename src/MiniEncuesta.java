import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JRadioButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.*;

public class MiniEncuesta extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel cpPic;
    private JLabel rfPic;
    private Dades d = new Dades();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MiniEncuesta frame = new MiniEncuesta();
                    frame.setSize(400, 400);
                    frame.setLayout(null);
                    frame.setTitle("ESTAT");
                    frame.setBounds(400, 200, 400, 350);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MiniEncuesta() {
        iniciar();
    }
    
    void iniciar() {
        try {

            contentPane = new JPanel();
            contentPane.setLayout(null);
            setContentPane(contentPane);

            // ### CAPTURA ###
            JLabel etiqueta = new JLabel("CAPTURA");
            etiqueta.setFont(new Font("Tahoma", Font.BOLD, 16));
            etiqueta.setForeground(Color.ORANGE);
            etiqueta.setBounds(40, 40, 99, 23);
            contentPane.add(etiqueta);

            // Inicialitzar estat Captura
            String cpImg = obtenirImatge("CP",d);
            BufferedImage img = ImageIO.read(new File(cpImg));
            
            JLabel cpPic = new JLabel(new ImageIcon(img));
            cpPic.setBounds(200, 20, 64, 64);
            contentPane.add(cpPic);
            

            // ### RADIOFREQÜÈNCIA ###
            etiqueta = new JLabel("RF");
            etiqueta.setFont(new Font("Tahoma", Font.BOLD, 16));
            etiqueta.setForeground(Color.GREEN);
            etiqueta.setBounds(40, 200, 99, 23);
            contentPane.add(etiqueta);

            String rfImg = obtenirImatge("RF",d);
            img = ImageIO.read(new File(rfImg));
            
            JLabel rfPic = new JLabel(new ImageIcon(img));
            rfPic.setBounds(200, 200, 64, 64);
            contentPane.add(rfPic);
            
            
            // ### RADIOBUTTONS ###
            // RF
            
            JRadioButton rf_prd = new JRadioButton("PRD", rfImg.equals(d.iPrd) ? true : false);
            rf_prd.setBounds(300, 205, 109, 23);
            contentPane.add(rf_prd);
            
            JRadioButton rf_des = new JRadioButton("DES", rfImg.equals(d.iDes) ? true : false);
            rf_des.setBounds(300, 230, 109, 23);
            contentPane.add(rf_des);
            
            ButtonGroup radiofrequencia = new ButtonGroup();
            radiofrequencia.add(rf_des);
            radiofrequencia.add(rf_prd);
                        
            // Listener botó Captura PRD
            rf_prd.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	/*
            			sap 	-> sap_DES
            			sap_PRD -> sap
                	*/
                	canviarNoms("RF", d.rutaRF+d.fSapDES, d.rutaRF+d.fSapPRD, d.iPrd);
                }
            });
            
            // Listener botó Captura DES
            rf_des.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    /*
                		sap 	-> sap_PRD
                		sap_des -> sap
                	*/
                    canviarNoms("RF", d.rutaRF+d.fSapPRD, d.rutaRF+d.fSapDES, d.iDes);
                }
            });
            
            
            // CAPTURA
            
            JRadioButton c_prd = new JRadioButton("PRD", cpImg.equals(d.iPrd) ? true : false);
            c_prd.setBounds(300, 25, 109, 23);
            contentPane.add(c_prd);
            
            JRadioButton c_des = new JRadioButton("DES", cpImg.equals(d.iDes) ? true : false);
            c_des.setBounds(300, 50, 109, 23);
            contentPane.add(c_des);
            
            ButtonGroup captura = new ButtonGroup();
            captura.add(c_des);
            captura.add(c_prd);
                        
            // Listener botó Captura PRD
            c_prd.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	/*
            			sap 	-> sap_DES
            			sap_PRD -> sap
                	*/
                	canviarNoms("CP", d.rutaCP+d.fSapDES, d.rutaCP+d.fSapPRD, d.iPrd);
                }
            });
            // Listener botó Captura DES
            c_des.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    /*
                		sap 	-> sap_PRD
                		sap_des -> sap
                	*/
                    canviarNoms("CP", d.rutaCP+d.fSapPRD, d.rutaCP+d.fSapDES, d.iDes);
                }
            });

            
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
    
    private String obtenirImatge(String app, Dades d) {
    	String imatge = d.iDes;
    	File f;
    	if(app.equals("CP")) {
    		f = new File(d.rutaCP+d.fSapDES);
    	} else if(app.equals("RF")) {
    		f = new File(d.rutaRF+d.fSapDES);
    	} else return null;
    	
        // Si sap_DES existeix, vol dir que està a PRD
        if (f.exists()) {
            imatge = d.iPrd;
        }
        return imatge;
	}

	/**
     * 
     * @param n1  Fitxer que s'inhabilitarà
     * @param n2  Fitxer que s'habilitarà
     * @param img Imatge que es mostrarà
     */
    private void canviarNoms(String bbdd, String n1, String n2, String img) {
    	
    	System.out.println(img);
    	
    	File f1 = new File((bbdd.equals("CP") ? d.rutaCP : d.rutaRF) + d.fSap);
        File r1 = new File(n1);

        File f2 = new File(n2);
        File r2 = new File((bbdd.equals("CP") ? d.rutaCP : d.rutaRF) + d.fSap);
         
    	if (f1.renameTo(r1) == true) {
            if (f2.renameTo(r2) == true) {
                JOptionPane.showMessageDialog(null, "Correcte");
                try {
                	if(bbdd.equals("CP"))
                		cpPic.setIcon(new ImageIcon(ImageIO.read(new File(img))));
                	else
                		rfPic.setIcon(new ImageIcon(ImageIO.read(new File(img))));
                } catch (Exception e) {e.printStackTrace();}
            } else {
                JOptionPane.showMessageDialog(null, "ERROR 2");
            }
        } else {
            JOptionPane.showMessageDialog(null, "ERROR 1");
        }
    }
    

}