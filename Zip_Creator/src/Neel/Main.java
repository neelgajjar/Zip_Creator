package Neel;

import javax.swing.SwingUtilities;

public class Main {
public static void main(String[] args){
	SwingUtilities.invokeLater(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			ZipPanel zp = new ZipPanel();
			zp.setVisible(true);
		}
	});
}
}
