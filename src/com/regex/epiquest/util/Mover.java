package com.regex.epiquest.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Mover
{
	public static void moveFiles(String srcDirectory, String destDirectory, JFrame frame, JLabel label)
	{
		frame.setTitle("Epiquest Modpack v1.0-1.7.2 --md::mover");
		File src = new File(srcDirectory);
		File dest = new File(destDirectory);

		if (src.exists())
		{
			src.mkdir();
		}

		if (dest.exists())
		{
			dest.mkdir();
		}

		File[] files = src.listFiles();

		for (int i = 0; i <= files.length; i++)
		{
			int k = i;
			if (k <= 26)
			{
				try
				{
					label.setText("Moving file " + files[k].getName() + "...");
					Files.move(files[k].toPath(), Paths.get(dest.toPath() + "/" + files[k].getName()), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		JOptionPane.showMessageDialog(frame, "Files successfully moved !");
		JOptionPane.showMessageDialog(frame, "Modpack successfully installed !");
		System.exit(0);
	}
}
