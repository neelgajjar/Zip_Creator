package Neel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ZipPanel extends JFrame{
	JPanel panel = new JPanel();
	ArrayList<File> fileList;
	public ZipPanel(){
		initUI();
		fileList = new ArrayList<File>();
		
	}
	private void initUI(){
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		final JPanel filePanel = new JPanel();
		filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.X_AXIS));
		final JButton addFileButton = new JButton("Add files you want to be zipped:   ");
		filePanel.add(addFileButton);
		
		final JTextField fileBox = new JTextField(24);
		fileBox.setEditable(false);
		filePanel.add(fileBox);
		panel.add(filePanel);
		
		final JPanel savePanel = new JPanel();
		savePanel.setLayout(new BoxLayout(savePanel, BoxLayout.X_AXIS));
		final JButton saveFileButton = new JButton("Select location and name for Zip:");
	    savePanel.add(saveFileButton);
		
		final JTextField saveFileBox = new JTextField(24);
		saveFileBox.setEditable(false);
		savePanel.add(saveFileBox);
		panel.add(savePanel);
		
		final JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		final JButton createButton = new JButton("Create Zip file");
		buttonPanel.add(createButton);
		
		final JButton clearFileButton = new JButton("Clear file list");
		buttonPanel.add(clearFileButton);
		panel.add(buttonPanel);
		
		addFileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fc = new JFileChooser();
				fc.setMultiSelectionEnabled(true);
				int returnVal = fc.showOpenDialog(ZipPanel.this);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					File files[] = fc.getSelectedFiles();
					for(int i=0;i<files.length;i++){
						File file = files[i];
						fileList.add(file);
						String fileName = file.getName();
						String currentText = fileBox.getText();
						if(currentText.length()!=0){
							fileBox.setText(currentText+", "+ fileName);
							
						}else{
							fileBox.setText(fileName);
						}
					}
				}
			}
		});
		
		createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ZipConverter zip = new ZipConverter(fileList, saveFileBox.getText());
				String result = zip.zipFiles();
				JOptionPane.showMessageDialog(null, result);
			}
		});
		saveFileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(ZipPanel.this);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					File file = fc.getSelectedFile();
					saveFileBox.setText(file.getAbsolutePath());
				}
			}
		});
		
		clearFileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fileBox.setText("");
			}
		});
		add(panel);
		pack();
		setTitle("ZipFile Creator");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
