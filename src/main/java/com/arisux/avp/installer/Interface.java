package com.arisux.avp.installer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

public class Interface extends JFrame
{
	private static final long serialVersionUID = 6396196434791174646L;
	private Dimension resolution;
	private Installer installer;
	private BackgroundPane backgroundPane;
	private Interface guiInstance;

	public Interface(final Installer installer)
	{
		this.guiInstance = this;
		this.installer = installer;
		this.resolution = new Dimension(728, 165);
		this.backgroundPane = new BackgroundPane(this);
		this.setSize(getResolution());
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setContentPane(backgroundPane);
		this.setVisible(true);
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);

		g.setColor(Color.BLACK);
		g.drawString(installer.getStatus(), ((int) 130), ((int) 98));
	}

	class BackgroundPane extends JPanel
	{
		private static final long serialVersionUID = 2583874780266631440L;
		private JProgressBar progressBar;

		public BackgroundPane(Interface frame)
		{
			try
			{
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e)
			{
				System.out.println("[Installer] Unable to set the theme.");
			}

			this.setSize(frame.resolution);
			this.setLayout(null);

			{
				progressBar = new JProgressBar();
				progressBar.setMinimum(0);
				progressBar.setMaximum(100);
				progressBar.setSize(getResolution().width - 130, 26);
				progressBar.setLocation(120, 55);
				add(progressBar);
			}

			{
				JButton button = new JButton("Install");
				button.setSize(80, 20);
				button.setLocation(getResolution().width - 90, getResolution().height - 50);
				button.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						new RunInstall(installer, guiInstance);
					}
				});
				this.add(button);
			}

			{
				JButton button = new JButton("Browse");
				button.setSize(80, 20);
				button.setLocation(getResolution().width - 175, getResolution().height - 50);
				button.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						System.out.println("[Installer] Browsing " + installer.getInstallPath());
						JFileChooser browser = new JFileChooser(installer.getInstallPath());
						browser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						browser.showOpenDialog(Installer.getGui());

						installer.setInstallPath(browser.getSelectedFile());
						System.out.println("[Installer] Installation path set to " + installer.getInstallPath());
						Installer.getGui().repaint();
					}
				});

				add(button);
			}
		}

		public JProgressBar getProgressBar()
		{
			return this.progressBar;
		}

		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);

			try
			{
				InputStream input = getClass().getResourceAsStream("/assets/avp/textures/misc/installer.png");
				BufferedImage image = ImageIO.read(input);
				g.drawImage(image, 0, 0, this);
			} catch (Exception e)
			{
				System.out.println("[Installer] Error reading image: " + e);
			}

			g.setColor(Color.BLACK);
			g.fillRect(0, getResolution().height - 50, getResolution().width, 60);

			g.setColor(Color.WHITE);
			g.drawString(installer.getInstallPath().getAbsolutePath(), 10, ((int) resolution.getHeight() - 35));
			g.drawString("Arisux Mod Installer - Copyright (C) 2012-2014 Arisux", 120, 103);
		}

		public void setCompletionPercent(int percent)
		{
			this.progressBar.setValue(percent);
		}
	}

	public void tickGui()
	{
		// this.repaint();
	}

	public Dimension getResolution()
	{
		return resolution;
	}

	public BackgroundPane getBackgroundPane()
	{
		return backgroundPane;
	}
}
