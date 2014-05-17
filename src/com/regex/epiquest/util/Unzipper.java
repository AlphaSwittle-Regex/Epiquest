package com.regex.epiquest.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class Unzipper
{
	List<String> fileList;

	public static void unzip(String zipFile, String outputFolder, JProgressBar progress, JLabel label, JFrame frame)
	{
		frame.setTitle("Epiquest Modpack v1.0-1.7.2 --md::unzipper");
		byte[] buffer = new byte[1024];
		progress.setIndeterminate(true);
		progress.setStringPainted(false);
		try
		{
			File folder = new File(outputFolder);
			if (!folder.exists())
			{
				folder.mkdir();
			}

			ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
			ZipEntry ze = zis.getNextEntry();

			while (ze != null)
			{

				String fileName = ze.getName();
				File newFile = new File(outputFolder + File.separator + fileName);

				label.setText("File unzipped : " + newFile.getAbsoluteFile());

				new File(newFile.getParent()).mkdirs();

				FileOutputStream fos = new FileOutputStream(newFile);

				int len;
				while ((len = zis.read(buffer)) > 0)
				{
					fos.write(buffer, 0, len);
				}

				fos.close();
				ze = zis.getNextEntry();
			}

			zis.closeEntry();
			zis.close();

			System.out.println("Done");
			
			JOptionPane.showMessageDialog(frame, "Files successfully extracted !");

		} catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
}
