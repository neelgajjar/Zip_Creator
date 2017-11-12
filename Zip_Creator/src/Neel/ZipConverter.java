package Neel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipConverter {
	private ArrayList<File> files;
	private String zipName;
	
	public ZipConverter(ArrayList<File> files, String zipName){
		this.files = files;
		this.zipName = zipName;
		
	}
	public String zipFiles(){
		byte[] buffer = new byte[18024];
		int len;
		try{
		if(zipName.contains(".")){
			zipName = zipName.split(".")[0] +".zip";
			
		}else{
			zipName = zipName + ".zip";
		}
		File outputFile = new File(zipName);
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputFile));
		
		out.setLevel(Deflater.BEST_COMPRESSION);
		
		for(int i=0;i<files.size();i++){
			FileInputStream in = new FileInputStream(files.get(i));
			String fileName = files.get(i).getName();
			out.putNextEntry(new ZipEntry(fileName));
			
			while((len = in.read(buffer))>0){
				out.write(buffer,0,len);
			}
			out.closeEntry();
			in.close();
		}
		out.close();
		return "Successfully created zip file";
	}catch(Exception e){
		return "A exception occured during zip process";
	}
	}
}
