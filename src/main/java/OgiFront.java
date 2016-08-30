package main.java;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JRadioButton;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.io.File;

public class OgiFront extends JFrame {
	private JPanel contentPane;
	private JTextField txtbasediroutput;
	private JTextField textField_1;
	private JLabel lblNumberOfCo;
	private JButton btnExit;
	private JButton btnExit_1;

	/*
	 * Declare formulator
	 */
	public TarqlFormulator tarqlformulator;

	/*
	 * Declare working directory
	 */
	String workingDir;

	private JTextField textField;
	private JLabel lblMeasuresmeasuremetricmeasuremetric;
	private JTextField textField_2;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JRadioButton rdbtnNewRadioButton_3;
	private JRadioButton rdbtnNewRadioButton_4;
	private JLabel lblNewLabel_2;
	private JRadioButton rdbtnNewRadioButton_5;
	private JTextField textField_4;
	private String marineDatasetName;
	private String serialization;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.out.println(new File("").getAbsolutePath());
		System.out.println(System.getProperty("user.home"));
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OgiFront frame = new OgiFront();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public OgiFront() {

		/*
		 * initiate formulator on the constructor
		 */

		tarqlformulator = new TarqlFormulator();
		marineDatasetName = "embty";
		serialization = "embty";
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton_1 = new JButton("Generate Cube");

		txtbasediroutput = new JTextField();
//		workingDir = System.getProperty("user.dir");
		workingDir=new File("").getAbsolutePath();
		txtbasediroutput.setText(workingDir + "/src/main/resources/output/");

		// btnNewButton_1.addMouseListener(new MouseAdapter() {
		// @Override
		// public void mouseClicked(MouseEvent e) {
		//
		// }
		// });
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* Getting choosed marine dataset name */
				if (rdbtnNewRadioButton_3.isSelected()) {
					marineDatasetName = rdbtnNewRadioButton_3.getText();
				}
				if (rdbtnNewRadioButton_2.isSelected()) {
					marineDatasetName = rdbtnNewRadioButton_2.getText();

				}
				if (rdbtnNewRadioButton_4.isSelected()) {
					marineDatasetName = rdbtnNewRadioButton_4.getText();

				}
				if (rdbtnNewRadioButton_5.isSelected()) {
					marineDatasetName = "other";

				}
				/* Getting choosed RDF Serialization Format */
				if (rdbtnNewRadioButton.isSelected()) {
					serialization = " ";/*
										 * because this is the default
										 * serialization output of tarql
										 */
				}
				if (rdbtnNewRadioButton_1.isSelected()) {
					serialization = rdbtnNewRadioButton_1.getText()
							.toLowerCase();

				}

				String csvLocation = textField_1.getText();
				String cubeDestinatoin = txtbasediroutput.getText();
				String numberofcolumns = "";

				System.out.print(csvLocation +"\n"+ cubeDestinatoin
						+ numberofcolumns +"\n"+ marineDatasetName +"\n"+ serialization);
				
				tarqlformulator.tarqlExcution(csvLocation, cubeDestinatoin,
						numberofcolumns, marineDatasetName, serialization);

				JOptionPane.showMessageDialog(null,
						"Transformation is in process check Cube destination folder!"
								+ "\r\n " + "Source:" + csvLocation + "\r\n "
								+ "Destination:" + cubeDestinatoin);

			}
		});
		btnNewButton_1.setBounds(152, 535, 146, 25);
		contentPane.add(btnNewButton_1);

		btnExit = new JButton("Clear");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				txtbasediroutput.setText("");
				textField_4.setText("");

			}
		});

		btnExit.setBounds(13, 535, 117, 25);
		contentPane.add(btnExit);

		btnExit_1 = new JButton("Next");
		btnExit_1.setBounds(310, 535, 117, 25);
		contentPane.add(btnExit_1);

		textField_1 = new JTextField();
		textField_1.setBounds(153, 33, 274, 25);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		txtbasediroutput.setBounds(156, 501, 275, 25);
		contentPane.add(txtbasediroutput);
		txtbasediroutput.setColumns(10);

		JLabel lblNewLabel = new JLabel("Cube Destination");
		lblNewLabel.setBounds(13, 503, 136, 20);
		contentPane.add(lblNewLabel);

		lblNumberOfCo = new JLabel("Dimensions (separated by \",\")");
		lblNumberOfCo.setBounds(96, 237, 225, 15);
		contentPane.add(lblNumberOfCo);

		JLabel lblDataSetLocation = new JLabel("Data Set Location");
		lblDataSetLocation.setBounds(10, 38, 136, 15);
		contentPane.add(lblDataSetLocation);

		textField = new JTextField();
		textField.setBounds(236, 264, 195, 25);
		contentPane.add(textField);
		textField.setColumns(10);

		lblMeasuresmeasuremetricmeasuremetric = new JLabel(
				"Measures(measure1;metric,measure2;metric)");
		lblMeasuresmeasuremetricmeasuremetric.setBounds(96, 298, 344, 20);
		contentPane.add(lblMeasuresmeasuremetricmeasuremetric);

		textField_2 = new JTextField();
		textField_2.setBounds(236, 330, 195, 25);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("RDF Serlization");
		lblNewLabel_1.setBounds(13, 479, 117, 15);
		contentPane.add(lblNewLabel_1);

		rdbtnNewRadioButton = new JRadioButton("Turtle");
		rdbtnNewRadioButton.setBounds(149, 475, 74, 23);
		contentPane.add(rdbtnNewRadioButton);

		rdbtnNewRadioButton_1 = new JRadioButton("Ntriples");
		rdbtnNewRadioButton_1.setBounds(246, 475, 149, 20);
		contentPane.add(rdbtnNewRadioButton_1);

		ButtonGroup bG1 = new ButtonGroup();
		bG1.add(rdbtnNewRadioButton);
		bG1.add(rdbtnNewRadioButton_1);

		rdbtnNewRadioButton_2 = new JRadioButton(
				"IrishNationalTideGaugeNetwork");
		rdbtnNewRadioButton_2
				.setToolTipText("http://erddap.marine.ie/erddap/tabledap/IrishNationalTideGaugeNetwork.html");
		rdbtnNewRadioButton_2.setBounds(22, 120, 264, 23);
		contentPane.add(rdbtnNewRadioButton_2);

		rdbtnNewRadioButton_3 = new JRadioButton("IWBNetwork");
		rdbtnNewRadioButton_3
				.setToolTipText("http://erddap.marine.ie/erddap/tabledap/IWBNetwork.html");
		rdbtnNewRadioButton_3.setBounds(22, 93, 264, 23);
		contentPane.add(rdbtnNewRadioButton_3);

		rdbtnNewRadioButton_4 = new JRadioButton("IMI_EATL_WAVE");
		rdbtnNewRadioButton_4
				.setToolTipText("http://erddap.marine.ie/erddap/griddap/IMI_EATL_WAVE.html");
		rdbtnNewRadioButton_4.setBounds(22, 147, 264, 23);
		contentPane.add(rdbtnNewRadioButton_4);

		rdbtnNewRadioButton_5 = new JRadioButton("Other, upload your schema");
		rdbtnNewRadioButton_5
				.setToolTipText("http://erddap.marine.ie/erddap/info/index.html?page=1&itemsPerPage=1000");
		rdbtnNewRadioButton_5.setBounds(22, 206, 218, 23);
		contentPane.add(rdbtnNewRadioButton_5);

		ButtonGroup bG = new ButtonGroup();
		bG.add(rdbtnNewRadioButton_2);
		bG.add(rdbtnNewRadioButton_3);
		bG.add(rdbtnNewRadioButton_4);
		bG.add(rdbtnNewRadioButton_5);

		lblNewLabel_2 = new JLabel("Choose data schema");
		lblNewLabel_2.setBounds(13, 70, 210, 15);
		contentPane.add(lblNewLabel_2);

		textField_4 = new JTextField();
		textField_4.setBounds(246, 210, 185, 19);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JRadioButton rdbtnNewRadioButton_6 = new JRadioButton("IWaveBNetwork30Min");
		rdbtnNewRadioButton_6.setBounds(21, 175, 195, 23);
		contentPane.add(rdbtnNewRadioButton_6);

	}
}
