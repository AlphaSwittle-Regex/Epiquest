package com.regex.epiquest.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Deleter
{
	public static void deleteFilesByFolder(String src, JFrame frame, JLabel label)
	{
		File srcFolder = new File(src);
		if (!srcFolder.exists())
		{
			return;
		}

		else
		{
			frame.setTitle("Epiquest Modpack v1.0-1.7.2 --md::deleter");
			File[] files = srcFolder.listFiles();
			for (int i = 0; i < files.length; i++)
			{
				try
				{
					label.setText("Deleting file " + files[i].getAbsoluteFile() + "...");
					Files.delete(files[i].toPath());
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	public static void deleteFileByFile(String src)
	{
		File srcFile = new File(src);
		if (!srcFile.exists())
		{
			return;
		} else
		{
			try
			{
				Files.delete(srcFile.toPath());
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
