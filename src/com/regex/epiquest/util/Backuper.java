package com.regex.epiquest.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Backuper
{
	public static void backup(String srcFolder, String destFolder, JFrame frame, JLabel label)
	{
		File src = new File(srcFolder);
		File dest = new File(destFolder);

		if (!src.exists())
		{
			System.exit(0);
		}
		
		if(!dest.exists())
		{
			dest.mkdir();
		}

		frame.setTitle("Epiquest Modpack v1.0-1.7.2 --md::backuper");

		File[] files = src.listFiles();
		for (int i = 0; i < files.length; i++)
		{
			try
			{
				label.setText("Backuping file " + files[i].getAbsoluteFile());
				Files.move(files[i].toPath(), Paths.get(destFolder + "/" + files[i].getName()), StandardCopyOption.ATOMIC_MOVE);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		JOptionPane.showMessageDialog(frame, "Mods successfully backuped !");
	}
}
