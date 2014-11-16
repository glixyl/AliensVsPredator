package com.arisux.avp.windows;

import java.net.URLEncoder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;

import com.arisux.airi.AIRI;
import com.arisux.airi.api.window.IWindow;
import com.arisux.airi.api.window.Window;
import com.arisux.airi.engine.GuiTypeLib.GuiCustomButton;
import com.arisux.airi.engine.GuiTypeLib.GuiCustomTextbox;
import com.arisux.airi.lib.util.NetworkUtil;
import com.arisux.airi.lib.util.interfaces.IActionPerformed;
import com.arisux.avp.AliensVsPredator;

public class WindowSubmitFeedback extends Window implements IWindow
{
	private GuiCustomButton buttonSubmit;
	public GuiCustomTextbox textbox;
	public String feedback;
	public boolean submitted = false;

	public WindowSubmitFeedback()
	{
		super("BETA_PROGRAM_AVP", I18n.format(AliensVsPredator.properties().LANG_BETA_FEEDBACK_SUBMIT_TITLE), 100, 100, 110, 40);
		this.buttonSubmit = new GuiCustomButton(0, xPos + 100, yPos + 100, 180, 20, AliensVsPredator.properties().LANG_BETA_FEEDBACK_SUBMIT_BUTTON, null);
		this.textbox = new GuiCustomTextbox(0, 0, 0, 0);
		this.width = 300;
		this.height = 80;
		this.setWindowCentered(true);
	}

	@Override
	public void draw(int mouseX, int mouseY)
	{
		super.draw(mouseX, mouseY);

		this.lineSpacing = 10;

		buttonSubmit.xPosition = xPos;
		buttonSubmit.yPosition = yPos + this.height - buttonSubmit.height;
		buttonSubmit.width = this.width;
		buttonSubmit.baseColor = 0xFF0099FF;
		buttonSubmit.setAction(new IActionPerformed()
		{
			@Override
			public void actionPerformed(GuiCustomButton button)
			{
				try
				{
					if (!textbox.getText().equals("") && textbox.getText().length() > 12)
					{
						String request = String.format(AliensVsPredator.properties().URL_SUBMIT_FEEDBACK, Minecraft.getMinecraft().session.getUsername(), Minecraft.getMinecraft().session.getPlayerID(), URLEncoder.encode(textbox.getText(), "UTF-8"));
						feedback = NetworkUtil.getURLContents(request);
						AIRI.logger.info("Submitted feedback: %s", feedback);
					}
					submitted = true;
				}
				catch (Exception e)
				{
					AIRI.logger.info(e.toString());
				}
			}
		});

		textbox.xPosition = xPos + 6;
		textbox.yPosition = yPos + 32;
		textbox.height = 20;
		textbox.width = this.width - 12;
		textbox.setMaxStringLength(200);
		int charsLeft = (textbox.getMaxStringLength() - textbox.getText().length());

		if (!submitted)
		{
			buttonSubmit.drawButton();
			buttonSubmit.handleInput();

			textbox.drawTextBox();
			textbox.handleInput();
			this.setTitle(I18n.format(AliensVsPredator.properties().LANG_BETA_FEEDBACK_CHARSLEFT_TITLE, charsLeft), false);
			this.setDefaultText(AliensVsPredator.properties().LANG_BETA_FEEDBACK_INFO, true);
		}
		else
		{
			if (feedback != null)
			{
				String[] stringReturn = feedback.split(":feedback_split:");
				
				setTitle(AliensVsPredator.properties().LANG_BETA_FEEDBACK_SUBMIT_THANKS_TITLE, true);
				setDefaultText(String.format(I18n.format(AliensVsPredator.properties().LANG_BETA_FEEDBACK_SUBMIT_THANKS), stringReturn[0], stringReturn[1], stringReturn[2]));
			}
			else if (textbox.getText().equals("") && textbox.getText().length() < 12)
			{
				setTitle(AliensVsPredator.properties().LANG_BETA_FEEDBACK_NOTIFY_SPAM_PREVENTION_TITLE, true);
				setDefaultText(AliensVsPredator.properties().LANG_BETA_FEEDBACK_NOTIFY_SPAM_PREVENTION, true);
			}
			else
			{
				setTitle(AliensVsPredator.properties().LANG_BETA_FEEDBACK_SUBMIT_ERROR_TITLE, true);
				setDefaultText(AliensVsPredator.properties().LANG_BETA_FEEDBACK_SUBMIT_ERROR, true);
			}
		}
	}

	@Override
	public void close()
	{
		super.close();
		this.feedback = null;
		this.submitted = false;
	}

	@Override
	public void onButtonPress(GuiButton button)
	{
		;
	}

	@Override
	public void keyTyped(char c, int id)
	{
		textbox.textboxKeyTyped(c, id);
	}
}
