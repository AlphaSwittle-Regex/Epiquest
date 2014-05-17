package com.regex.epiquest.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class Downloader
{
	public static String download(String forUrl, JProgressBar progress, JFrame frame, JLabel nameLabel)
	{
		String nameForFile = forUrl.substring(forUrl.lastIndexOf('/') + 1);
		String filename = "modpack/" + nameForFile;
		File modpackFolder = new File("modpack");
		if (!modpackFolder.exists())
		{
			modpackFolder.mkdir();
		}
		try
		{
			nameLabel.setText("Downloading file " + nameForFile + "...");
			URL url = new URL(forUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			int filesize = connection.getContentLength();
			float totalDataRead = 0;
			java.io.BufferedInputStream in = new java.io.BufferedInputStream(connection.getInputStream());
			java.io.FileOutputStream fos = new java.io.FileOutputStream(filename);
			java.io.BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
			byte[] data = new byte[1024];
			int i = 0;
			while ((i = in.read(data, 0, 1024)) >= 0)
			{
				totalDataRead = totalDataRead + i;
				bout.write(data, 0, i);
				float Percent = (totalDataRead * 100) / filesize;
				progress.setValue((int) Percent);
			}
			bout.close();
			in.close();
			JOptionPane.showMessageDialog(frame, "Modpack package successfully downloaded !");
		} catch (Exception e)
		{
			javax.swing.JOptionPane.showConfirmDialog((java.awt.Component) null, e.getMessage(), "Error", javax.swing.JOptionPane.DEFAULT_OPTION);
			System.exit(0);
		}
		return nameForFile;
	}
}
