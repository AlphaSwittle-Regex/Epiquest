package com.regex.epiquest;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.regex.epiquest.util.Backuper;
import com.regex.epiquest.util.Deleter;
import com.regex.epiquest.util.Downloader;
import com.regex.epiquest.util.Mover;
import com.regex.epiquest.util.Unzipper;

public class Epiquest extends JFrame
{
	private static final long serialVersionUID = -756380754027163118L;

	private JLabel nameLabel = new JLabel("No download currently.", SwingConstants.CENTER);

	private JProgressBar progress = new JProgressBar();

	{
		progress.setStringPainted(true);
		progress.setValue(0);
	}

	public Epiquest()
	{
		setTitle("Epiquest Modpack v1.0-1.7.2 --md::downloader");
		setSize(1000, 75);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		setLocationRelativeTo(null);

		JPanel topPanel = new JPanel();
		topPanel.add(nameLabel);

		JPanel progressPanel = new JPanel();
		progressPanel.setLayout(new BorderLayout());
		progressPanel.add(progress, BorderLayout.CENTER);

		setLayout(new BorderLayout());

		add(topPanel, BorderLayout.NORTH);
		add(progressPanel, BorderLayout.CENTER);
	}

	public void getModpack()
	{
		String filename = null;
		File modpackFile = new File("modpack/epiquest-1.0-1.7.2.zip");
		if (!modpackFile.exists())
		{
			filename = Downloader.download("http://download1504.mediafire.com/99p86t695xog/433zevw8ca1bwjr/epiquest-1.0-1.7.2.zip", progress, this, nameLabel);
		} else
		{
			filename = "epiquest-1.0-1.7.2.zip";
		}
		File cacheFolder = new File("modpack/cache");
		File modsFolder = new File(System.getProperty("user.home") + "/.minecraft/mods");
		File[] files = cacheFolder.listFiles();
		File[] files2 = modsFolder.listFiles();
		if (files2.length > 27)
		{
			Deleter.deleteFilesByFolder(System.getProperty("user.home") + "/.minecraft/mods", this, nameLabel);
			Mover.moveFiles("modpack/cache", System.getProperty("user.home") + "/.minecraft/mods", this, nameLabel);
		}
		if (files.length >= 26)
		{
			Backuper.backup(System.getProperty("user.home") + "/.minecraft/mods", "modpack/backup", this, nameLabel);
			Deleter.deleteFilesByFolder(System.getProperty("user.home") + "/.minecraft/mods", this, nameLabel);
			Mover.moveFiles("modpack/cache", System.getProperty("user.home") + "/.minecraft/mods", this, nameLabel);
		} else
		{
			Unzipper.unzip("modpack/" + filename, "modpack/cache", progress, nameLabel, this);
			Backuper.backup(System.getProperty("user.home") + "/.minecraft/mods", "modpack/backup", this, nameLabel);
			Deleter.deleteFilesByFolder(System.getProperty("user.home") + "/.minecraft/mods", this, nameLabel);
			Mover.moveFiles("modpack/cache", System.getProperty("user.home") + "/.minecraft/mods", this, nameLabel);
		}

		Deleter.deleteFileByFile("modpack/epiquest-1.0-1.7.2.zip");
	}

	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		Epiquest dlmanager = new Epiquest();
		dlmanager.getModpack();
	}
}